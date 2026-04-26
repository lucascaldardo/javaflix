CREATE TABLE filme(
    id serial PRIMARY KEY,
    titulo varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);