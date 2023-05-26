package com.music.music.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.music.Dao.UserDao;
import com.music.music.Model.Music;
import com.music.music.Model.Playlist;
import com.music.music.Model.PlaylistMapper;
import com.music.music.Model.User;

@Service
public class UserService {
    
    @Autowired
    private UserDao userdao;

    public User addUser(User user){
        return userdao.addUser(user);
    }

    public User viewUser(User user){
        return userdao.viewUser(user);
    }
    public String addToPlaylist( PlaylistMapper playlistmapper){
       return userdao.addToPlaylist(playlistmapper);
    }

    public void createPlaylist( Playlist playlist){
        userdao.createPlaylist(playlist);
    }

    public List<Playlist> displayPlaylists(){
        return userdao.displayPlaylists();
    }

    public PlaylistMapper checkMusic( PlaylistMapper playlistmapper){
        return userdao.checkMusic(playlistmapper);
    }

    public List<Music> displayPlaylistSongs(PlaylistMapper playlistmapper){
        return  userdao.displayPlaylistSongs(playlistmapper);
 
    }

    public void removeFromPlaylist( PlaylistMapper playlistmapper){
        userdao.removeFromPlaylist(playlistmapper);
    }
}
