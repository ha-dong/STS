package com.example.storycraft.service;

import com.example.storycraft.dao.NoticeDao;
import com.example.storycraft.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeDao noticeDao;

    @Autowired
    public NoticeService(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    public void saveNotice(Notice notice) {
        noticeDao.save(notice);
    }

    public List<Notice> getAllNotices() {
        List<Notice> notices = noticeDao.findAll();
        // 내림차순 정렬 (최신 공지사항이 위로)
        notices.sort(Comparator.comparing(Notice::getCreateDate).reversed());
        return notices;
    }

    public Notice getNoticeById(Long id) {
        return noticeDao.findById(id);
    }

    public void updateNotice(Notice notice) {
        noticeDao.update(notice);  // 공지사항 수정
    }
}
