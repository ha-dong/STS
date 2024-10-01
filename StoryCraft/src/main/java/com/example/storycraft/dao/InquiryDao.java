package com.example.storycraft.dao;

import com.example.storycraft.model.Comment;
import com.example.storycraft.model.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InquiryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 문의 등록
    public void insertInquiry(Inquiry inquiry) {
        String sql = "INSERT INTO INQUIRY (INQ_NUM, INQ_TITLE, INQ_TEXT, INQ_TYPECODE, INQ_GENRECODE, INQ_FILE, INQ_CRDATE, INQ_DSTATUS, U_ID) "
                   + "VALUES (SEQ_INQUIRY.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                inquiry.getInqTitle(),
                inquiry.getInqText(),
                inquiry.getInqTypecode(),
                inquiry.getInqGenrecode(),
                inquiry.getInqFile(),
                inquiry.getInqCrdate(),
                inquiry.getInqDstatus(),
                inquiry.getUserId());
    }

    // 모든 문의 목록 가져오기
    public List<Inquiry> getAllInquiries() {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_DSTATUS != 'Delete' ORDER BY INQ_CRDATE DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToInquiry(rs));
    }

    // 문의 상세 정보 가져오기
    public Inquiry getInquiryDetail(int inqNum) {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_NUM = ?";
        List<Inquiry> inquiries = jdbcTemplate.query(sql, new Object[]{inqNum}, (rs, rowNum) -> mapRowToInquiry(rs));
        return inquiries.isEmpty() ? null : inquiries.get(0);
    }

    // 문의 삭제 (완전 삭제)
    public void hardDeleteInquiry(int inqNum) {
        String sql = "DELETE FROM INQUIRY WHERE INQ_NUM = ?";
        jdbcTemplate.update(sql, inqNum);
    }

    // 문의 수정
    public void updateInquiry(int inqNum, Inquiry inquiry) {
        String sql = "UPDATE INQUIRY SET INQ_TITLE = ?, INQ_TEXT = ?, INQ_TYPECODE = ?, INQ_FILE = ?, INQ_CRDATE = ? WHERE INQ_NUM = ?";
        jdbcTemplate.update(sql,
                inquiry.getInqTitle(),
                inquiry.getInqText(),
                inquiry.getInqTypecode(),
                inquiry.getInqFile(),
                inquiry.getInqCrdate(),
                inqNum);
    }

    // 문의 상태 업데이트
    public void updateInquiryStatus(int inqNum, String newStatus) {
        String sql = "UPDATE INQUIRY SET INQ_GENRECODE = ? WHERE INQ_NUM = ?";
        jdbcTemplate.update(sql, newStatus, inqNum);
    }

    // 댓글 추가
    public void insertComment(int inqNum, String commentText) {
        String sql = "INSERT INTO COMMENTS (CMT_NUM, INQ_NUM, CMT_DATE, CMT_TEXT) VALUES (SEQ_COMMENT.NEXTVAL, ?, SYSDATE, ?)";
        jdbcTemplate.update(sql, inqNum, commentText);
    }

    // 특정 문의에 대한 댓글 목록 가져오기
    public List<Comment> getCommentsForInquiry(int inqNum) {
        String sql = "SELECT * FROM COMMENTS WHERE INQ_NUM = ?";
        return jdbcTemplate.query(sql, new Object[]{inqNum}, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setCmtNum(rs.getInt("CMT_NUM"));
            comment.setInqNum(rs.getInt("INQ_NUM"));
            comment.setCmtDate(rs.getTimestamp("CMT_DATE"));
            comment.setCmtText(rs.getString("CMT_TEXT"));
            return comment;
        });
    }

    // ResultSet을 Inquiry 객체로 변환하는 메소드
    private Inquiry mapRowToInquiry(ResultSet rs) throws SQLException {
        Inquiry inquiry = new Inquiry();
        inquiry.setInqNum(rs.getInt("INQ_NUM"));
        inquiry.setInqTitle(rs.getString("INQ_TITLE"));
        inquiry.setInqText(rs.getString("INQ_TEXT"));
        inquiry.setInqTypecode(rs.getString("INQ_TYPECODE"));
        inquiry.setInqGenrecode(rs.getString("INQ_GENRECODE"));
        inquiry.setInqFile(rs.getString("INQ_FILE"));
        inquiry.setInqCrdate(rs.getTimestamp("INQ_CRDATE"));
        inquiry.setInqDstatus(rs.getString("INQ_DSTATUS"));
        inquiry.setUserId(rs.getString("U_ID"));
        return inquiry;
    }
    
    // 문의 종류로 필터링
    public List<Inquiry> findInquiriesByType(String inquiryType) {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_TYPECODE = ? AND INQ_DSTATUS != 'Delete'";
        return jdbcTemplate.query(sql, new Object[]{inquiryType}, (rs, rowNum) -> mapRowToInquiry(rs));
    }

    // 상태로 필터링
    public List<Inquiry> findInquiriesByStatus(String inquiryStatus) {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_GENRECODE = ? AND INQ_DSTATUS != 'Delete'";
        return jdbcTemplate.query(sql, new Object[]{inquiryStatus}, (rs, rowNum) -> mapRowToInquiry(rs));
    }

    // 문의 종류와 상태로 필터링
    public List<Inquiry> findInquiriesByTypeAndStatus(String inquiryType, String inquiryStatus) {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_TYPECODE = ? AND INQ_GENRECODE = ? AND INQ_DSTATUS != 'Delete'";
        return jdbcTemplate.query(sql, new Object[]{inquiryType, inquiryStatus}, (rs, rowNum) -> mapRowToInquiry(rs));
    }
    
}
