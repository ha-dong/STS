package com.example.storycraft.controller;

import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.service.SceneService;
import com.example.storycraft.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class StartStoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private SceneService sceneService;

    // 특정 스토리를 시작하는 페이지로 이동
    @GetMapping("/startStory/{storyId}")
    public String startStory(@PathVariable("storyId") Long storyId, Model model) {
        // 스토리 조회
        Optional<Story> optionalStory = storyService.getStoryById(storyId);
        if (optionalStory.isPresent()) {
            Story story = optionalStory.get();
            model.addAttribute("story", story);

            // 스토리에 관련된 씬들 조회
            List<Scene> scenes = sceneService.findAllScenes();
            model.addAttribute("scenes", scenes);
            
            return "startstory";  // startstory.jsp로 이동
        } else {
            model.addAttribute("error", "스토리를 찾을 수 없습니다.");
            return "error";  // 에러 페이지로 이동
        }
    }

    // 특정 씬을 진행하는 메서드
    @GetMapping("/startStory/scene/{sceneId}")
    public String startScene(@PathVariable("sceneId") Long sceneId, Model model) {
        // 씬 조회
        Optional<Scene> optionalScene = sceneService.findSceneById(sceneId);
        if (optionalScene.isPresent()) {
            Scene scene = optionalScene.get();
            model.addAttribute("scene", scene);
            return "sceneplay";  // 해당 씬을 플레이하는 페이지로 이동
        } else {
            model.addAttribute("error", "씬을 찾을 수 없습니다.");
            return "error";  // 에러 페이지로 이동
        }
    }
}
