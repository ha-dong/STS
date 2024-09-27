package com.example.storycraft.dao;

import com.example.storycraft.model.Choice;
import com.example.storycraft.model.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class SceneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChoiceDao choiceDao;

    // 장면 저장
    public int insertScene(Scene scene) {
        String sql = "INSERT INTO SCENE (SC_NUM, ST_NUM, PARENT_SC_NUM, SC_LEVEL, SC_TEXT, SC_ILLUS, MONEY, HP) " +
                "VALUES (SEQ_SCENE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                scene.getStNum(),
                scene.getParentScNum(),
                scene.getScLevel(),
                scene.getScText(),
                scene.getScIllus(),
                scene.getMoney(),
                scene.getHp()
        );

        // 방금 삽입된 SC_NUM 가져오기
        int scNum = jdbcTemplate.queryForObject("SELECT SEQ_SCENE.CURRVAL FROM DUAL", Integer.class);

        // 선택지 저장
        for (Choice choice : scene.getChoices()) {
            choiceDao.insertChoice(choice, scNum, scene.getStNum(), scene.getParentScNum(), scene.getScLevel());
        }

        return 1;
    }
}
