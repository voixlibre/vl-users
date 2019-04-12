alter table if exists app_role_app_user
  drop constraint if exists FKftj0mdruxjyodpb4ay6hna1a2;

alter table if exists app_role_app_user
  drop constraint if exists FKi0rl707b9g0190knculbwhshs;

drop table if exists app_role_app_user cascade;

drop table if exists app_user cascade;

drop table if exists app_role cascade;

drop table if exists user_roles cascade;

create table app_role_app_user (
                                 app_role_id int4 not null,
                                 app_user_id int4 not null
);

create table app_user (
                        id  serial not null,
                        active boolean,
                        address_id int4,
                        birthday date,
                        email varchar(255) not null,
                        first_name varchar(255),
                        name varchar(255),
                        password varchar(255) not null,
                        registration_date timestamp,
                        tel varchar(255),
                        primary key (id)
);

create table app_role (
                        id  serial not null,
                        role_name varchar(255),
                        primary key (id)
);

alter table if exists app_role_app_user
  add constraint FKftj0mdruxjyodpb4ay6hna1a2
    foreign key (app_user_id)
      references app_user;

alter table if exists app_role_app_user
  add constraint FKi0rl707b9g0190knculbwhshs
    foreign key (app_role_id)
      references app_role;