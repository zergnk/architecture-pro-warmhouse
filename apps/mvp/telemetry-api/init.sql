-- Create the database if it doesn't exist
CREATE DATABASE telemetry;

-- Connect to the database
\c telemetry;

create table if not exists telemetry_data (
    id        serial primary key,
    device_id integer,
    value     real,
    status    varchar,
    time      timestamp
);

