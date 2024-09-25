package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class StoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 스토리 저장 메서드
    public void saveStory(Story story) {
        String sql = "INSERT INTO STORY (ST_NUM, ST_TITLE, ST_CRDATE, ST_EDDATE, ST_VIEWNUM, ST_TYPECODE, ST_GENRECODE, ST_SUGNUM, ST_COVER, ST_DSTATUS, ST_DREASON, ST_DDATE, U_ID, ST_CONTENT) " +
                     "VALUES (STORY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, 
            story.getStTitle(),
            new Timestamp(System.currentTimeMillis()), // ST_CRDATE
            null, // ST_EDDATE
            0L, // ST_VIEWNUM 초기값
            "CST-02", // ST_TYPECODE (회원스토리)
            mapGenreCode(story.getStGenrecode()), // ST_GENRECODE
            0L, // ST_SUGNUM 초기값
            story.getStCover(),
            "N", // ST_DSTATUS
            null, // ST_DREASON
            null, // ST_DDATE
            story.getuId(),
            story.getStContent()
        );
    }

    // 장르 코드 매핑 메서드
    private String mapGenreCode(String genre) {
        switch (genre) {
            case "판타지":
                return "CG-01";
            case "스릴러":
                return "CG-02";
            case "코미디":
                return "CG-03";
            case "Sci-Fi":
                return "CG-04";
            case "미스터리":
                return "CG-05";
            case "로맨스":
                return "CG-06";
            case "호러":
                return "CG-07";
            case "무협":
                return "CG-08";
            case "드라마":
                return "CG-09";
            case "서부":
                return "CG-10";
            case "역사":
                return "CG-11";
            default:
                return "CG-00"; // 기타
        }
    }

    // 모든 스토리 목록 조회
    public List<Story> findAllStories() {
        String sql = "SELECT * FROM STORY";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Story story = new Story();
            story.setStNum(rs.getLong("ST_NUM"));
            story.setStTitle(rs.getString("ST_TITLE"));
            story.setStContent(rs.getString("ST_CONTENT"));
            story.setStCover(rs.getString("ST_COVER"));
            story.setuId(rs.getString("U_ID"));
            story.setStTypecode(rs.getString("ST_TYPECODE"));
            story.setStGenrecode(rs.getString("ST_GENRECODE"));
            story.setStSugnum(rs.getLong("ST_SUGNUM"));
            story.setStCrdate(rs.getTimestamp("ST_CRDATE"));
            return story;
        });
    }

    // 스토리 ID로 특정 스토리 조회
    public Story findStoryById(Long stNum) {
        String sql = "SELECT * FROM STORY WHERE ST_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{stNum}, (rs, rowNum) -> {
            Story story = new Story();
            story.setStNum(rs.getLong("ST_NUM"));
            story.setStTitle(rs.getString("ST_TITLE"));
            story.setStCrdate(rs.getTimestamp("ST_CRDATE"));
            story.setStEddate(rs.getTimestamp("ST_EDDATE"));
            story.setStViewnum(rs.getLong("ST_VIEWNUM"));
            story.setStTypecode(rs.getString("ST_TYPECODE"));
            story.setStGenrecode(rs.getString("ST_GENRECODE"));
            story.setStSugnum(rs.getLong("ST_SUGNUM"));
            story.setStCover(rs.getString("ST_COVER"));
            story.setStDstatus(rs.getString("ST_DSTATUS"));
            story.setStDreason(rs.getString("ST_DREASON"));
            story.setStDdate(rs.getTimestamp("ST_DDATE"));
            story.setuId(rs.getString("U_ID"));
            story.setStContent(rs.getString("ST_CONTENT"));
            return story;
        });
    }
    
    // 모든 선택지 목록을 조회하는 메서드
    public List<Choice> findAllChoices() {
        String sql = "SELECT * FROM CHOICE"; // CHOICE 테이블 이름이 실제로 다르면 수정 필요
        return jdbcTemplate.query(sql, new RowMapper<Choice>() {
            @Override
            public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
                Choice choice = new Choice();
                choice.setId(rs.getLong("CHOICE_ID")); // 실제 컬럼명으로 수정 필요
                choice.setName(rs.getString("CHOICE_NAME"));
                choice.setContent(rs.getString("CHOICE_CONTENT"));
                choice.setMoneyEffect(rs.getLong("MONEY_EFFECT"));
                choice.setHpEffect(rs.getLong("HP_EFFECT"));
                return choice;
            }
        });
    }

    // 스토리 업데이트
    public void updateStory(Story story) {
        String sql = "UPDATE STORY SET ST_TITLE = ?, ST_EDDATE = ?, ST_VIEWNUM = ?, ST_TYPECODE = ?, " +
                     "ST_GENRECODE = ?, ST_SUGNUM = ?, ST_COVER = ?, ST_DSTATUS = ?, ST_DREASON = ?, ST_DDATE = ?, U_ID = ?, ST_CONTENT = ? " +
                     "WHERE ST_NUM = ?";

        jdbcTemplate.update(sql,
            story.getStTitle(),
            new Timestamp(System.currentTimeMillis()), // ST_EDDATE
            story.getStViewnum(),
            story.getStTypecode(),
            story.getStGenrecode(),
            story.getStSugnum(),
            story.getStCover(),
            story.getStDstatus(),
            story.getStDreason(),
            story.getStDdate(),
            story.getuId(),
            story.getStContent(),
            story.getStNum()
        );
    }

    // 스토리 삭제
    public boolean deleteStory(Long stNum) {
        String sql = "DELETE FROM STORY WHERE ST_NUM = ?";
        int rows = jdbcTemplate.update(sql, stNum);
        return rows > 0;
    }
}
