create table items
(
    id          serial primary key,
    name        varchar(255) not null,
    description varchar      not null,
    price       int          not null,
    currency    varchar      not null
);

create table clients
(
    id    serial primary key,
    email varchar(255) not null
);


create table orders
(
    order_id      serial primary key,
    creation_time timestamp not null,
    is_paid       boolean default false,
    client_id     int       not null,
    foreign key (client_id) references clients (id)
);

create table orders_items
(
    id       serial primary key,
    order_id int not null,
    item_id  int not null,
    quantity int not null,
    foreign key (order_id) references orders (order_id),
    foreign key (item_id) references items (id)
);