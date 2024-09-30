package com.example.storycraft.dao;

import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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

    // 스토리 수정
    public int updateStory(Story story) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE STORY SET ST_TITLE = ?, ST_GENRECODE = ?, ST_TYPECODE = ?, ");
        List<Object> params = new ArrayList<>();
        params.add(story.getStTitle());
        params.add(story.getStGenrecode());
        params.add(story.getStTypecode());

        if (story.getStCover() != null) {
            sqlBuilder.append("ST_COVER = ?, ");
            params.add(story.getStCover());
        }

        sqlBuilder.append("END_CODE = ? WHERE ST_NUM = ?");
        params.add(story.getEndCode());
        params.add(story.getStNum());

        return jdbcTemplate.update(sqlBuilder.toString(), params.toArray());
    }

    // 방금 삽입된 스토리의 ST_NUM 가져오기
    public int getLastInsertedStNum() {
        String sql = "SELECT SEQ_STORY.CURRVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 전체 스토리 가져오기 필터링 및 정렬
    public List<Story> getAllStoriesFilteredAndSorted(String genre, String sort) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM STORY WHERE ST_DSTATUS = 'N'");
        List<Object> params = new ArrayList<>();

        if (genre != null && !genre.isEmpty()) {
            sqlBuilder.append(" AND ST_GENRECODE = ?");
            params.add(genre);
        }

        if (sort != null) {
            if (sort.equals("recent")) {
                sqlBuilder.append(" ORDER BY ST_CRDATE DESC");
            } else if (sort.equals("popular")) {
                sqlBuilder.append(" ORDER BY ST_SUGNUM DESC");
            }
        } else {
            sqlBuilder.append(" ORDER BY ST_CRDATE DESC");
        }

        return jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), (rs, rowNum) -> mapStory(rs));
    }

    // 모든 스토리 가져오기
    public List<Story> getAllStories() {
        String sql = "SELECT * FROM STORY WHERE ST_DSTATUS = 'N' ORDER BY ST_CRDATE DESC";
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

    // 추천 수 증가
    public int incrementRecommendation(int stNum) {
        String sql = "UPDATE STORY SET ST_SUGNUM = ST_SUGNUM + 1 WHERE ST_NUM = ?";
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

        // 추가: 장면 정보 로드 (필요 시 추가 구현)
        // 예: story.setScenes(sceneDao.getScenesByStory(story.getStNum()));

        return story;
    }
}
