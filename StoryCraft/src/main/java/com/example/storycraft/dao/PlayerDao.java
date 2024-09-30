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
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{stNum, userId}, Integer.class);
        return count != null && count > 0;
    }

    public boolean hasPlayerRecord(int stNum, String userId) {
        String sql = "SELECT COUNT(*) FROM PLAYER WHERE ST_NUM = ? AND U_ID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{stNum, userId}, Integer.class);
        return count != null && count > 0;
    }

    public void insertPlayer(int stNum, String userId) {
        String sql = "INSERT INTO PLAYER (PLAYER_NUM, ST_NUM, U_ID, RECOMMENDED) VALUES (SEQ_PLAYER.NEXTVAL, ?, ?, 'N')";
        jdbcTemplate.update(sql, stNum, userId);
    }

    public boolean recommendStory(int stNum, String userId) {
        // 먼저 PLAYER 레코드가 있는지 확인하고, 없으면 삽입
        if (!hasPlayerRecord(stNum, userId)) {
            insertPlayer(stNum, userId);
        }

        String sql = "UPDATE PLAYER SET RECOMMENDED = 'Y' WHERE ST_NUM = ? AND U_ID = ? AND RECOMMENDED = 'N'";
        int updated = jdbcTemplate.update(sql, stNum, userId);
        return updated > 0;
    }

    public boolean recommendStoryAlternative(int stNum, String userId) {
        // 이 메서드는 기존 recommendStory 로직을 대체할 수 있습니다.
        // 플레이어가 이미 추천했는지 확인
        boolean hasRecommended = hasRecommended(stNum, userId);
        if (hasRecommended) {
            return false;
        }

        // 플레이어 레코드가 있는지 확인
        boolean hasPlayerRecord = hasPlayerRecord(stNum, userId);
        if (!hasPlayerRecord) {
            // 플레이어 레코드 삽입
            insertPlayer(stNum, userId);
        }

        // 추천 처리
        String updateSql = "UPDATE PLAYER SET RECOMMENDED = 'Y' WHERE ST_NUM = ? AND U_ID = ?";
        int updated = jdbcTemplate.update(updateSql, stNum, userId);
        if (updated > 0) {
            return true;
        }
        return false;
    }

    public void deletePlayersByStory(int stNum) {
        String sql = "DELETE FROM PLAYER WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }
}
