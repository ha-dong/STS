// ChoiceDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;

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
        return jdbcTemplate.query(sql, new Object[]{scNum}, new ChoiceRowMapper());
    }

    public int insertChoice(Choice choice, Scene scene) {
        String sql = "INSERT INTO CHOICE (CHOICE_NUM, SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, CHOICE_NAME, CHOICE_CONTENT, MONEY, HP, NEXT_SC_NUM) " +
                     "VALUES (SEQ_CHOICE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            scene.getScNum(),
            scene.getStNum(),
            scene.getParentScNum(),
            scene.getScLevel(),
            choice.getChoiceName(),
            choice.getChoiceContent(),
            choice.getMoney(), // NULL 값 허용
            choice.getHp(),    // NULL 값 허용
            choice.getNextScNum()
        );
    }
}
