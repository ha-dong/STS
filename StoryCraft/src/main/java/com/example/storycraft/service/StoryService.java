package com.example.storycraft.service;

import com.example.storycraft.dao.*;
import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;
import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ChoiceDao choiceDao;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private InquiryDao inquiryDao;

    // 전체 스토리 저장
    @Transactional
    public boolean saveFullStory(String title, String genre, String coverFileName, int initialMoney, int initialHP, String userId, Map<String, String> allParams) {
        try {
            // 스토리 저장
            Story story = new Story();
            story.setStTitle(title);
            story.setStGenrecode(genre);
            story.setStTypecode("CST-02");
            story.setStCover(coverFileName);
            story.setuId(userId != null ? userId : "subo"); // userId가 null이면 "subo"로 설정
            story.setEndCode(allParams.get("endCode")); // 엔딩 코드 설정
            story.setInitialMoney(initialMoney);
            story.setInitialHP(initialHP);

            int result = storyDao.insertStory(story);

            if (result > 0) {
                // 방금 삽입된 스토리의 ST_NUM 가져오기
                int stNum = storyDao.getLastInsertedStNum();

                // 장면 및 선택지 데이터 파싱
                List<Scene> scenes = parseScenesFromParameters(allParams, stNum);

                // 임시 장면 번호와 실제 SC_NUM 매핑을 위한 맵
                Map<Integer, Integer> sceneNumMap = new HashMap<>();

                // 모든 장면을 먼저 저장하여 SC_NUM 확보
                for (Scene scene : scenes) {
                    sceneDao.insertScene(scene);
                    int scNum = sceneDao.getLastInsertedScNum();
                    sceneNumMap.put(scene.getTempSceneNum(), scNum); // tempSceneNum은 parseScenesFromParameters에서 설정
                    scene.setScNum(scNum); // 실제 SC_NUM 설정
                }

                // 선택지 저장
                for (Scene scene : scenes) {
                    for (Choice choice : scene.getChoices()) {
                        // NEXT_SC_NUM 설정
                        Integer tempNextSceneNum = choice.getNextScNum(); // 임시로 사용한 다음 장면 번호
                        Integer nextScNum = tempNextSceneNum != null ? sceneNumMap.get(tempNextSceneNum) : null; // 실제 SC_NUM 가져오기
                        choice.setNextScNum(nextScNum);

                        // 선택지 저장
                        choiceDao.insertChoice(choice, scene);
                    }
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
    
    // 관리자용 모든 스토리 가져오기
    public List<Story> getAllStoriesForAdmin(String orderBy, String orderDirection) {
        return storyDao.getAllStoriesForAdmin(orderBy, orderDirection);
    }

    // 신고 수 기준으로 스토리 검색
    public List<Story> getStoriesByReportCount(int minReports, int maxReports) {
        return storyDao.getStoriesByReportCount(minReports, maxReports);
    }

    // 관리자용 스토리 삭제
    public boolean deleteStoryByAdmin(int stNum) {
        // 필요한 경우 관련된 데이터 삭제 로직 추가
        return storyDao.deleteStoryByAdmin(stNum) > 0;
    }


    // 스토리 수정
    @Transactional
    public boolean updateFullStory(int stNum, String title, String genre, String coverFileName, int initialMoney, int initialHP,
                                   String userId, Map<String, String> allParams) {
        try {
            // 스토리 업데이트
            Story story = new Story();
            story.setStNum(stNum);
            story.setStTitle(title);
            story.setStGenrecode(genre);
            story.setStTypecode("CST-02");
            if (coverFileName != null) {
                story.setStCover(coverFileName);
            }
            story.setuId(userId != null ? userId : "subo"); // userId가 null이면 "subo"로 설정
            story.setEndCode(allParams.get("endCode")); // 엔딩 코드 설정
            story.setInitialMoney(initialMoney);
            story.setInitialHP(initialHP);

            int result = storyDao.updateStory(story);;

            if (result > 0) {
                // 기존 장면 삭제
                sceneDao.deleteScenesByStory(stNum);

                // 장면 및 선택지 데이터 파싱
                List<Scene> scenes = parseScenesFromParameters(allParams, stNum);

                // 임시 장면 번호와 실제 SC_NUM 매핑을 위한 맵
                Map<Integer, Integer> sceneNumMap = new HashMap<>();

                // 모든 장면을 먼저 저장하여 SC_NUM 확보
                for (Scene scene : scenes) {
                    sceneDao.insertScene(scene);
                    int scNum = sceneDao.getLastInsertedScNum();
                    sceneNumMap.put(scene.getTempSceneNum(), scNum); // tempSceneNum은 parseScenesFromParameters에서 설정
                    scene.setScNum(scNum); // 실제 SC_NUM 설정
                }

                // 선택지 저장
                for (Scene scene : scenes) {
                    for (Choice choice : scene.getChoices()) {
                        // NEXT_SC_NUM 설정
                        Integer tempNextSceneNum = choice.getNextScNum(); // 임시로 사용한 다음 장면 번호
                        Integer nextScNum = tempNextSceneNum != null ? sceneNumMap.get(tempNextSceneNum) : null; // 실제 SC_NUM 가져오기
                        choice.setNextScNum(nextScNum);

                        // 선택지 저장
                        choiceDao.insertChoice(choice, scene);
                    }
                }

                return true;
            } else {
                logger.error("스토리 수정 실패: 영향 받은 행의 수가 0입니다.");
                return false;
            }
        } catch (Exception e) {
            logger.error("스토리 수정 중 오류 발생", e);
            throw e;
        }
    }

    // 전체 스토리 가져오기 필터링 및 정렬
    public List<Story> getAllStoriesFilteredAndSorted(String genre, String sort) {
        return storyDao.getAllStoriesFilteredAndSorted(genre, sort);
    }

    // 모든 스토리 가져오기
    public List<Story> getAllStories() {
        return storyDao.getAllStories();
    }

    // 스토리 번호로 스토리 가져오기
    public Story getStoryById(int stNum) {
        return storyDao.getStoryById(stNum);
    }
    
    public void incrementViewCount(int stNum) {
        storyDao.incrementViewCount(stNum);
    }


    // 스토리 삭제
    @Transactional
    public boolean deleteStory(int stNum) {
        try {
            // 자식 테이블의 레코드 삭제
            reportDao.deleteReportsByStory(stNum);
            playerDao.deletePlayersByStory(stNum);

            // SCENE 및 CHOICE는 외래 키에 ON DELETE CASCADE가 설정되어 있으므로 STORY 삭제 시 자동으로 삭제됨

            // STORY 삭제
            return storyDao.deleteStory(stNum) > 0;
        } catch (Exception e) {
            logger.error("스토리 삭제 중 오류 발생", e);
            return false;
        }
    }

    // 신고 처리
    public boolean reportStory(int stNum, String reTypeCode, String reText, String userId, String imagePath) {
        int result = reportDao.insertReport(stNum, reTypeCode, reText, userId, imagePath);
        if (result > 0) {
            // 신고 수 증가
            storyDao.incrementReportCount(stNum);
            return true;
        } else {
            return false;
        }
    }

    // 추천 처리
    public boolean recommendStory(int stNum, String userId) {
        // 플레이어가 이미 추천했는지 확인
        boolean hasRecommended = playerDao.hasRecommended(stNum, userId);
        if (hasRecommended) {
            return false;
        }
        // 추천이 자신의 스토리인지 확인
        Story story = storyDao.getStoryById(stNum);
        if (story != null && userId.equals(story.getuId())) {
            return false;
        }
        // 플레이어 레코드가 있는지 확인
        boolean hasPlayerRecord = playerDao.hasPlayerRecord(stNum, userId);
        if (!hasPlayerRecord) {
            // 플레이어 레코드 삽입
            playerDao.insertPlayer(stNum, userId);
        }
        // 추천 처리
        boolean updated = playerDao.recommendStory(stNum, userId);
        if (updated) {
            // 추천 수 증가
            storyDao.incrementRecommendation(stNum);
            return true;
        }
        return false;
    }

    // 장면 및 선택지 데이터 파싱
    private List<Scene> parseScenesFromParameters(Map<String, String> allParams, int stNum) {
        List<Scene> scenes = new ArrayList<>();

        for (String key : allParams.keySet()) {
            if (key.startsWith("sceneText_")) {
                String sceneNumStr = key.substring("sceneText_".length());
                int sceneNum = Integer.parseInt(sceneNumStr);

                Scene scene = new Scene();
                scene.setStNum(stNum);
                scene.setTempSceneNum(sceneNum); // 임시 장면 번호 설정
                scene.setScLevel(1);
                scene.setScIllus(allParams.get("sceneImageFileName_" + sceneNumStr));
                scene.setScText(allParams.get(key));

                // 부모 장면 번호 설정
                int parentSceneNum = Integer.parseInt(allParams.getOrDefault("parentSceneNum_" + sceneNumStr, "0"));
                scene.setParentScNum(parentSceneNum);

                // 선택지 정보 수집
                List<Choice> choices = collectChoicesForScene(allParams, sceneNum);
                scene.setChoices(choices);

                scenes.add(scene);
            }
        }

        return scenes;
    }

    private List<Choice> collectChoicesForScene(Map<String, String> allParams, int sceneNum) {
        List<Choice> choices = new ArrayList<>();

        for (String key : allParams.keySet()) {
            if (key.startsWith("choiceName_scene_" + sceneNum + "_choice_")) {
                String choiceKey = key.substring("choiceName_".length()); // "scene_{sceneNum}_choice_{choiceNum}"
                String[] parts = choiceKey.split("_");
                int choiceNum = Integer.parseInt(parts[3]); // parts[3]은 choiceNum

                Choice choice = new Choice();
                // choice.setChoiceNum(choiceNum); // choiceNum은 DB에서 자동 생성되므로 설정하지 않음
                String choiceName = allParams.get(key);
                choice.setChoiceName(choiceName);
                choice.setChoiceContent(choiceName); // 선택지 이름 데이터를 choiceContent에도 저장
                choice.setMoney(Integer.parseInt(allParams.getOrDefault("choiceMoney_" + choiceKey, "0")));
                choice.setHp(Integer.parseInt(allParams.getOrDefault("choiceHP_" + choiceKey, "0")));

                // NEXT_SC_NUM 값을 설정
                int nextSceneNum = Integer.parseInt(allParams.getOrDefault("nextSceneNum_" + choiceKey, "-1"));
                choice.setNextScNum(nextSceneNum == -1 ? null : nextSceneNum);

                choices.add(choice);
            }
        }

        return choices;
    }

    // 장르 리스트 가져오기 (예시로 하드코딩)
    public List<Map<String, String>> getGenreList() {
        List<Map<String, String>> genreList = new ArrayList<>();
        genreList.add(createCodeMap("CG-01", "판타지"));
        genreList.add(createCodeMap("CG-02", "스릴러"));
        genreList.add(createCodeMap("CG-03", "코미디"));
        genreList.add(createCodeMap("CG-04", "SF"));
        genreList.add(createCodeMap("CG-05", "미스터리"));
        genreList.add(createCodeMap("CG-06", "로맨스"));
        genreList.add(createCodeMap("CG-07", "호러"));
        genreList.add(createCodeMap("CG-08", "무협"));
        genreList.add(createCodeMap("CG-09", "드라마"));
        genreList.add(createCodeMap("CG-10", "서부"));
        genreList.add(createCodeMap("CG-11", "역사"));
        return genreList;
    }

    private Map<String, String> createCodeMap(String code, String codeName) {
        Map<String, String> map = new HashMap<>();
        map.put("CODE", code);
        map.put("CODE_NAME", codeName);
        return map;
    }
}
