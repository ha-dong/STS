package com.example.storycraft.dao;

import com.example.storycraft.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper for Notice
    private static final class NoticeRowMapper implements RowMapper<Notice> {
        @Override
        public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notice notice = new Notice();
            notice.setNtNum(rs.getLong("NT_NUM"));
            notice.setNtTypeCode(rs.getString("NT_TYPECODE"));
            notice.setNtTitle(rs.getString("NT_TITLE"));
            notice.setNtText(rs.getString("NT_TEXT"));
            notice.setNtCrdate(rs.getTimestamp("NT_CRDATE"));
            notice.setNtEddate(rs.getTimestamp("NT_EDDATE"));
            notice.setUid(rs.getString("U_ID")); // 수정된 부분: setUId → setUid
            return notice;
        }
    }

    @Override
    public void save(Notice notice) {
        String sql = "INSERT INTO NOTICE (NT_NUM, NT_TYPECODE, NT_TITLE, NT_TEXT, NT_CRDATE, U_ID) " +
                     "VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, notice.getNtTypeCode(), notice.getNtTitle(), notice.getNtText(),
                            new java.sql.Timestamp(notice.getNtCrdate().getTime()), notice.getUid()); // 수정된 부분: getUId() → getUid()
    }

    @Override
    public List<Notice> findAll() {
        String sql = "SELECT * FROM NOTICE ORDER BY NT_CRDATE DESC";
        return jdbcTemplate.query(sql, new NoticeRowMapper());
    }

    @Override
    public Notice findById(Long id) {
        String sql = "SELECT * FROM NOTICE WHERE NT_NUM = ?";
        List<Notice> notices = jdbcTemplate.query(sql, new Object[]{id}, new NoticeRowMapper());
        return notices.isEmpty() ? null : notices.get(0);
    }

    @Override
    public void update(Notice notice) {
        String sql = "UPDATE NOTICE SET NT_TYPECODE = ?, NT_TITLE = ?, NT_TEXT = ?, NT_EDDATE = ? WHERE NT_NUM = ?";
        jdbcTemplate.update(sql, notice.getNtTypeCode(), notice.getNtTitle(), notice.getNtText(),
                            new java.sql.Timestamp(System.currentTimeMillis()), notice.getNtNum());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM NOTICE WHERE NT_NUM = ?";
        jdbcTemplate.update(sql, id);
    }
}
