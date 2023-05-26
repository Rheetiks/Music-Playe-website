package com.music.music.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.music.music.Model.Music;
public class MusicRowMapper implements RowMapper<Music> {

    @Override
    public Music mapRow(ResultSet rs, int rowNum) throws SQLException {
        Music music =new Music();
        music.setMusicId(rs.getInt(1));
        music.setMusicName(rs.getString(2));
        music.setMusicArtist(rs.getString(3));
        music.setMusicFileName(rs.getString(4));
        music.setMusicImage(rs.getString(5));
        return music;
    }
    
}
