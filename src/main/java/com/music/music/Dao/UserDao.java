package com.music.music.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.music.music.Model.Music;
import com.music.music.Model.Playlist;
import com.music.music.Model.PlaylistMapper;
import com.music.music.Model.User;
import com.music.music.RowMapper.MusicRowMapper;
import com.music.music.RowMapper.PlaylistMapperRowMapper;
import com.music.music.RowMapper.PlaylistRowMapper;
import com.music.music.RowMapper.UserRowMapper;

@Repository
public class UserDao {
    
    @Autowired
    private JdbcTemplate jdbc;
    public static User currUser=new User();

    public User addUser(User user){
        String sql="";
        currUser=viewUser(user);
        if(currUser!=null){
            return currUser;
        }
        sql="insert into user(userName,userPhoneNo,userEmail,userPassword) values(?,?,?,?)";
        this.jdbc.update(sql, user.getUserName(),user.getUserPhoneNo(),user.getUserEmail(),user.getUserPassword());
        currUser=viewUser(user);
        return currUser;
    }

    public User viewUser(User user){
        currUser=null;
        try{
            String sql="select * from user where userEmail=? && userPassword=?";
            currUser=this.jdbc.queryForObject(sql, new UserRowMapper(), user.getUserEmail(),user.getUserPassword());
        }catch(Exception e){e.printStackTrace();}
        return currUser;
    }

    public String addToPlaylist(PlaylistMapper playlistmapper){
        PlaylistMapper check=checkMusic(playlistmapper);
        if (check!=null){
            return "This music already exists in your playlist";
        }
        String sql="insert into PlaylistMapper(musicId,userId,playlistId) values(?,?,?)";
        this.jdbc.update(sql,playlistmapper.getMusic().getMusicId(),currUser.getUserId() ,playlistmapper.getPlaylist().getPlaylistId());
        return "Successfully added to playlist"+playlistmapper.getPlaylist().getPlaylistId();
    }

    public void createPlaylist(Playlist playlist){
        String sql="insert into playlist(playlistName,userId) values(?,?)";
        this.jdbc.update(sql, playlist.getPlaylistName(),currUser.getUserId());
    }

    public PlaylistMapper checkMusic(PlaylistMapper playlistmapper){
        PlaylistMapper check=null;
        try{
            String sql="select * from PlaylistMapper where musicId=? && userId=? && playlistId=?";
            check=this.jdbc.queryForObject(sql, new PlaylistMapperRowMapper(),playlistmapper.getMusic().getMusicId(),currUser.getUserId(),playlistmapper.getPlaylist().getPlaylistId());
        }catch(Exception e){e.printStackTrace();}
        return check;
    }

    public List<Playlist> displayPlaylists (){
        List<Playlist> list=null;
        String sql="select * from playlist where userId=?";
        list=this.jdbc.query(sql,new PlaylistRowMapper(),currUser.getUserId());
            
        return list;
    }

    public List<Music> displayPlaylistSongs(PlaylistMapper playlistmapper){
        List<Music> list=null;
        String sql="select * from music where musicId in (select musicId from PlaylistMapper where PlaylistId=?)";
        list=this.jdbc.query(sql,new MusicRowMapper(),playlistmapper.getPlaylist().getPlaylistId());
        return list;
 
    }

    public void removeFromPlaylist(PlaylistMapper playlistmapper){
        String sql="delete from PlaylistMapper where musicId=? && playlistId=?";
        this.jdbc.update(sql,playlistmapper.getMusic().getMusicId(),playlistmapper.getPlaylist().getPlaylistId());
    }

    
}
