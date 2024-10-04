package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 이미지 경로도 함께 저장하는 메서드
    public int insertReport(int stNum, String reTypeCode, String reText, String userId, String imagePath) {
        String sql = "INSERT INTO REPORT (RE_NUM, ST_NUM, RE_TYPECODE, RE_TEXT, U_ID, RE_RP, RE_DATE) " +
                     "VALUES (SEQ_REPORT.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
        return jdbcTemplate.update(sql, stNum, reTypeCode, reText, userId, imagePath);
    }

    public void deleteReportsByStory(int stNum) {
        String sql = "DELETE FROM REPORT WHERE ST_NUM = ?";
        jdbcTemplate.update(sql, stNum);
    }
}
