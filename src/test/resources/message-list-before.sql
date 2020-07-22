delete from message;
insert into message(id,text,tag,user_id) values
(1,'first','tag',1),
(2,'second','tag2',1),
(3,'third','tag2',1),
(4,'fourth','tag3',1);

alter sequence hibernate_sequence restart with 10;