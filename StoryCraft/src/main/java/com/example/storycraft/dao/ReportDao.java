package com.example.storycraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.storycraft.model.Report;

@Repository
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 신고 저장 메서드
    public boolean saveReport(Report report) {
        String sql = "INSERT INTO REPORT (RE_NUM, ST_NUM, RE_TYPECODE, RE_TEXT, RE_DATE) " +
                     "VALUES (REPORT_SEQ.NEXTVAL, ?, ?, ?, ?)";

        int rows = jdbcTemplate.update(sql,
                report.getStNum(),
                report.getReTypecode(),
                report.getReText(),
                report.getReDate()
        );

        return rows > 0;
    }
}
