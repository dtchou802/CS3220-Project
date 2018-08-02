drop table if exists uploads;

create table uploads (
    id      integer auto_increment primary key,
    fileName    varchar(255),
    filePath    varchar(255),
    date DATE
);

insert into uploads (fileName, filePath,  date) values ('newFile','/c:/filePath', 'CURDATE()');
insert into uploads (fileName, filePath,  date) values ('secoundFile', '/c:/filePath', 'CURDATE()');
