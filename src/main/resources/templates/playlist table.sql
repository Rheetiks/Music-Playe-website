create table playlist(
userId int,
musicId int,
FOREIGN KEY (userId) REFERENCES user(userId),
FOREIGN KEY (musicId) REFERENCES music(musicId)
);

select * from playlist;




insert into PlaylistMapper(musicId,userId,playlistId) values(9,2,3);

select * from PlaylistMapper;

delete from PlaylistMapper where userId=2;

select * from music where musicId in (select musicId from PlaylistMapper where PlaylistId=4);

alter table PlaylistMapper add playlistId int, add constraint foreign key(playlistId) references playlist(playlistId);