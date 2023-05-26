package com.music.music.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.music.music.Model.User;

public class UserRowMapper implements RowMapper<User>{

   

    @Override
    public User mapRow(ResultSet rs ,int rowNum) throws SQLException {
        User user=new User();
        user.setUserId(rs.getInt(1));
        user.setUserName(rs.getString(2));
        user.setUserPhoneNo(rs.getLong(3));
        user.setUserEmail(rs.getString(4));
        user.setUserPassword(rs.getString(5));

        return user;

    }


   
    
}
