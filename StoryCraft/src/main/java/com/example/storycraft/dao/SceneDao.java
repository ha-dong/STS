// SceneDao.java

package com.example.storycraft.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;

@Repository
public class SceneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChoiceDao choiceDao;

    public int insertScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, SC_TEXT, SC_ILLUS, MONEY, HP) " +
                     "VALUES (SEQ_SCENE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                scene.getStNum(),
                scene.getParentScNum(),
                scene.getScLevel(),
                scene.getScText(),
                scene.getScIllus(),
                scene.getMoney(),
                scene.getHp()
        );
    }
    
    public List<Scene> getNextSceneList(int stNum, int parentScNum) {
    	int nextParentScNum = parentScNum + 1;
    	String sql = "SELECT * FROM SCENE WHERE ST_NUM = ? AND PARENT_SC_NUM = ? ORDER BY SC_LEVEL ASC";
        List<Scene> scenes = jdbcTemplate.query(sql, new Object[]{stNum, parentScNum}, new SceneRowMapper());
        return scenes;
    }
    
    public Scene getFirstScene(int stNum) {
    	 String sql = "SELECT * FROM (SELECT * FROM SCENE WHERE ST_NUM = ? AND PARENT_SC_NUM = 0 ORDER BY SC_LEVEL ASC) WHERE ROWNUM = 1";
    	    Scene scene = jdbcTemplate.queryForObject(sql, new Object[]{stNum}, new SceneRowMapper());
        
        // 다음 씬 조회
        List<Scene> scenes = getNextSceneList(scene.getScNum(), scene.getParentScNum());
        
        // 선택지 추가
        List<Choice> choices = getChoicesForScene(scene.getScNum());
        
        // 현재 초이스 객체의 씬 번호와 씬 번호가 동일하면
        for (Choice choice : choices) {
        	
		}
        
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
        // 씬에서 초이스 가져올 때 순서 뒤집힘 처리
        // AS-IS) 1-2, 1-1
        // TO-BE) 1-1, 1-2
    	// ORDER BY 처리
        String sql = "SELECT * FROM CHOICE WHERE SC_NUM = ? ORDER BY CHOICE_NUM ASC";
        return jdbcTemplate.query(sql, new Object[]{scNum}, new ChoiceRowMapper());
    }

    // 장면 삭제 by 스토리 번호
    public int deleteScenesByStory(int stNum) {
        String sql = "DELETE FROM SCENE WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

	public List<Scene> getSceneByStNum(Integer stNum) {
		 String sql = "SELECT * FROM SCENE WHERE ST_NUM = ?";
	     return jdbcTemplate.query(sql, new Object[]{stNum}, new SceneRowMapper());
	}
	
	public int getLastInsertedScNum() {
	    String sql = "SELECT SEQ_SCENE.CURRVAL FROM DUAL";
	    return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
