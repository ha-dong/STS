package com.example.storycraft.service;

import com.example.storycraft.dao.SceneDao;
import com.example.storycraft.dao.StoryDao;
import com.example.storycraft.dao.ReportDao;
import com.example.storycraft.dao.PlayerDao;
import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class StoryService {

    private static final Logger logger = LoggerFactory.getLogger(StoryService.class);

    @Autowired
    private StoryDao storyDao;

    @Autowired
    private SceneDao sceneDao;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private PlayerDao playerDao;

    // 전체 스토리 저장
    public boolean saveFullStory(String title, String genre, String coverFileName, int initialMoney, int initialHP,
                                 String userId, Map<String, String> allParams) {
        try {
            // 스토리 저장
            Story story = new Story();
            story.setStTitle(title);
            story.setStGenrecode(genre);
            story.setStTypecode("CST-02");
            story.setStCover(coverFileName);
            story.setuId(userId != null ? userId : "subo"); // userId가 null이면 "subo"로 설정
            story.setEndCode(allParams.get("endCode")); // 엔딩 코드 설정
            // 기타 필드 설정

            int result = storyDao.insertStory(story);

            if (result > 0) {
                // 방금 삽입된 스토리의 ST_NUM 가져오기
                int stNum = storyDao.getLastInsertedStNum();

                // 장면 및 선택지 데이터 파싱
                List<Scene> scenes = parseScenesFromParameters(allParams, stNum);

                // 장면 저장
                for (Scene scene : scenes) {
                    sceneDao.insertScene(scene);
                }

                return true;
            } else {
                logger.error("스토리 저장 실패: 영향 받은 행의 수가 0입니다.");
                return false;
            }
        } catch (Exception e) {
            logger.error("스토리 저장 중 오류 발생", e);
            throw e;
        }
    }

    // 모든 스토리 가져오기
    public List<Story> getAllStories() {
        return storyDao.getAllStories();
    }

    // 스토리 번호로 스토리 가져오기
    public Story getStoryById(int stNum) {
        return storyDao.getStoryById(stNum);
    }

    // 스토리 삭제
    public boolean deleteStory(int stNum) {
        return storyDao.deleteStory(stNum) > 0;
    }

    // 신고 처리
    public boolean reportStory(int stNum, String reTypeCode, String reText, String userId) {
        return reportDao.insertReport(stNum, reTypeCode, reText, userId) > 0;
    }

    // 추천 처리
    public boolean recommendStory(int stNum, String userId) {
        // 플레이어가 이미 추천했는지 확인
        boolean hasRecommended = playerDao.hasRecommended(stNum, userId);
        if (hasRecommended) {
            return false;
        }
        // 추천 처리
        return playerDao.recommendStory(stNum, userId) > 0;
    }

    // 장면 및 선택지 데이터 파싱
    private List<Scene> parseScenesFromParameters(Map<String, String> allParams, int stNum) {
        List<Scene> scenes = new ArrayList<>();

        // sceneText_{sceneNum} 형태의 키를 찾아서 장면을 생성
        for (String key : allParams.keySet()) {
            if (key.startsWith("sceneText_")) {
                String sceneNumStr = key.substring("sceneText_".length());
                int sceneNum = Integer.parseInt(sceneNumStr);

                Scene scene = new Scene();
                scene.setStNum(stNum);
                scene.setScNum(sceneNum);
                scene.setScLevel(1); // 필요에 따라 레벨 설정
                scene.setScText(allParams.get(key));
                // 부모 장면 번호 설정
                int parentSceneNum = Integer.parseInt(allParams.getOrDefault("parentSceneNum_" + sceneNum, "0"));
                scene.setParentScNum(parentSceneNum);

                // 선택지 정보 수집
                List<Choice> choices = collectChoicesForScene(allParams, sceneNum);
                scene.setChoices(choices);

                // 삽화 이미지 파일명 설정 (필요한 경우)
                scene.setScIllus(allParams.get("sceneImageFileName_" + sceneNum));

                scenes.add(scene);
            }
        }

        return scenes;
    }

    private List<Choice> collectChoicesForScene(Map<String, String> allParams, int sceneNum) {
        List<Choice> choices = new ArrayList<>();

        for (String key : allParams.keySet()) {
            if (key.startsWith("choiceName_scene_" + sceneNum + "_choice_")) {
                String choiceKey = key.substring("choiceName_".length()); // "scene_1_choice_1" 형식
                int choiceNum = Integer.parseInt(choiceKey.substring(choiceKey.lastIndexOf("_") + 1));

                Choice choice = new Choice();
                choice.setSceneNum(sceneNum);
                choice.setChoiceNum(choiceNum);
                choice.setChoiceName(allParams.get(key));
                choice.setChoiceContent(allParams.get("choiceContent_" + choiceKey));
                choice.setMoney(Integer.parseInt(allParams.getOrDefault("choiceMoney_" + choiceKey, "0")));
                choice.setHp(Integer.parseInt(allParams.getOrDefault("choiceHP_" + choiceKey, "0")));

                choices.add(choice);
            }
        }

        return choices;
    }
}
