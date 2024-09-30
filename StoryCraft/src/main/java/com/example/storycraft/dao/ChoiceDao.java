// ChoiceDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChoiceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 선택지 저장
    public int insertChoice(Choice choice, int scNum, int stNum, int parentScNum, int scLevel) {
        String sql = "INSERT INTO CHOICE (CHOICE_NUM, SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, CHOICE_NAME, CHOICE_CONTENT, MONEY, HP) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                choice.getChoiceNum(),
                scNum,
                stNum,
                parentScNum,
                scLevel,
                choice.getChoiceName(),
                choice.getChoiceContent(),
                choice.getMoney(),
                choice.getHp()
        );
    }
}
