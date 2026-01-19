-- Create the database if it doesn't exist
CREATE DATABASE devices;

-- Connect to the database
\c devices;

create table if not exists device (
    id            serial primary key,
    house_id      integer,
    module_id     integer,
    state         smallint constraint device_state_check check ((state >= 0) AND (state <= 1)),
    type_id       integer,
    location      varchar(255),
    serial_number varchar(255),
    unit          varchar(255)
);

create table if not exists device_type (
    id serial primary key
);

create table if not exists module (
    id serial primary key
);

create table if not exists module_type (
    id serial primary key
);


