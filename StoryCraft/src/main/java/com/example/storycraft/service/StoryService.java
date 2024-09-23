package com.example.storycraft.service;

import com.example.storycraft.dao.SceneDao;
import com.example.storycraft.dao.StoryDao;
import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryDao storyDao;

    @Autowired
    private SceneDao sceneDao;

    // 스토리와 씬을 저장하는 메서드
    public void saveStoryAndScene(Story story, Scene scene) {
        // 스토리 저장
        storyDao.saveStory(story);
        // 씬 저장 (스토리에 연결된 씬이 있는 경우)
        sceneDao.saveScene(scene);
        System.out.println("스토리와 씬이 저장되었습니다: " + story.getStTitle());
    }

    // 모든 스토리 조회
    public List<Story> getStories() {
        return storyDao.findAllStories();
    }

    // 특정 스토리 조회 메서드 (Optional 처리)
    public Optional<Story> getStoryById(Long id) {
        return Optional.ofNullable(storyDao.findStoryById(id));
    }

    // 특정 씬 조회 메서드
    public Optional<Scene> getSceneById(Long id) {
        return Optional.ofNullable(sceneDao.findSceneById(id));
    }

    // 최종 스토리 제출 처리
    public void submitStory(Story story) {
        // 제출된 스토리 확인 및 처리 로직 추가 가능
        System.out.println("최종 스토리가 제출되었습니다: " + story.getStTitle());
        storyDao.saveStory(story);  // 제출된 스토리를 저장
    }

    // 스토리 업데이트 메서드
    public void updateStory(Story updatedStory) {
        Optional<Story> existingStory = getStoryById(updatedStory.getStNum());
        if (existingStory.isPresent()) {
            Story story = existingStory.get();
            story.setStTitle(updatedStory.getStTitle());
            story.setStTypecode(updatedStory.getStTypecode());
            story.setStGenrecode(updatedStory.getStGenrecode());
            story.setStCrdate(updatedStory.getStCrdate());
            storyDao.updateStory(story);
            System.out.println("스토리가 업데이트되었습니다: " + story.getStTitle());
        } else {
            throw new RuntimeException("스토리를 찾을 수 없습니다: ID " + updatedStory.getStNum());
        }
    }

    // 스토리 삭제 메서드
    public void deleteStory(Long id) {
        Optional<Story> story = getStoryById(id);
        if (story.isPresent()) {
            storyDao.deleteStory(id);
            System.out.println("스토리가 삭제되었습니다: ID " + id);
        } else {
            throw new RuntimeException("스토리를 찾을 수 없습니다: ID " + id);
        }
    }

    // 씬 삭제 메서드
    public void deleteScene(Long id) {
        Optional<Scene> scene = getSceneById(id);
        if (scene.isPresent()) {
            sceneDao.deleteScene(id);
            System.out.println("씬이 삭제되었습니다: ID " + id);
        } else {
            throw new RuntimeException("씬을 찾을 수 없습니다: ID " + id);
        }
    }

    // 스토리와 씬을 함께 저장할 때의 검증 로직 등을 처리할 수 있음
    public synchronized void saveStoryAndScenes(Story story, List<Scene> scenes) {
        // 스토리 저장
        storyDao.saveStory(story);

        // 씬 리스트 저장
        for (Scene scene : scenes) {
            sceneDao.saveScene(scene);
        }
        System.out.println("스토리와 씬들이 저장되었습니다.");
    }
}
