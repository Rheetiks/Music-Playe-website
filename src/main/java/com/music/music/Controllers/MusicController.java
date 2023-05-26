package com.music.music.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.music.music.Model.Music;
import com.music.music.Service.MusicService;

@RestController
public class MusicController {
    @Autowired
    MusicService musicservice;

    @GetMapping("/songs")
    public List<Music> songs(){
        List<Music> musics;
        musics=musicservice.songs();
        return musics;

    }

    @PostMapping("/search")
    public List<Music> searchMusic(@RequestBody Music music){
        return musicservice.searchMusic(music);
    }

    @PostMapping("/uploadMusic")
    public String uploadMusic(@RequestParam("music") MultipartFile file , @RequestParam("musicImageFile") MultipartFile musicImageFile, @ModelAttribute Music music) throws IOException{ 
        musicservice.uploadFile(file,musicImageFile,music); 
        return music.getMusicName();
    }
}
