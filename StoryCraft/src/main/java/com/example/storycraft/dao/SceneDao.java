package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Scene;

@Repository
public class SceneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 씬 저장
    public void saveScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, PLAYER_NUM, SC_TEXT, SC_CHOICES) " +
                     "VALUES (SCENE_SEQ.NEXTVAL, ?, ?, ?)";

        jdbcTemplate.update(sql, scene.getPlayerNum(), scene.getScText(), scene.getScChoices());
    }

    // 씬 ID로 특정 씬 조회
    public Scene findSceneById(Long scNum) {
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scNum}, (rs, rowNum) -> {
            Scene scene = new Scene();
            scene.setScNum(rs.getLong("SC_NUM"));
            scene.setPlayerNum(rs.getLong("PLAYER_NUM"));
            scene.setScText(rs.getString("SC_TEXT"));
            scene.setScChoices(rs.getString("SC_CHOICES"));
            return scene;
        });
    }

    // 기타 필요한 CRUD 작업 추가 가능 (업데이트, 삭제 등)
}
