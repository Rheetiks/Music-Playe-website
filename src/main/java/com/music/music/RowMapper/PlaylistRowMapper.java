package com.music.music.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.music.music.Model.Playlist;

public class PlaylistRowMapper implements RowMapper<Playlist>{

    @Override
    public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Playlist playlist=new Playlist();
        playlist.setPlaylistId(rs.getInt(1));
        playlist.setPlaylistName(rs.getString(2));
        playlist.getUser().setUserId(rs.getInt(3));

        return playlist;
    }

    
    
}
