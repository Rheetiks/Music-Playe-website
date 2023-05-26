package com.music.music.Dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.music.music.Model.Music;
import com.music.music.RowMapper.MusicRowMapper;

@Repository
public class MusicDao {
    
    @Autowired
    private JdbcTemplate jdbc;
    public String uploadUrl="D:\\projects\\music\\src\\main\\resources\\static\\Musics";
    public String uploadImageUrl="D:\\projects\\music\\src\\main\\resources\\static\\musicImages";

    
    public List<Music> searchMusic(Music music){
            String q="select * from Music where musicName like concat(?,'%')";
            List<Music> list=this.jdbc.query(q, new MusicRowMapper(),music.getMusicName());
        return list;
    }

    public void uploadFile(MultipartFile file,MultipartFile musicImageFile,Music music) throws IOException{
        Files.copy(file.getInputStream(), Paths.get(uploadUrl+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(musicImageFile.getInputStream(), Paths.get(uploadImageUrl+File.separator+musicImageFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        String sql="insert into music(musicName,musicArtist,musicFileName,musicImage) values(?,?,?,?)";
        this.jdbc.update(sql, music.getMusicName(),music.getMusicArtist(),file.getOriginalFilename(),musicImageFile.getOriginalFilename());
    }

    public List<Music> songs(){
        List<Music> musics;
        String sql="select * from music";
        musics=this.jdbc.query(sql,new MusicRowMapper());

        return musics;
    }
        
    
}
