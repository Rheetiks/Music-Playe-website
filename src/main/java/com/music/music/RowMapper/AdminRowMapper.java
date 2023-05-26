package com.music.music.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.music.music.Model.Admin;

public class AdminRowMapper implements RowMapper<Admin>{

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Admin admin =new Admin();

        admin.setAdminId(rs.getInt(1));
        admin.setAdminName(rs.getString(2));
        admin.setAdminEmail(rs.getString(3));
        admin.setAdminPassword(rs.getString(4));

        return admin;

    }
    
}
