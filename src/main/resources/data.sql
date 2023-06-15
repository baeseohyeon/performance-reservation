insert into users (email, password, username, business_license, type)
values ('producer', '$2a$10$SuKEgeyxhiYJh5p5Fv9So.Zn5CcynNFCzO.9p1UDa0dqSqZ4.aVfW', 'pname',
        'license', 'VENUE');
insert into users (email, password, username)
values ('user', '$2a$10$SuKEgeyxhiYJh5p5Fv9So.Zn5CcynNFCzO.9p1UDa0dqSqZ4.aVfW', 'uname');

insert into authority (role)
values ('USER');
insert into authority (role)
values ('ADMIN');

insert into user_authority (user_id, role)
values (1, 'USER');
insert into user_authority (user_id, role)
values (1, 'ADMIN');
insert into user_authority (user_id, role)
values (2, 'USER');