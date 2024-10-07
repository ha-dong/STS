package com.example.storycraft.dao;

import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class StoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 스토리 저장
    public int insertStory(Story story) {
        String sql = "INSERT INTO STORY (ST_NUM, ST_TITLE, ST_CRDATE, ST_EDDATE, ST_VIEWNUM, ST_TYPECODE, ST_GENRECODE, " +
                "ST_SUGNUM, ST_COVER, ST_DSTATUS, U_ID, END_CODE, INITIAL_MONEY, INITIAL_HP) " +
                "VALUES (SEQ_STORY.NEXTVAL, ?, SYSDATE, NULL, 0, ?, ?, 0, ?, 'N', ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                story.getStTitle(),
                story.getStTypecode(),
                story.getStGenrecode(),
                story.getStCover(),
                story.getuId(),
                story.getEndCode(),
                story.getInitialMoney(),
                story.getInitialHP()
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

        sqlBuilder.append("END_CODE = ?, INITIAL_MONEY = ?, INITIAL_HP = ? WHERE ST_NUM = ?");
        params.add(story.getEndCode());
        params.add(story.getInitialMoney());
        params.add(story.getInitialHP());
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

    // 선택지 삭제
    public int deleteChoicesByStory(int stNum) {
        String sql = "DELETE FROM CHOICE WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // 장면 삭제
    public int deleteScenesByStory(int stNum) {
        String sql = "DELETE FROM SCENE WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // 플레이어 데이터 삭제
    public int deletePlayersByStory(int stNum) {
        String sql = "DELETE FROM PLAYER WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // 신고 데이터 삭제
    public int deleteReportsByStory(int stNum) {
        String sql = "DELETE FROM REPORT WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }
    
    public void incrementViewCount(int stNum) {
        String sql = "UPDATE STORY SET ST_VIEWNUM = NVL(ST_VIEWNUM, 0) + 1 WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }


    // 스토리 삭제 (모든 종속 데이터 삭제 후)
    public int deleteStory(int stNum) {
        String sql = "DELETE FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // 전체 삭제 처리 메소드
    public boolean deleteFullStory(int stNum) {
        // 삭제 순서대로 실행
        deleteChoicesByStory(stNum);
        deleteScenesByStory(stNum);
        deletePlayersByStory(stNum);
        deleteReportsByStory(stNum);
        return deleteStory(stNum) > 0; // 성공 시 true 반환
    }
    
    // 모든 스토리 가져오기 (관리자용)
    public List<Story> getAllStoriesForAdmin(String orderBy, String orderDirection) {
        String sql = "SELECT * FROM STORY ORDER BY " + orderBy + " " + orderDirection;
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapStory(rs));
    }
    
    // 신고 수 기준으로 스토리 검색
    public List<Story> getStoriesByReportCount(int minReports, int maxReports) {
        String sql = "SELECT * FROM STORY WHERE ST_RENUM BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{minReports, maxReports}, (rs, rowNum) -> mapStory(rs));
    }
    
    // 스토리 삭제 (관리자용)
    public int deleteStoryByAdmin(int stNum) {
        String sql = "DELETE FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.update(sql, stNum);
    }

    // 신고 수 증가 메서드
    public void incrementReportCount(int stNum) {
        String sql = "UPDATE STORY SET ST_RENUM = NVL(ST_RENUM, 0) + 1 WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }

    // 추천 수 증가 메서드 추가
    public void incrementRecommendation(int stNum) {
        String sql = "UPDATE STORY SET ST_SUGNUM = ST_SUGNUM + 1 WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
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
        story.setInitialMoney(rs.getInt("INITIAL_MONEY")); // 초기 돈 설정
        story.setInitialHP(rs.getInt("INITIAL_HP"));       // 초기 체력 설정
        story.setStRenum(rs.getInt("ST_RENUM")); // 신고 수 설정 추가

        return story;
    }
}
