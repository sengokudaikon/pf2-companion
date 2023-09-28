CREATE TABLE IF NOT EXISTS traits
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  TEXT        NOT NULL,
    type         varchar(50) NOT NULL,
    is_hidden    BOOLEAN DEFAULT false,
    is_archived  BOOLEAN DEFAULT false,
    source       VARCHAR(255) NOT NULL,
    homebrew_id  INTEGER DEFAULT NULL
);