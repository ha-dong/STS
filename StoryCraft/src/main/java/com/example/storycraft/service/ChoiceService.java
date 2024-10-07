package com.example.storycraft.service;

import com.example.storycraft.dao.ChoiceDao;
import com.example.storycraft.model.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    @Autowired
    private ChoiceDao choiceDao;

    // SC_NUM으로 선택지 리스트를 가져오는 메서드
    public List<Choice> getChoicesByScNum(int scNum) {
        return choiceDao.getChoicesByScNum(scNum);
    }

    // 선택지의 NEXT_SC_NUM을 가져오는 메서드 추가
    public Integer getNextSceneNum(int choiceNum) {
        return choiceDao.getNextSceneNum(choiceNum);
    }

}
