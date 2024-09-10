package com.example.storycraft.dao;

import org.springframework.jdbc.core.RowMapper;
import com.example.storycraft.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setuId(rs.getString("U_ID"));
        user.setuPw(rs.getString("U_PW"));
        user.setuName(rs.getString("U_NAME"));
        user.setuPhone(rs.getString("U_PHONE"));
        user.setuAdd(rs.getString("U_ADD"));
        user.setuProfile(rs.getString("U_PROFILE"));
        user.setuEmail(rs.getString("U_EMAIL"));
        user.setuCertified(rs.getString("U_CERTIFIED"));
        user.setuCdate(rs.getTimestamp("U_CDATE"));
        user.setuActivate(rs.getString("U_ACTIVATE"));
        user.setuNickname(rs.getString("U_NICKNAME"));
        user.setuItd(rs.getString("U_ITD"));
        user.setuBirthday(rs.getTimestamp("U_BIRTHDAY"));
        user.setuGender(rs.getString("U_GENDER"));
        user.setMainComplete(rs.getString("MAIN_COMPLETE"));
        user.setuCode(rs.getString("U_CODE"));
        user.setuDstatus(rs.getString("U_DSTATUS"));
        return user;
    }
}

