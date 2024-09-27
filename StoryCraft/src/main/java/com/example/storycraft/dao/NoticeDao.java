package com.example.storycraft.dao;

import com.example.storycraft.model.Notice;
import java.util.List;

public interface NoticeDao {
    void save(Notice notice);           // 공지사항 저장
    List<Notice> findAll();             // 모든 공지사항 조회
    Notice findById(Long id);           // ID로 공지사항 조회
    void update(Notice notice);         // 공지사항 수정
    void deleteById(Long id);           // 공지사항 삭제
}
