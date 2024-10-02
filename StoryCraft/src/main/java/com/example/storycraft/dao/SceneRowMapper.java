package com.example.storycraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.storycraft.model.Scene;

public class SceneRowMapper implements RowMapper<Scene> {
    @Override
    public Scene mapRow(ResultSet rs, int rowNum) throws SQLException {
        Scene scene = new Scene();
        scene.setScNum(rs.getInt("SC_NUM"));
        scene.setStNum(rs.getInt("ST_NUM"));
        scene.setParentScNum(rs.getInt("PARENT_SC_NUM"));
        scene.setScLevel(rs.getInt("SC_LEVEL"));
        scene.setScText(rs.getString("SC_TEXT"));
        scene.setScIllus(rs.getString("SC_ILLUS"));
        scene.setMoney(rs.getInt("MONEY"));
        scene.setHp(rs.getInt("HP"));
        return scene;
    }
}

