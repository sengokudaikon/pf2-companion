CREATE TABLE IF NOT EXISTS languages
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(1000) DEFAULT NULL,
    homebrew_id INTEGER       DEFAULT NULL
);