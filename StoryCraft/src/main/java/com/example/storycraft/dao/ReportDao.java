package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertReport(int stNum, String reTypeCode, String reText, String userId) {
        String sql = "INSERT INTO REPORT (RE_NUM, ST_NUM, RE_TYPECODE, RE_TEXT, RE_DATE, U_ID) " +
                     "VALUES (SEQ_REPORT.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
        return jdbcTemplate.update(sql, stNum, reTypeCode, reText, userId);
    }
}
