create table admin(
adminId int auto_increment primary key,
adminName varchar(15),
adminEmail varchar(30),
adminPassword varchar(20)
);

select * from admin;

insert into admin(adminName,adminEmail,adminPassword) values('admin','admin@gmail.com','admin');