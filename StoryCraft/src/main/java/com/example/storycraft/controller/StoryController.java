package com.example.storycraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.storycraft.model.Story;
import com.example.storycraft.service.StoryService;

import io.jsonwebtoken.io.IOException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping
    public String showStoryPage() {
        return "story";  // story.jsp로 이동
    }

    @GetMapping("/storylist")
    public String list(Model model) {
        List<Story> stories = storyService.getStories(); // StoryService에 getStories() 메서드가 구현되어 있어야 합니다.
        model.addAttribute("stories", stories);
        return "storylist";  // storylist.jsp로 이동
    }
    
    @GetMapping("/list")
    public String listAlias(Model model) {
        return list(model); // 이미 수정된 list 메서드를 호출합니다.
    }


    @PostMapping("/save")
    public String saveStory(@ModelAttribute Story story,
                            @RequestParam("stImg") MultipartFile stImg,
                            Model model) throws Exception {

        // 이미지 업로드 처리
        if (!stImg.isEmpty()) {
            try {
                String uploadDir = "D:/upload/"; // 실제 업로드 디렉토리로 변경 필요
                String fileName = System.currentTimeMillis() + "_" + stImg.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                stImg.transferTo(dest);
                story.setStCover("/profile-images/" + fileName); // 업로드된 파일 경로 설정
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "이미지 업로드에 실패했습니다.");
                return "error"; // error.jsp로 리다이렉트
            }
        }

        // 스토리 저장 (Scene은 StoryAddController에서 별도로 처리)
        storyService.saveStoryAndScene(story, null);

        // 생성된 stNum을 URL 파라미터로 전달하여 storyadd.jsp로 이동
        return "redirect:/storyadd?stNum=" + story.getStNum();
    }

    @GetMapping("/getStoryDetail")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStoryDetail(@RequestParam Long stNum) {
        Story story = storyService.findStoryById(stNum);
        if (story != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("stNum", story.getStNum());
            response.put("stTitle", story.getStTitle());
            response.put("stCrdate", story.getStCrdate());
            response.put("stEddate", story.getStEddate());
            response.put("stViewnum", story.getStViewnum());
            response.put("stTypecode", story.getStTypecode());
            response.put("stGenrecode", story.getStGenrecode());
            response.put("stSugnum", story.getStSugnum());
            response.put("stCover", story.getStCover());
            response.put("stContent", story.getStContent());
            response.put("uId", story.getuId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/edit")
    public String showEditStoryPage(@RequestParam Long stNum, Model model) {
        Story story = storyService.findStoryById(stNum);
        if (story != null) {
            model.addAttribute("story", story);
            return "editStory";  // editStory.jsp로 이동 (추가 JSP 파일 필요)
        } else {
            return "redirect:/story/storylist";  // 스토리가 없으면 목록 페이지로 리다이렉트
        }
    }

    @PostMapping("/edit")
    public String editStory(@ModelAttribute Story story,
                            @RequestParam("stImg") MultipartFile stImg,
                            Model model) throws Exception {
        // 이미지 업로드 처리 (수정 시 새로운 이미지가 제공된 경우)
        if (!stImg.isEmpty()) {
            try {
                String uploadDir = "D:/upload/"; // 실제 업로드 디렉토리로 변경 필요
                String fileName = System.currentTimeMillis() + "_" + stImg.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                stImg.transferTo(dest);
                story.setStCover("/profile-images/" + fileName); // 업로드된 파일 경로 설정
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "이미지 업로드에 실패했습니다.");
                return "error"; // error.jsp로 리다이렉트
            }
        }

        // 스토리 업데이트
        storyService.updateStory(story);
        return "redirect:/story/storylist";  // 스토리 목록 페이지로 리다이렉트
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> deleteStory(@RequestBody Map<String, Long> payload) {
        Long stNum = payload.get("stNum");
        boolean deleted = storyService.deleteStory(stNum);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", deleted);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/report")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> reportStory(@RequestBody Map<String, Object> payload) {
        Long stNum = Long.valueOf(payload.get("stNum").toString());
        String reason = payload.get("reason").toString();
        boolean reported = storyService.reportStory(stNum, reason);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", reported);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit")
    public String submitStory(@ModelAttribute Story story) {
        // 스토리 제출 시 필요한 추가 로직 (예: 상태 변경)
        storyService.submitStory(story);
        return "redirect:/story/storylist";  // 스토리 목록 페이지로 리다이렉트
    }
}
