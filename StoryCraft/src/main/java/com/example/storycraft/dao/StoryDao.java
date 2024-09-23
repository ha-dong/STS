package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
        return jdbcTemplate.queryForObject(sql, new Object[]{stNum}, this::mapRowToStory);
    }

    // 모든 스토리 조회
    public List<Story> findAllStories() {
        String sql = "SELECT * FROM STORY";
        return jdbcTemplate.query(sql, this::mapRowToStory);
    }

    // 스토리 업데이트
    public void updateStory(Story story) {
        String sql = "UPDATE STORY SET ST_TITLE = ?, ST_TYPECODE = ?, ST_GENRECODE = ?, ST_CRDATE = ? " +
                     "WHERE ST_NUM = ?";

        jdbcTemplate.update(sql, story.getStTitle(), story.getStTypecode(), story.getStGenrecode(),
                new Timestamp(System.currentTimeMillis()), story.getStNum());
    }

    // 스토리 삭제
    public void deleteStory(Long stNum) {
        String sql = "DELETE FROM STORY WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }

    // 스토리와 씬을 함께 저장
    public void saveStoryAndScene(Story story) {
        // 스토리 저장
        saveStory(story);
        // 씬 저장 로직 추가 가능 (만약 story와 연결된 씬이 있다면)
    }

    // RowMapper 함수: ResultSet을 Story 객체로 변환
    private Story mapRowToStory(ResultSet rs, int rowNum) throws SQLException {
        Story story = new Story();
        story.setStNum(rs.getLong("ST_NUM"));
        story.setStTitle(rs.getString("ST_TITLE"));
        story.setStCrdate(rs.getTimestamp("ST_CRDATE"));
        story.setStTypecode(rs.getString("ST_TYPECODE"));
        story.setStGenrecode(rs.getString("ST_GENRECODE"));
        story.setUId(rs.getString("U_ID"));
        return story;
    }
}
