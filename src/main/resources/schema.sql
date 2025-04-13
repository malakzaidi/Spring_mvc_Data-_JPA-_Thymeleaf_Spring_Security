create table if not exists users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table if not exists authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));

DROP INDEX IF EXISTS ix_auth_username ON authorities;

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);