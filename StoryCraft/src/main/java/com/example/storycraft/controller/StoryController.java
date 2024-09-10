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
    
    // 스토리 추가 페이지로 이동
    @GetMapping("/storylist")
    public String list() {
    	return "storylist";  // storylist.jsp로 이동
    }

    // 스토리와 씬 저장 후 storyadd.jsp로 리다이렉트
    @PostMapping("/testStory")
    public String testStory(@ModelAttribute Story story) {
    	
    	System.out.println(story);
    	
    	// 스토리와 씬을 저장
//    	storyService.saveStoryAndScene(story, scene);
    	
    	// 스토리 추가 페이지로 이동
    	return "redirect:/story/add";  // storyadd.jsp로 리다이렉트
    }
    
    // 스토리와 씬 저장 후 storyadd.jsp로 리다이렉트
    @PostMapping("/save")
    public String saveStory(@ModelAttribute Story story, @ModelAttribute Scene scene) {
        // 스토리와 씬을 저장
        storyService.saveStoryAndScene(story, scene);

        // 스토리 추가 페이지로 이동
        return "redirect:/story/add";  // storyadd.jsp로 리다이렉트
    }

    // 최종 스토리 제출
    @PostMapping("/submit")
    public String submitStory(@ModelAttribute Story story) {
        // 최종 스토리 제출 처리
        storyService.submitStory(story);

        // 제출 완료 후 스토리 목록 페이지로 리다이렉트
        return "redirect:/storylist";
    }
}
