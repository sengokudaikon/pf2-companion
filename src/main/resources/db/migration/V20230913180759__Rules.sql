CREATE TABLE IF NOT EXISTS rules (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT NOT NULL,
                       mode varchar(255) NOT NULL,
                       priority INT NOT NULL,
                       prompt VARCHAR(255) DEFAULT NULL,
                       path VARCHAR(255) DEFAULT NULL,
                       is_archived BOOLEAN DEFAULT FALSE,
                       content_src VARCHAR(255) DEFAULT NULL,
                       version INT DEFAULT 1,
                       homebrew_id VARCHAR(255) DEFAULT NULL
);