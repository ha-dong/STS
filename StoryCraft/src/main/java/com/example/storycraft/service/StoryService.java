package com.example.storycraft.service;

import org.springframework.stereotype.Service;

import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    // 메모리에 스토리와 씬을 저장하는 리스트
    private List<Story> stories = new ArrayList<>();
    private List<Scene> scenes = new ArrayList<>();

    // 스토리와 씬을 저장하는 메서드
    public void saveStoryAndScene(Story story, Scene scene) {
        stories.add(story);  // 메모리에 스토리 저장
        scenes.add(scene);    // 메모리에 씬 저장
        System.out.println("스토리 저장됨: " + story.getStTitle());
        System.out.println("씬 저장됨: " + scene.getScText());
    }

    // 최종 스토리 제출 처리
    public void submitStory(Story story) {
        // 제출된 스토리 확인 (일단 메모리에 있는 스토리를 그대로 유지)
        System.out.println("최종 스토리 제출됨: " + story.getStTitle());
    }

    // 스토리 리스트를 반환하는 메서드
    public List<Story> getStories() {
        return stories;  // 저장된 스토리 목록 반환
    }
}
