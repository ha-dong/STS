package com.example.storycraft.dao;

import com.example.storycraft.model.Notice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {

    private List<Notice> notices = new ArrayList<>();
    private long nextId = 1;  // 순차적인 번호 부여를 위한 변수

    @Override
    public void save(Notice notice) {
        notice.setId(nextId++);  // 순차적으로 번호 부여
        notices.add(notice);
    }

    @Override
    public List<Notice> findAll() {
        // 내림차순 정렬
        List<Notice> sortedNotices = new ArrayList<>(notices);
        sortedNotices.sort(Comparator.comparing(Notice::getCreateDate).reversed());
        return sortedNotices;
    }

    @Override
    public Notice findById(Long id) {
        return notices.stream().filter(notice -> notice.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Notice updatedNotice) {
        Notice existingNotice = findById(updatedNotice.getId());
        if (existingNotice != null) {
            existingNotice.setTitle(updatedNotice.getTitle());
            existingNotice.setContent(updatedNotice.getContent());
        }
    }
}
