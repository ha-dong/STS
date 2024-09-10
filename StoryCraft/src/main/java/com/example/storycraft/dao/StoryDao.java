package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Story;

import java.sql.Timestamp;

@Repository
public class StoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 스토리 저장
    public void saveStory(Story story) {
        String sql = "INSERT INTO STORY (ST_NUM, ST_TITLE, ST_CRDATE, ST_TYPECODE, ST_GENRECODE, U_ID) " +
                     "VALUES (STORY_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, story.getStTitle(), new Timestamp(System.currentTimeMillis()),
                            story.getStTypecode(), story.getStGenrecode(), story.getUId());
    }

    // 스토리 ID로 특정 스토리 조회
    public Story findStoryById(Long stNum) {
        String sql = "SELECT * FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{stNum}, (rs, rowNum) -> {
            Story story = new Story();
            story.setStNum(rs.getLong("ST_NUM"));
            story.setStTitle(rs.getString("ST_TITLE"));
            story.setStCrdate(rs.getTimestamp("ST_CRDATE"));
            story.setStTypecode(rs.getString("ST_TYPECODE"));
            story.setStGenrecode(rs.getString("ST_GENRECODE"));
            story.setUId(rs.getString("U_ID"));
            return story;
        });
    }

    // 기타 필요한 CRUD 작업 추가 가능 (업데이트, 삭제 등)
}
