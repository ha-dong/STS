package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasRecommended(int stNum, String userId) {
        String sql = "SELECT COUNT(*) FROM PLAYER WHERE ST_NUM = ? AND U_ID = ? AND RECOMMENDED = 'Y'";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{stNum, userId}, Integer.class);
        return count > 0;
    }

    public int recommendStory(int stNum, String userId) {
        String sql = "UPDATE PLAYER SET RECOMMENDED = 'Y' WHERE ST_NUM = ? AND U_ID = ?";
        return jdbcTemplate.update(sql, stNum, userId);
    }
}
