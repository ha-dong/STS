package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Scene;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SceneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 씬 저장
    public void saveScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, PLAYER_NUM, SC_TEXT, SC_CHOICES, MONEY, HP, END_CODE, SOUND_CODE) " +
                     "VALUES (SCENE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, scene.getPlayerNum(), scene.getScText(), scene.getScChoices(),
                scene.getMoney(), scene.getHp(), scene.getEndCode(), scene.getSoundCode());
    }

    // 씬 ID로 특정 씬 조회
    public Scene findSceneById(Long scNum) {
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scNum}, this::mapRowToScene);
    }

    // 모든 씬 조회
    public List<Scene> findAllScenes() {
        String sql = "SELECT * FROM SCENE";
        return jdbcTemplate.query(sql, this::mapRowToScene);
    }

    // 씬 업데이트
    public void updateScene(Scene scene) {
        String sql = "UPDATE SCENE SET PLAYER_NUM = ?, SC_TEXT = ?, SC_CHOICES = ?, MONEY = ?, HP = ?, END_CODE = ?, SOUND_CODE = ? " +
                     "WHERE SC_NUM = ?";

        jdbcTemplate.update(sql, scene.getPlayerNum(), scene.getScText(), scene.getScChoices(),
                scene.getMoney(), scene.getHp(), scene.getEndCode(), scene.getSoundCode(), scene.getScNum());
    }

    // 씬 삭제
    public void deleteScene(Long scNum) {
        String sql = "DELETE FROM SCENE WHERE SC_NUM = ?";
        jdbcTemplate.update(sql, scNum);
    }

    // RowMapper 함수: ResultSet을 Scene 객체로 변환
    private Scene mapRowToScene(ResultSet rs, int rowNum) throws SQLException {
        Scene scene = new Scene();
        scene.setScNum(rs.getLong("SC_NUM"));
        scene.setPlayerNum(rs.getLong("PLAYER_NUM"));
        scene.setScText(rs.getString("SC_TEXT"));
        scene.setScChoices(rs.getString("SC_CHOICES"));
        scene.setMoney(rs.getInt("MONEY"));
        scene.setHp(rs.getInt("HP"));
        scene.setEndCode(rs.getString("END_CODE"));
        scene.setSoundCode(rs.getString("SOUND_CODE"));
        return scene;
    }
}
