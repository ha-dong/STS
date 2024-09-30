package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteCommentsByStory(int stNum) {
        String sql = "DELETE FROM COMMENTS WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }
}
