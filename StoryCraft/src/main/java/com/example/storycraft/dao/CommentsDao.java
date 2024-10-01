package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 특정 문의 번호에 해당하는 모든 댓글 삭제
    public void deleteCommentsByInquiry(int inqNum) {
        String sql = "DELETE FROM COMMENTS WHERE INQ_NUM = ?";
        jdbcTemplate.update(sql, inqNum);
    }
}
