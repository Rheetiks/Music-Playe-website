create database MusicStore;

use MusicStore;

create table Music(
musicId int primary key,
musicName varchar(50),
musicUrl varchar(100),
musicArtist varchar(20) 
);

ALTER TABLE Music 
MODIFY musicId INT AUTO_INCREMENT;


select * from Music;

insert into Music (musicName,musicUrl,musicArtist)values(
'dhoka',
'gfsghfggfkjghdfjhg',
'ray'
);

select musicName from Music where musicName like '%p%';

select musicName from Music where musicName like concat('p','%');