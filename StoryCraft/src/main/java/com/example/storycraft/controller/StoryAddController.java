package com.example.storycraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.service.SceneService;
import com.example.storycraft.service.StoryService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/storyadd")
public class StoryAddController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private SceneService sceneService;

    @GetMapping
    public String showStoryAddPage(
            @RequestParam Long stNum,
            @RequestParam(required = false) String choiceName,
            @RequestParam(required = false) String choiceContent,
            @RequestParam(required = false, defaultValue = "0") Long moneyEffect,
            @RequestParam(required = false, defaultValue = "0") Long hpEffect,
            Model model) {

        // 선택지 정보 전달
        if (choiceName != null && choiceContent != null) {
            model.addAttribute("choiceName", choiceName);
            model.addAttribute("choiceContent", choiceContent);
            model.addAttribute("moneyEffect", moneyEffect);
            model.addAttribute("hpEffect", hpEffect);
        }

        // 선택지 목록 추가
        model.addAttribute("choices", storyService.getAllChoices());

        // stNum을 모델에 추가
        model.addAttribute("stNum", stNum);

        return "storyadd"; // storyadd.jsp로 이동
    }

    // 스토리 추가 저장
    @PostMapping("/save")
    public String saveStoryAdd(
            @RequestParam Long stNum,
            @RequestParam String choiceName,
            @RequestParam String choiceContent,
            @RequestParam Long moneyEffect,
            @RequestParam Long hpEffect,
            @RequestParam("additionalImg") MultipartFile additionalImg,
            @RequestParam String stContent,
            Model model) throws Exception {

        // 추가 이미지 업로드 처리
        String additionalImgPath = null;
        if (!additionalImg.isEmpty()) {
            try {
                String uploadDir = "D:/upload/"; // 실제 업로드 디렉토리로 변경 필요
                String fileName = System.currentTimeMillis() + "_" + additionalImg.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                additionalImg.transferTo(dest);
                additionalImgPath = "/profile-images/" + fileName; // 업로드된 파일 경로 설정
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "추가 이미지 업로드에 실패했습니다.");
                return "error"; // error.jsp로 리다이렉트
            }
        }

        // 새로운 스토리 생성
        Story newStory = new Story();
        newStory.setStTitle(choiceName + "의 이어진 스토리"); // 예시 제목
        newStory.setStContent(stContent);
        newStory.setStCover(additionalImgPath);
        newStory.setuId(getCurrentUserId()); // 실제 로그인 사용자 ID로 변경 필요
        newStory.setStTypecode("CST-02"); // 회원스토리 유형 코드
        newStory.setStGenrecode("CG-01"); // 예시 장르 코드 (판타지)

        // 스토리 저장
        storyService.saveStoryAndScene(newStory, null); // Scene은 별도로 저장

        // 씬 생성
        Scene newScene = new Scene();
        newScene.setStNum(newStory.getStNum());
        newScene.setParentScNum(0L); // 부모 씬 번호 설정 (필요 시 변경)
        newScene.setScLevel(1L); // 씬 레벨 설정 (필요 시 변경)
        newScene.setPlayerNum(1L); // 플레이어 번호 설정 (필요 시 변경)
        newScene.setScText(choiceContent);
        newScene.setScChoices(choiceName);
        newScene.setScIllus(additionalImgPath);
        newScene.setMoney(moneyEffect);
        newScene.setHp(hpEffect);
        newScene.setEndCode("CE-01"); // 예시 엔딩 코드
        newScene.setSoundCode("CS-01"); // 예시 음향 코드

        // 씬 저장
        sceneService.saveScene(newScene);

        return "redirect:/story/storylist"; // 스토리 목록 페이지로 리다이렉트
    }

    // 현재 로그인한 사용자 ID 가져오기 (예시)
    private String getCurrentUserId() {
        return "currentUser"; // 실제 로그인 사용자 ID로 변경 필요
    }
}
