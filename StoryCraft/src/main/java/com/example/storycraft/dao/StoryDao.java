package com.example.storycraft.dao;

import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class StoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 스토리 저장
    public int insertStory(Story story) {
        String sql = "INSERT INTO STORY (ST_NUM, ST_TITLE, ST_CRDATE, ST_EDDATE, ST_VIEWNUM, ST_TYPECODE, ST_GENRECODE, " +
                "ST_SUGNUM, ST_COVER, ST_DSTATUS, U_ID, END_CODE) " +
                "VALUES (SEQ_STORY.NEXTVAL, ?, SYSDATE, NULL, 0, ?, ?, 0, ?, 'N', ?, ?)";
        return jdbcTemplate.update(sql,
                story.getStTitle(),
                story.getStTypecode(),
                story.getStGenrecode(),
                story.getStCover(),
                story.getuId(),
                story.getEndCode()
        );
    }

    // 방금 삽입된 스토리의 ST_NUM 가져오기
    public int getLastInsertedStNum() {
        String sql = "SELECT SEQ_STORY.CURRVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 모든 스토리 가져오기
    public List<Story> getAllStories() {
        String sql = "SELECT * FROM STORY WHERE ST_DSTATUS = 'N'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapStory(rs));
    }

    // 스토리 번호로 스토리 가져오기
    public Story getStoryById(int stNum) {
        String sql = "SELECT * FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{stNum}, (rs, rowNum) -> mapStory(rs));
    }

    // 스토리 삭제
    public int deleteStory(int stNum) {
        String sql = "DELETE FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // Story 객체로 매핑하는 메서드
    private Story mapStory(ResultSet rs) throws SQLException {
        Story story = new Story();
        story.setStNum(rs.getInt("ST_NUM"));
        story.setStTitle(rs.getString("ST_TITLE"));
        story.setStCrdate(rs.getTimestamp("ST_CRDATE"));
        story.setStEddate(rs.getTimestamp("ST_EDDATE"));
        story.setStViewnum(rs.getInt("ST_VIEWNUM"));
        story.setStTypecode(rs.getString("ST_TYPECODE"));
        story.setStGenrecode(rs.getString("ST_GENRECODE"));
        story.setStSugnum(rs.getInt("ST_SUGNUM"));
        story.setStCover(rs.getString("ST_COVER"));
        story.setStDstatus(rs.getString("ST_DSTATUS"));
        story.setStDreason(rs.getString("ST_DREASON"));
        story.setStDdate(rs.getTimestamp("ST_DDATE"));
        story.setuId(rs.getString("U_ID"));
        story.setEndCode(rs.getString("END_CODE")); // 엔딩 코드 설정
        return story;
    }
}
