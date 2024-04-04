CREATE TABLE IF NOT EXISTS public.books (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    title  VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(50),
    qty INTEGER,
    description TEXT,
    PRIMARY KEY (id)
);