CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(500) UNIQUE NOT NULL,
    pass_hash VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
