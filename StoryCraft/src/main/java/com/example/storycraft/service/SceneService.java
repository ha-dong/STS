package com.example.storycraft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.storycraft.dao.ChoiceDao;
import com.example.storycraft.dao.SceneDao;
import com.example.storycraft.dao.SceneRowMapper;
import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;

@Service
public class SceneService {

    @Autowired
    private SceneDao sceneDao;
    
    @Autowired
    private ChoiceDao choiceDao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Scene getFirstSceneByStory(int stNum) {
        return sceneDao.getFirstScene(stNum);
    }

    // SceneService.java에서 다음 장면을 가져오는 로직 수정
    public Scene getNextSceneByChoice(int choiceNum) {
        Integer nextSceneNum = choiceDao.getNextSceneNum(choiceNum);
        
        // 만약 nextSceneNum이 존재하지 않으면 null 반환
        if (nextSceneNum == null) {
            return null;
        }

        // nextSceneNum을 사용해 다음 장면 가져오기
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{nextSceneNum}, new SceneRowMapper());
    }
    
    public Scene getSceneByScNum(int scNum) {
        return sceneDao.getSceneByScNum(scNum);  // SceneDao에서 SC_NUM으로 장면 가져오기
    }
    
    public List<Choice> collectChoicesForScene(Map<String, String> allParams, int sceneNum) {
        List<Choice> choices = new ArrayList<>();

        for (String key : allParams.keySet()) {
            if (key.startsWith("choiceName_scene_" + sceneNum + "_choice_")) {
                String choiceKey = key.substring("choiceName_".length()); 
                int choiceNum = Integer.parseInt(choiceKey.substring(choiceKey.lastIndexOf("_") + 1));

                Choice choice = new Choice();
                choice.setScNum(sceneNum);
                choice.setChoiceNum(choiceNum);
                choice.setChoiceName(allParams.get(key));
                choice.setChoiceContent(allParams.get("choiceContent_" + choiceKey));
                choice.setMoney(Integer.parseInt(allParams.getOrDefault("choiceMoney_" + choiceKey, "0")));
                choice.setHp(Integer.parseInt(allParams.getOrDefault("choiceHP_" + choiceKey, "0")));

                // NEXT_SC_NUM 값을 설정하는 로직을 추가합니다.
                int nextSceneNum = Integer.parseInt(allParams.getOrDefault("nextSceneNum_" + choiceKey, "0"));
                choice.setNextScNum(nextSceneNum); // 여기서 설정합니다.

                choices.add(choice);
            }
        }

        return choices;
    }

	public List<Scene> getSceneByStNum(Integer stNum) {
		return sceneDao.getSceneByStNum(stNum);
	}
}
