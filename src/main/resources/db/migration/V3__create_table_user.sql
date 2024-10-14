CREATE TABLE epicusers (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    avatar_url VARCHAR(255),
    score INT DEFAULT 0
);