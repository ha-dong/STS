// StoryController.java

package com.example.storycraft.controller;

import com.example.storycraft.model.Story;
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

    // 스토리 제작 페이지 이동 (추가: editMode 파라미터)
    @GetMapping({"/create", "/edit"})
    public String showStoryCreatePage(@RequestParam(value = "stNum", required = false) Integer stNum, Model model) {
        if (stNum != null) {
            Story story = storyService.getStoryById(stNum);
            if (story != null) {
                model.addAttribute("story", story);
                model.addAttribute("editMode", true);
            }
        }
        // Assuming genreList is fetched from service or defined elsewhere
        model.addAttribute("genreList", storyService.getGenreList());
        return "storyCreate";
    }

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
                    coverFileName = System.currentTimeMillis() + "_" + cover.getOriginalFilename();
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
                            String sceneImageFileName = System.currentTimeMillis() + "_" + sceneImage.getOriginalFilename();
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

    // 신고 처리
    @PostMapping("/report")
    @ResponseBody
    public Map<String, Object> reportStory(@RequestParam Map<String, String> params, HttpSession session, HttpServletRequest request) {
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
            int stNum = Integer.parseInt(params.get("stNum"));
            String reTypeCode = params.get("reTypeCode");
            String reText = params.get("reText");

            boolean isReported = storyService.reportStory(stNum, reTypeCode, reText, userId);

            if (isReported) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "신고 접수에 실패했습니다.");
            }
        } catch (NumberFormatException e) {
            logger.error("신고 처리 중 잘못된 스토리 번호: {}", params.get("stNum"), e);
            response.put("success", false);
            response.put("message", "잘못된 스토리 번호입니다.");
        } catch (Exception e) {
            logger.error("신고 처리 중 예외 발생", e);
            response.put("success", false);
            response.put("message", "신고 처리 중 오류가 발생했습니다.");
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
                response.put("success", true);
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

    // 스토리 플레이 페이지 이동
    @GetMapping("/play")
    public String playStory(@RequestParam("stNum") int stNum, Model model) {
        // 스토리 및 첫 번째 장면 정보 로드
        Story story = storyService.getStoryById(stNum);
        if (story == null) {
            // 스토리를 찾을 수 없는 경우, 에러 페이지로 이동
            return "error/404";
        }
        // 첫 번째 장면 정보도 로드해야 함 (추가 구현 필요)
        model.addAttribute("story", story);
        // 필요한 데이터 추가

        return "storyPlay";
    }
}
