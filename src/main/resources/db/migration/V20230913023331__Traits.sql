CREATE TABLE traits
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    description  TEXT        NOT NULL,
    is_important BOOLEAN     NOT NULL,
    is_hidden    BOOLEAN DEFAULT false,
    is_archived  BOOLEAN DEFAULT false,
    source       VARCHAR(50) NOT NULL,
    homebrew_id  INTEGER DEFAULT NULL
);