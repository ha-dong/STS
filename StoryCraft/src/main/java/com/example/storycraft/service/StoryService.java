package com.example.storycraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.storycraft.dao.StoryDao;
import com.example.storycraft.dao.ReportDao;
import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Report;

import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryDao storyDao;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private ReportDao reportDao; // ReportDao 주입
    
    // 모든 선택지 목록을 반환하는 메서드
    public List<Choice> getAllChoices() {
        return storyDao.findAllChoices();
    }

    // 스토리와 씬을 저장하는 메서드
    @Transactional
    public void saveStoryAndScene(Story story, Scene scene) {
        // 스토리 저장
        storyDao.saveStory(story);
        System.out.println("스토리 저장됨: " + story.getStTitle());

        // 씬 저장
        if (scene != null) {
            scene.setStNum(story.getStNum()); // 저장된 스토리 번호 설정
            sceneService.saveScene(scene);
            System.out.println("씬 저장됨: " + scene.getScText());
        }
    }

    // 최종 스토리 제출 처리
    public void submitStory(Story story) {
        // 스토리 제출 시 필요한 추가 로직 (예: 상태 변경)
        story.setStDstatus("Submitted"); // 예시로 상태 변경
        storyDao.updateStory(story);
        System.out.println("최종 스토리 제출됨: " + story.getStTitle());
    }

    // 스토리 리스트를 반환하는 메서드
    public List<Story> getStories() {
        return storyDao.findAllStories(); // StoryDao에 findAllStories() 메서드가 구현되어 있어야 합니다.
    }

    // 특정 스토리 조회
    public Story findStoryById(Long stNum) {
        return storyDao.findStoryById(stNum);
    }

    // 스토리 수정
    public void updateStory(Story story) {
        storyDao.updateStory(story);
    }

    // 스토리 삭제
    public boolean deleteStory(Long stNum) {
        return storyDao.deleteStory(stNum);
    }

    // 스토리 신고
    public boolean reportStory(Long stNum, String reason) {
        Report report = new Report();
        report.setStNum(stNum);
        report.setReTypecode("신고"); // 고정값, 필요에 따라 변경 가능
        report.setReText(reason);
        report.setReDate(new java.sql.Timestamp(System.currentTimeMillis()));
        return reportDao.saveReport(report);
    }
}
