package com.music.music.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.music.music.Model.Music;
import com.music.music.Model.Playlist;
import com.music.music.Model.PlaylistMapper;
import com.music.music.Model.User;
import com.music.music.Service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userservice.addUser(user);
    }

    @PostMapping("/viewUser")
    public User viewUser(@RequestBody User user){
        return userservice.viewUser(user);
    }

    @PostMapping("/checkMusic")
    public PlaylistMapper checkMusic(@RequestBody PlaylistMapper playlistmapper){
        return userservice.checkMusic(playlistmapper);
    }


    @PostMapping("/addToPlaylist")
    public String addToPlaylist(@RequestBody PlaylistMapper playlistmapper){
       return  userservice.addToPlaylist(playlistmapper);

    }

    @PostMapping("/createPlaylist")
    public String addToPlaylist(@RequestBody Playlist playlist ){
        userservice.createPlaylist(playlist);

        return "Successfully created playlist"+" " +playlist.getPlaylistName();
    }

    @GetMapping("/displayPlaylists")
    public List<Playlist> displayPlaylists(){
        return userservice.displayPlaylists();
    }

    @PostMapping("/displayPlaylistSongs")
    public List<Music> displayPlaylistSongs(@RequestBody PlaylistMapper playlistmapper){
       return userservice.displayPlaylistSongs(playlistmapper);
    }

    @PostMapping("/removeFromPlaylist")
    public List<Music> removeFromPlaylist(@RequestBody PlaylistMapper playlistmapper){
        userservice.removeFromPlaylist(playlistmapper);
        return userservice.displayPlaylistSongs(playlistmapper);
 
     }
    
}
