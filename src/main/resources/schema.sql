drop table if exists reservation_seat cascade;
drop table if exists seat cascade;
drop table if exists reservation cascade;
drop table if exists performance cascade;
drop table if exists venue cascade;
drop table if exists user_authority cascade;
drop table if exists authority cascade;
drop table if exists payment cascade;
drop table if exists users cascade;

create table payment
(
    card_cvv        integer not null,
    id              bigint AUTO_INCREMENT,
    card_expiration varchar(255),
    card_number     varchar(255),
    payment_method  varchar(255) check (payment_method in ('CREDIT_CARD')),
    created_at      timestamp(6) default current_timestamp(6),
    updated_at      timestamp(6) default current_timestamp(6) on update current_timestamp (6),
    primary key (id)
) engine=InnoDB;

create table authority
(
    role       varchar(255) not null check (role in ('USER', 'ADMIN')),
    created_at timestamp(6) default current_timestamp(6),
    updated_at timestamp(6) default current_timestamp(6) on update current_timestamp (6),
    primary key (role)
) engine=InnoDB;

create table performance
(
    capacity     integer not null,
    normal_price integer not null,
    vip_price    integer not null,
    created_at   timestamp(6) default current_timestamp(6),
    end_time     timestamp(6),
    id           bigint AUTO_INCREMENT,
    start_time   timestamp(6),
    updated_at   timestamp(6) DEFAULT current_timestamp(6) on update current_timestamp (6),
    user_id      bigint,
    venue_id     bigint,
    name         varchar(255),
    available_seats integer not null,
    primary key (id)
) engine=InnoDB;

create table reservation
(
    created_at     timestamp(6) default current_timestamp(6),
    id             bigint AUTO_INCREMENT,
    payment_id     bigint,
    updated_at     timestamp(6) DEFAULT current_timestamp(6) ON UPDATE current_timestamp (6),
    performance_id bigint,
    user_id        bigint,
    primary key (id)
) engine=InnoDB;

create table seat
(
    created_at timestamp(6) default current_timestamp(6),
    id         bigint AUTO_INCREMENT,
    updated_at timestamp(6) default current_timestamp(6) on update current_timestamp (6),
    venue_id   bigint,
    number     varchar(255),
    type       varchar(255) check (type in ('VIP', 'NORMAL')),
    primary key (id)
) engine=InnoDB;

create table reservation_seat
(
    id             bigint AUTO_INCREMENT,
    seat_id     bigint,
    reservation_id bigint,
    created_at     timestamp(6) default current_timestamp(6),
    updated_at     timestamp(6) DEFAULT current_timestamp(6) ON UPDATE current_timestamp (6),
    primary key (id)
) engine=InnoDB;

create table user_authority
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role)
) engine=InnoDB;

create table users
(
    created_at       timestamp(6) default current_timestamp(6),
    id               bigint AUTO_INCREMENT,
    updated_at       timestamp(6) default current_timestamp(6) on
        update current_timestamp (6),
    business_license varchar(255),
    email            varchar(255),
    password         varchar(255),
    type             varchar(255) check (type in ('VENUE', 'PERFORMANCE')),
    username         varchar(255),
    primary key (id)
) engine=InnoDB;

create table venue
(
    capacity   integer not null,
    end_time   time(6),
    start_time time(6),
    type       varchar(255) check (type in ('FIXED_SEAT')),
    created_at timestamp(6) default current_timestamp(6),
    id         bigint AUTO_INCREMENT,
    updated_at timestamp(6) default current_timestamp(6) on update current_timestamp (6),
    user_id    bigint,
    name       varchar(255),
    primary key (id)
) engine=InnoDB;

alter table performance
    add constraint FKgnrih50e9vd2u791msuqhg73a
        foreign key (user_id)
            references users (id);

alter table performance
    add constraint FKlx8rv5t5hrt28t6v29osmacai
        foreign key (venue_id)
            references venue (id);

alter table reservation
    add constraint FK8g1s9tyunsjdv96dyiobv51bb
        foreign key (payment_id)
            references payment (id);

alter table reservation
    add constraint FKsmerqyye39w16l921kogfj6cs
        foreign key (performance_id)
            references performance (id);

alter table reservation
    add constraint FKrea93581tgkq61mdl13hehami
        foreign key (user_id)
            references users (id);

alter table seat
    add constraint FKkt7mgkoowgxocqf3844m53i8q
        foreign key (venue_id)
            references venue (id);

alter table user_authority
    add constraint FKiqifjsx11qm8yqjrokri9qlig
        foreign key (role)
            references authority (role);

alter table user_authority
    add constraint FKhi46vu7680y1hwvmnnuh4cybx
        foreign key (user_id)
            references users (id);

alter table venue
    add constraint FK3y4akt1tbhmxnivjldiem1dam
        foreign key (user_id)
            references users (id);

alter table reservation_seat
    add constraint FKmppl4hty8sagbei7ywefv0qxy
        foreign key (reservation_id)
            references reservation (id);

alter table reservation_seat
    add constraint FKbdrctobcg03ls3djj77hxh9h6
        foreign key (seat_id)
            references seat (id);