package com.music.music.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.music.music.Dao.MusicDao;
import com.music.music.Model.Music;

@Service
public class MusicService {
    @Autowired
    MusicDao musicdao;

    public List<Music> searchMusic(Music music){
        return musicdao.searchMusic(music);
    } 

    public void uploadFile(MultipartFile file,MultipartFile musicImageFile, Music music) throws IOException{
        musicdao.uploadFile(file,musicImageFile,music);
    }

    public List<Music> songs(){
        return musicdao.songs();
    }
}
