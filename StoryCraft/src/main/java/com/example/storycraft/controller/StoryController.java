package com.example.storycraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.service.StoryService;

@Controller
public class StoryController {

    @Autowired
    private StoryService storyService;

    // 스토리 페이지로 이동
    @GetMapping("/story")
    public String showStoryPage() {
        return "story";  // story.jsp로 이동
    }

    // 스토리 추가 페이지로 이동
    @GetMapping("/storyadd")
    public String showStoryAddPage() {
        return "storyadd";  // storyadd.jsp로 이동
    }

    // 스토리 목록 페이지로 이동
    @GetMapping("/storylist")
    public String list() {
        return "storylist";  // storylist.jsp로 이동
    }

    // 테스트용 스토리 저장 후 리다이렉트 (예시)
    @PostMapping("/testStory")
    public String testStory(@ModelAttribute Story story) {
        System.out.println(story);

        // 스토리와 씬을 저장하는 로직이 주석처리 되어있어 주석 해제 가능
        // storyService.saveStoryAndScene(story, new Scene());

        // 스토리 추가 페이지로 리다이렉트
        return "redirect:/storyadd";
    }

    // 스토리와 씬을 저장 후 스토리 추가 페이지로 리다이렉트
    @PostMapping("/save")
    public String saveStory(@ModelAttribute Story story, @ModelAttribute Scene scene) {
        // 스토리와 씬을 저장
        storyService.saveStoryAndScene(story, scene);

        // 저장 완료 후 스토리 추가 페이지로 리다이렉트
        return "redirect:/storyadd";
    }

    // 최종 스토리 제출 후 처리
    @PostMapping("/submit")
    public String submitStory(@ModelAttribute Story story) {
        // 최종 스토리 제출 처리
        storyService.submitStory(story);

        // 제출 완료 후 스토리 목록 페이지로 리다이렉트
        return "redirect:/storylist";
    }
}
