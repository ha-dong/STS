package com.example.storycraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.service.SceneService;
import com.example.storycraft.service.StoryService;

import java.util.Optional;

@Controller
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private StoryService storyService;

    // 씬을 저장하고 처리하는 메서드
    @PostMapping("/saveScene")
    public String saveScene(@ModelAttribute Scene scene) {
        sceneService.saveScene(scene);
        return "redirect:/storyadd";
    }

    // 씬을 업데이트하는 메서드
    @PostMapping("/updateScene")
    public String updateScene(@ModelAttribute Scene scene) {
        sceneService.updateScene(scene);
        return "redirect:/storylist";
    }

    // 씬 조회 메서드 - Optional 처리
    @GetMapping("/scene/{id}")
    public String getScene(Long sceneId) {
        Optional<Scene> optionalScene = sceneService.findSceneById(sceneId);
        if (optionalScene.isPresent()) {
            Scene scene = optionalScene.get();  // Optional에서 Scene 객체 추출
            // scene 객체 사용
        } else {
            System.out.println("씬을 찾을 수 없습니다.");
        }

        // 또는 간단하게 처리 (orElseThrow 사용)
        Scene scene = sceneService.findSceneById(sceneId)
                                  .orElseThrow(() -> new RuntimeException("씬을 찾을 수 없습니다."));
        // scene 객체 사용
        return "sceneView";
    }
}
