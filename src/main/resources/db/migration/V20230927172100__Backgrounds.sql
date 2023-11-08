CREATE TABLE IF NOT EXISTS backgrounds
(
    id           uuid PRIMARY KEY,
    name         varchar(50)  NOT NULL,
    description  text         NOT NULL,
    rarity       varchar(10)  NOT NULL,
    code         varchar(10)  NOT NULL,
    is_archived  boolean      NOT NULL DEFAULT false,
    content_src  varchar(100) NOT NULL,
    rules        jsonb,
    trained_lore varchar(50)           default null
)