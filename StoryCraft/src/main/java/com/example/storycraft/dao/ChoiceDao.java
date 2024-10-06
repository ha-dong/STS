// ChoiceDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.Choice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChoiceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // ChoiceDao.java에서 NEXT_SC_NUM을 가져오는 메소드 추가
    public Integer getNextSceneNum(int choiceNum) {
        String sql = "SELECT NEXT_SC_NUM FROM CHOICE WHERE CHOICE_NUM = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{choiceNum}, Integer.class);
    }
    
    // 선택지 이름을 통해 다음 장면 번호 찾기
    public Integer getNextSceneNumByChoiceName(String choiceName) {
        String sql = "SELECT NEXT_SC_NUM FROM CHOICE WHERE CHOICE_NAME = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{choiceName}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없을 때
        }
    }
    
    public List<Choice> getChoicesByScNum(int scNum) {
        String sql = "SELECT * FROM CHOICE WHERE SC_NUM = ?";
        return jdbcTemplate.query(sql, new Object[]{scNum}, new ChoiceRowMapper());  // SC_NUM으로 선택지 가져오기
    }

    // 선택지 저장 (NEXT_SC_NUM이 0으로 저장되지 않도록 수정)
    public int insertChoice(Choice choice, int scNum, int stNum, int parentScNum, int scLevel) {
        String sql = "INSERT INTO CHOICE (CHOICE_NUM, SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, CHOICE_NAME, CHOICE_CONTENT, MONEY, HP, NEXT_SC_NUM) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // NEXT_SC_NUM이 없을 경우 자동으로 다음 장면 번호를 설정하거나 null로 저장
        Integer nextScNum = choice.getNextScNum();
        if (nextScNum == null || nextScNum == 0) {
            nextScNum = null;  // 0으로 들어가는 것을 방지
        }

        return jdbcTemplate.update(sql,
            choice.getChoiceNum(),
            scNum,
            stNum,
            parentScNum,
            scLevel,
            choice.getChoiceName(),
            choice.getChoiceContent(),
            choice.getMoney(),
            choice.getHp(),
            nextScNum  // 0으로 저장되지 않도록 수정된 로직
        );
    }
}
