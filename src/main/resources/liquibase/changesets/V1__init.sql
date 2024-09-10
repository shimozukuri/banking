create table if not exists users
(
    id bigserial primary key,
    username varchar(30) not null unique,
    password varchar(255) not null,
    birth_day timestamp,
    name varchar(30)
);

create table if not exists money_accounts
(
    id bigserial primary key,
    balance double precision not null,
    max_balance double precision not null
);

create table if not exists payments
(
    id bigserial primary key,
    amount double precision not null,
    sender_id bigserial not null,
    recipient_id bigserial not null,
    payment_date timestamptz not null
);

create table if not exists users_accounts
(
    user_id bigserial not null,
    account_id bigserial not null,
    primary key (user_id, account_id),
    constraint fk_users_accounts_users foreign key (user_id) references users (id) on delete cascade on update cascade,
    constraint fk_users_accounts_accounts foreign key (account_id) references money_accounts (id) on delete cascade on update cascade
);

create table if not exists users_roles
(
    user_id bigserial not null,
    role varchar not null,
    primary key (user_id, role),
    constraint fk_users_roles foreign key (user_id) references users (id) on delete cascade on update cascade
);

create table if not exists users_emails
(
    user_id bigserial not null,
    email varchar not null,
    primary key (user_id, email),
    constraint fk_users_emails foreign key (user_id) references users (id) on delete cascade on update cascade
);

create table if not exists users_phone_numbers
(
    user_id bigserial not null,
    phone_number varchar not null,
    primary key (user_id, phone_number),
    constraint fk_users_phone_numbers foreign key (user_id) references users (id) on delete cascade on update cascade
);