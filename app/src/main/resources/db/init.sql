CREATE TABLE IF NOT EXISTS books
(
    id UUID NOT NULL PRIMARY KEY,
    title  VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(50),
    qty INTEGER,
    description TEXT
);