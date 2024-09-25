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

    /**
     * 씬 저장
     */
    public void saveScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, PLAYER_NUM, SC_TEXT, SC_CHOICES, SC_ILLUS, MONEY, HP, END_CODE, SOUND_CODE) " +
                     "VALUES (SEQ_SCENE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                scene.getStNum(),
                scene.getParentScNum(),
                scene.getScLevel(),
                scene.getPlayerNum(),
                scene.getScText(),
                scene.getScChoices(),
                scene.getScIllus(),
                scene.getMoney(),
                scene.getHp(),
                scene.getEndCode(),
                scene.getSoundCode()
        );
    }

    /**
     * 씬 ID로 특정 씬 조회
     */
    public Scene findSceneById(Long scNum) {
        String sql = "SELECT * FROM SCENE WHERE SC_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scNum}, (rs, rowNum) -> {
            Scene scene = new Scene();
            scene.setScNum(rs.getLong("SC_NUM"));
            scene.setStNum(rs.getLong("ST_NUM"));
            scene.setParentScNum(rs.getLong("PARENT_SC_NUM"));
            scene.setScLevel(rs.getLong("SC_LEVEL"));
            scene.setPlayerNum(rs.getLong("PLAYER_NUM"));
            scene.setScText(rs.getString("SC_TEXT"));
            scene.setScChoices(rs.getString("SC_CHOICES"));
            scene.setScIllus(rs.getString("SC_ILLUS"));
            scene.setMoney(rs.getLong("MONEY"));
            scene.setHp(rs.getLong("HP"));
            scene.setEndCode(rs.getString("END_CODE"));
            scene.setSoundCode(rs.getString("SOUND_CODE"));
            return scene;
        });
    }

    /**
     * 스토리에 속한 모든 씬 조회
     */
    public List<Scene> findAllScenesByStory(Long stNum) {
        String sql = "SELECT * FROM SCENE WHERE ST_NUM = ?";
        return jdbcTemplate.query(sql, new Object[]{stNum}, (rs, rowNum) -> {
            Scene scene = new Scene();
            scene.setScNum(rs.getLong("SC_NUM"));
            scene.setStNum(rs.getLong("ST_NUM"));
            scene.setParentScNum(rs.getLong("PARENT_SC_NUM"));
            scene.setScLevel(rs.getLong("SC_LEVEL"));
            scene.setPlayerNum(rs.getLong("PLAYER_NUM"));
            scene.setScText(rs.getString("SC_TEXT"));
            scene.setScChoices(rs.getString("SC_CHOICES"));
            scene.setScIllus(rs.getString("SC_ILLUS"));
            scene.setMoney(rs.getLong("MONEY"));
            scene.setHp(rs.getLong("HP"));
            scene.setEndCode(rs.getString("END_CODE"));
            scene.setSoundCode(rs.getString("SOUND_CODE"));
            return scene;
        });
    }

    /**
     * 씬 업데이트
     */
    public void updateScene(Scene scene) {
        String sql = "UPDATE SCENE SET SC_TEXT = ?, SC_CHOICES = ?, SC_ILLUS = ?, MONEY = ?, HP = ?, END_CODE = ?, SOUND_CODE = ? " +
                     "WHERE SC_NUM = ?";

        jdbcTemplate.update(sql,
                scene.getScText(),
                scene.getScChoices(),
                scene.getScIllus(),
                scene.getMoney(),
                scene.getHp(),
                scene.getEndCode(),
                scene.getSoundCode(),
                scene.getScNum()
        );
    }

    /**
     * 씬 삭제
     */
    public boolean deleteScene(Long scNum) {
        String sql = "DELETE FROM SCENE WHERE SC_NUM = ?";
        int rows = jdbcTemplate.update(sql, scNum);
        return rows > 0;
    }
}
