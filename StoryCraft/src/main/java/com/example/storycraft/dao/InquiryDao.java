package com.example.storycraft.dao;

import com.example.storycraft.model.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                inquiry.getUId());
    }

    // 사용자 ID로 문의 목록 가져오기
    public List<Inquiry> getInquiryListByUserId(String userId) {
        String sql = "SELECT * FROM INQUIRY WHERE U_ID = ? AND INQ_DSTATUS != 'Delete' ORDER BY INQ_CRDATE DESC";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> mapRowToInquiry(rs));
    }

    // 문의 상세 조회
    public Inquiry getInquiryDetail(int inqNum, String userId) {
        String sql = "SELECT * FROM INQUIRY WHERE INQ_NUM = ? AND U_ID = ?";
        List<Inquiry> inquiryList = jdbcTemplate.query(sql, new Object[]{inqNum, userId}, (rs, rowNum) -> mapRowToInquiry(rs));
        return inquiryList.isEmpty() ? null : inquiryList.get(0);  // 데이터가 없으면 null 반환
    }

    // 문의 하드 삭제 처리
    public void hardDeleteInquiry(int inqNum, String userId) {
        String sql = "DELETE FROM INQUIRY WHERE INQ_NUM = ? AND U_ID = ?";
        jdbcTemplate.update(sql, inqNum, userId);  // 실제로 DB에서 데이터를 삭제
    }

    // 문의 수정 처리
    public void updateInquiry(int inqNum, Inquiry inquiryDetails, String userId) {
        String sql = "UPDATE INQUIRY SET INQ_TITLE = ?, INQ_TEXT = ?, INQ_TYPECODE = ?, INQ_GENRECODE = ?, INQ_EDDATE = ? WHERE INQ_NUM = ? AND U_ID = ?";
        jdbcTemplate.update(sql,
                            inquiryDetails.getInqTitle(),
                            inquiryDetails.getInqText(),
                            inquiryDetails.getInqTypecode(),
                            inquiryDetails.getInqGenrecode(),
                            new Timestamp(System.currentTimeMillis()),
                            inqNum,
                            userId);
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
        inquiry.setInqDreason(rs.getString("INQ_DREASON"));
        inquiry.setInqDdate(rs.getTimestamp("INQ_DDATE"));
        inquiry.setUId(rs.getString("U_ID"));
        return inquiry;
    }
}
