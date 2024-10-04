// StoryController.java

package com.example.storycraft.controller;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.service.ChoiceService;
import com.example.storycraft.service.SceneService;
import com.example.storycraft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/story")
public class StoryController {

    private static final Logger logger = LoggerFactory.getLogger(StoryController.class);

    @Autowired
    private StoryService storyService;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private ChoiceService choiceService;

    // 스토리 제작 페이지 이동 (추가: editMode 파라미터)
    @GetMapping({"/create", "/edit"})
    public String showStoryCreatePage(@RequestParam(value = "stNum", required = false) Integer stNum, Model model) {
        if (stNum != null) {
            // 수정일 때
            // 스토리 조회
            Story story = storyService.getStoryById(stNum);

            // 씬 조회
            List<Scene> scenes = sceneService.getSceneByStNum(stNum);

            // 초이스 조회
            for (Scene scene : scenes) {
                List<Choice> choices = choiceService.getChoicesByScNum(scene.getScNum());
                scene.setChoices(choices);
            }

            // 스토리객체에 씬, 초이스 세팅
            story.setScenes(scenes);

            if (story != null) {
                model.addAttribute("story", story);
                model.addAttribute("editMode", true);
            }
        }
        // 장르 목록 추가
        model.addAttribute("genreList", storyService.getGenreList());
        return "storyCreate";
    }
    
// // 스토리 생성 페이지 이동
//    @GetMapping("/create")
//    public String showStoryCreatePage(Model model) {
//        model.addAttribute("genreList", storyService.getGenreList());
//        return "storyCreate"; // 스토리 생성 페이지 뷰
//    }
//
//    // 스토리 수정 페이지 이동
//    @GetMapping("/edit")
//    public String showStoryEditPage(@RequestParam("stNum") Integer stNum, Model model, HttpSession session) {
//        // 로그인된 사용자 확인
//        String userId = getUserIdFromSessionOrCookie(session, null);
//        Story story = storyService.getStoryById(stNum);
//        if (story == null) {
//            // 스토리가 없을 경우 에러 페이지로 이동하거나 메시지 표시
//            return "error/404";
//        }
//        if (!story.getuId().equals(userId)) {
//            // 수정 권한이 없는 경우 처리
//            return "error/403";
//        }
//        model.addAttribute("story", story);
//        model.addAttribute("genreList", storyService.getGenreList());
//        return "storyEdit"; // 스토리 수정 페이지 뷰
//    }


    // 스토리 저장 처리 (수정 포함)
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveStory(
            @RequestParam Map<String, String> allParams,
            @RequestParam(value = "cover", required = false) MultipartFile cover,
            @RequestParam Map<String, MultipartFile> files,
            HttpSession session,
            HttpServletRequest request
    ) {
        Map<String, Object> response = new HashMap<>();

        // 로그인된 사용자 ID 가져오기
        String userId = getUserIdFromSessionOrCookie(session, request);

        try {
            // 스토리 기본 정보 추출
            String title = allParams.get("title");
            String genre = allParams.get("genre");
            int initialMoney = Integer.parseInt(allParams.getOrDefault("initialMoney", "0"));
            int initialHP = Integer.parseInt(allParams.getOrDefault("initialHP", "100"));
            String endCode = allParams.get("endCode");
            Integer stNum = allParams.get("stNum") != null ? Integer.parseInt(allParams.get("stNum")) : null;

            // 필수 입력 필드 검증
            if (title == null || title.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "제목은 필수 입력 사항입니다.");
                return response;
            }
            if (genre == null || genre.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "장르는 필수 선택 사항입니다.");
                return response;
            }

            // 파일 저장 처리
            String coverFileName = null;
            if (cover != null && !cover.isEmpty()) {
                try {
                    // 원본 파일 이름 가져오기
                    String originalFileName = cover.getOriginalFilename();
                    // 확장자 추출
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    // 파일 이름에서 확장자를 제외한 부분 추출
                    String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                    // 고유한 파일 이름 생성 (타임스탬프 + 원본 파일 이름)
                    coverFileName = System.currentTimeMillis() + "_" + fileNameWithoutExtension + fileExtension;

                    // 저장 경로 설정 및 파일 저장
                    String savePath = "C:/embeded/upload/" + coverFileName;
                    cover.transferTo(new File(savePath));
                } catch (IOException e) {
                    logger.error("표지 이미지 저장 중 오류 발생", e);
                    response.put("success", false);
                    response.put("message", "파일 저장 중 오류 발생.");
                    return response;
                }
            }

            // 삽화 이미지 파일 저장 처리
            for (String key : files.keySet()) {
                if (key.startsWith("sceneImage_")) {
                    MultipartFile sceneImage = files.get(key);
                    if (sceneImage != null && !sceneImage.isEmpty()) {
                        try {
                            // 원본 파일 이름 가져오기
                            String originalFileName = sceneImage.getOriginalFilename();
                            // 확장자 추출
                            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                            // 파일 이름에서 확장자를 제외한 부분 추출
                            String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                            // 고유한 파일 이름 생성 (타임스탬프 + 원본 파일 이름)
                            String sceneImageFileName = System.currentTimeMillis() + "_" + fileNameWithoutExtension + fileExtension;

                            // 저장 경로 설정 및 파일 저장
                            String savePath = "C:/embeded/upload/" + sceneImageFileName;
                            sceneImage.transferTo(new File(savePath));

                            // 파일명을 allParams에 추가
                            String sceneNum = key.substring("sceneImage_".length());
                            allParams.put("sceneImageFileName_" + sceneNum, sceneImageFileName);
                        } catch (IOException e) {
                            logger.error("삽화 이미지 저장 중 오류 발생", e);
                            response.put("success", false);
                            response.put("message", "삽화 이미지 저장 중 오류 발생.");
                            return response;
                        }
                    }
                }
            }

            boolean isSaved;
            if (stNum == null) {
                // 스토리 저장 로직 호출
                isSaved = storyService.saveFullStory(title, genre, coverFileName, initialMoney, initialHP, userId, allParams);
            } else {
                // 스토리 수정 로직 호출
                isSaved = storyService.updateFullStory(stNum, title, genre, coverFileName, initialMoney, initialHP, userId, allParams);
            }

            if (isSaved) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "스토리 저장 중 오류 발생.");
            }
        } catch (Exception e) {
            logger.error("스토리 저장 중 예외 발생", e);
            response.put("success", false);
            response.put("message", "서버 오류 발생: " + e.getMessage());
        }
        return response;
    }

    // 스토리 목록 페이지 이동
    @GetMapping("/list")
    public String showStoryListPage(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "sort", required = false) String sort,
            Model model,
            HttpSession session,
            HttpServletRequest request
    ) {
        List<Story> storyList = storyService.getAllStoriesFilteredAndSorted(genre, sort);
        model.addAttribute("storyList", storyList);

        // 로그인된 사용자 ID 가져오기
        String userId = (String) session.getAttribute("user"); // "userId" -> "user"
        if (userId == null) {
            // 쿠키에서 userId 가져오기 (선택 사항)
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("U_ID".equals(cookie.getName())) {
                        userId = cookie.getValue();
                        break;
                    }
                }
            }
            if (userId == null) {
                userId = "subo"; // 쿠키에도 없으면 "subo"로 설정
            }
        }
        model.addAttribute("userId", userId);

        // Assuming genreList is fetched from service or defined elsewhere
        model.addAttribute("genreList", storyService.getGenreList());

        return "storyList";
    }

    // 스토리 삭제 처리
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteStory(@RequestParam("stNum") int stNum, HttpSession session, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        String userId = getUserIdFromSessionOrCookie(session, request);

        Story story = storyService.getStoryById(stNum);

        if (story == null) {
            response.put("success", false);
            response.put("message", "스토리를 찾을 수 없습니다.");
            return response;
        }

        if (!userId.equals(story.getuId())) {
            response.put("success", false);
            response.put("message", "삭제 권한이 없습니다.");
            return response;
        }

        try {
            boolean isDeleted = storyService.deleteStory(stNum);

            if (isDeleted) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "스토리 삭제에 실패했습니다.");
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("스토리 삭제 중 데이터 무결성 오류 발생", e);
            response.put("success", false);
            response.put("message", "스토리에 관련된 다른 데이터가 존재하여 삭제할 수 없습니다.");
        } catch (Exception e) {
            logger.error("스토리 삭제 중 예외 발생", e);
            response.put("success", false);
            response.put("message", "스토리 삭제 중 오류가 발생했습니다.");
        }

        return response;
    }
    
    // 유저 ID 가져오는 메서드
    private String getUserIdFromSessionOrCookie(HttpSession session, HttpServletRequest request) {
        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("U_ID".equals(cookie.getName())) {
                        userId = cookie.getValue();
                        break;
                    }
                }
            }
            if (userId == null) {
                userId = "subo"; // 기본값 설정
            }
        }
        return userId;
    }

    @PostMapping("/incrementViewCount")
    @ResponseBody
    public Map<String, Object> incrementViewCount(@RequestParam("stNum") int stNum) {
        Map<String, Object> response = new HashMap<>();
        try {
            storyService.incrementViewCount(stNum);
            response.put("success", true);
        } catch (Exception e) {
            logger.error("조회수 증가 중 오류 발생", e);
            response.put("success", false);
            response.put("message", "조회수 증가 중 오류가 발생했습니다.");
        }
        return response;
    }

    
    // 신고 처리
    @PostMapping("/report")
    @ResponseBody
    public Map<String, Object> reportStory(
            @RequestParam Map<String, String> params,
            @RequestParam(value = "reportImage", required = false) MultipartFile reportImage,
            HttpSession session, HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        String userId = getUserIdFromSessionOrCookie(session, request);

        try {
            int stNum = Integer.parseInt(params.get("stNum"));
            String reTypeCode = params.get("reTypeCode");
            String reText = params.get("reText");

            // 이미지 파일 저장 처리
            String imagePath = null;
            if (reportImage != null && !reportImage.isEmpty()) {
                try {
                    // 파일 저장 경로 설정
                    String originalFileName = reportImage.getOriginalFilename();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String fileName = System.currentTimeMillis() + "_" + originalFileName;

                    // 파일 저장 (예: C:/uploaded/reportImages/)
                    String savePath = "C:/uploaded/reportImages/" + fileName;
                    reportImage.transferTo(new File(savePath));

                    // 이미지 경로 저장
                    imagePath = savePath;
                } catch (IOException e) {
                    response.put("success", false);
                    response.put("message", "이미지 파일 저장 중 오류가 발생했습니다.");
                    return response;
                }
            }

            // 신고 저장 처리
            boolean isReported = storyService.reportStory(stNum, reTypeCode, reText, userId, imagePath);

            if (isReported) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "신고 접수에 실패했습니다.");
            }
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "잘못된 스토리 번호입니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 처리 중 오류가 발생했습니다.");
        }

        return response;
    }
    
    @GetMapping("/play")
    public String playStory(@RequestParam("stNum") int stNum, Model model) {
        Scene firstScene = sceneService.getFirstSceneByStory(stNum);
        List<Choice> choices = choiceService.getChoicesByScNum(firstScene.getScNum()); // 선택지 가져오기
        model.addAttribute("story", storyService.getStoryById(stNum));
        model.addAttribute("scene", firstScene);
        model.addAttribute("choices", choices); // 선택지 추가
        return "storyPlay";
    }

    @PostMapping("/nextScene")
    @ResponseBody
    public Map<String, Object> getNextScene(@RequestBody Map<String, Integer> requestParams) {
        Map<String, Object> response = new HashMap<>();
        try {
            int choiceNum = requestParams.get("choiceNum");

            // 선택한 선택지의 NEXT_SC_NUM 가져오기
            Integer nextScNum = choiceService.getNextSceneNum(choiceNum);

            // 다음 장면이 없을 경우 처리
            if (nextScNum == null || nextScNum == 0) {
                response.put("success", false);
                response.put("message", "다음 장면이 존재하지 않습니다.");
                return response;
            }

            // 다음 장면 가져오기
            Scene nextScene = sceneService.getSceneByScNum(nextScNum);
            
            // 선택지 추가
            if (nextScene != null) {
                List<Choice> choices = choiceService.getChoicesByScNum(nextScene.getScNum());
                response.put("success", true);
                response.put("scene", nextScene);
                response.put("choices", choices);
            } else {
                response.put("success", false);
                response.put("message", "다음 장면을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            logger.error("Error fetching next scene", e);
            response.put("success", false);
            response.put("message", "서버 오류 발생: " + e.getMessage());
        }
        return response;
    }
    
    
    // 추천 처리
    @PostMapping("/recommend")
    @ResponseBody
    public Map<String, Object> recommendStory(@RequestParam("stNum") int stNum, HttpSession session, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        // 로그인된 사용자 ID 가져오기
        String userId = (String) session.getAttribute("user"); // "userId" -> "user"
        if (userId == null) {
            // 쿠키에서 userId 가져오기
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("U_ID".equals(cookie.getName())) {
                        userId = cookie.getValue();
                        break;
                    }
                }
            }
            if (userId == null) {
                userId = "subo"; // 쿠키에도 없으면 "subo"로 설정
            }
        }

        try {
            boolean isRecommended = storyService.recommendStory(stNum, userId);

            if (isRecommended) {
                // 추천된 후 추천수 가져오기
                Story story = storyService.getStoryById(stNum);
                response.put("success", true);
                response.put("recommendCount", story.getStSugnum()); // 추천수 추가
            } else {
                response.put("success", false);
                response.put("message", "이미 추천한 스토리이거나 자신의 스토리입니다.");
            }
        } catch (Exception e) {
            logger.error("추천 처리 중 예외 발생", e);
            response.put("success", false);
            response.put("message", "추천 처리 중 오류가 발생했습니다.");
        }

        return response;
    }

}
