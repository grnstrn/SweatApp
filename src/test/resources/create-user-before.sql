delete from user_role;
delete from usr;

insert into usr(id,active,password,username) values
(1,true,'$2a$08$X9IYgd0BBo55SEmgZ20Oyen49g2MritaNIzJZILVJVLZVFFl6I2FC', 'grn'),
(2,true,'$2a$08$X9IYgd0BBo55SEmgZ20Oyen49g2MritaNIzJZILVJVLZVFFl6I2FC', 'mike');

insert into user_role(user_id, roles) values
(1,'USER'),(1,'ADMIN'),
(2,'USER');