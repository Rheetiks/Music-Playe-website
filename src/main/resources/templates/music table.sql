use MusicStore;

select * from music;

alter table music
rename column musicUrl TO musicFileName;