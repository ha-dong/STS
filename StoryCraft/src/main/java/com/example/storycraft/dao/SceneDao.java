// SceneDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class SceneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChoiceDao choiceDao;

    // 장면 저장
    public int insertScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, SC_TEXT, SC_ILLUS, MONEY, HP) " +
                     "VALUES (SEQ_SCENE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                scene.getStNum(),
                scene.getParentScNum(),
                scene.getScLevel(),
                scene.getScText(),
                scene.getScIllus(),
                scene.getMoney(),
                scene.getHp()
        );

        // 방금 삽입된 SC_NUM 가져오기
        int scNum = jdbcTemplate.queryForObject("SELECT SEQ_SCENE.CURRVAL FROM DUAL", Integer.class);

        // 선택지 저장
        for (Choice choice : scene.getChoices()) {
            choiceDao.insertChoice(choice, scNum, scene.getStNum(), scene.getParentScNum(), scene.getScLevel());
        }

        return 1;
    }
    
    public Scene getFirstScene(int stNum) {
        String sql = "SELECT * FROM SCENE WHERE ST_NUM = ? AND PARENT_SC_NUM = 0 ORDER BY SC_LEVEL ASC";
        Scene scene = jdbcTemplate.queryForObject(sql, new Object[]{stNum}, new SceneRowMapper());
        
        // 선택지 추가
        List<Choice> choices = getChoicesForScene(scene.getScNum());
        scene.setChoices(choices);
        
        return scene;
    }

    public Scene getNextScene(int choiceNum) {
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = (SELECT PARENT_SC_NUM FROM CHOICE WHERE CHOICE_NUM = ? AND ROWNUM = 1)";
        Scene scene = jdbcTemplate.queryForObject(sql, new Object[]{choiceNum}, new SceneRowMapper());

        // 선택지 추가
        List<Choice> choices = getChoicesForScene(scene.getScNum());
        scene.setChoices(choices);

        return scene;
    }
    
    public Scene getSceneByScNum(int scNum) {
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = ?";
        List<Scene> scenes = jdbcTemplate.query(sql, new Object[]{scNum}, new SceneRowMapper());
        
        if (scenes.isEmpty()) {
            return null; // 데이터가 없을 경우 null 반환
        }
        return scenes.get(0); // 데이터가 있으면 첫 번째 결과 반환
    }
    
    // 선택지 가져오는 메서드 추가
    private List<Choice> getChoicesForScene(int scNum) {
        String sql = "SELECT * FROM CHOICE WHERE SC_NUM = ?";
        return jdbcTemplate.query(sql, new Object[]{scNum}, new ChoiceRowMapper());
    }

    // 장면 삭제 by 스토리 번호
    public int deleteScenesByStory(int stNum) {
        String sql = "DELETE FROM SCENE WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }
}
