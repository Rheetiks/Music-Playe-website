package com.music.music.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.music.music.Model.PlaylistMapper;


public class PlaylistMapperRowMapper implements RowMapper<PlaylistMapper>{

    @Override
    public PlaylistMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
       PlaylistMapper playlistmapper=new PlaylistMapper();

       playlistmapper.getUser().setUserId(rs.getInt(1));
       playlistmapper.getMusic().setMusicId(rs.getInt(2));
       playlistmapper.getPlaylist().setPlaylistId(3);

       return playlistmapper;
    }
    
}
