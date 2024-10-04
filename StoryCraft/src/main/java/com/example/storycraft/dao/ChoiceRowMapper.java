package com.example.storycraft.dao;

import org.springframework.jdbc.core.RowMapper;

import com.example.storycraft.model.Choice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setChoiceNum(rs.getInt("CHOICE_NUM"));
        choice.setScNum(rs.getInt("SC_NUM"));
        choice.setStNum(rs.getInt("ST_NUM"));
        choice.setParentScNum(rs.getInt("PARENT_SC_NUM"));
        choice.setScLevel(rs.getInt("SC_LEVEL"));
        choice.setChoiceName(rs.getString("CHOICE_NAME"));
        choice.setChoiceContent(rs.getString("CHOICE_CONTENT"));
        choice.setMoney(rs.getInt("MONEY"));
        choice.setHp(rs.getInt("HP"));
        choice.setNextScNum(rs.getObject("NEXT_SC_NUM", Integer.class));
        return choice;
    }
}
