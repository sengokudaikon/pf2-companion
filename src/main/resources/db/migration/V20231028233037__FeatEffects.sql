CREATE TABLE IF NOT EXISTS feat_effects
(
    id          uuid PRIMARY KEY,
    feat_id     uuid REFERENCES feats (id) ON DELETE CASCADE,
    img         varchar(255),
    name        varchar(255),
    description text,
    level       int,
    content_src text,
    rules       jsonb,
    expiry      varchar(100) not null,
    sustained   boolean      not null,
    unit        varchar(100) not null,
    value       int          not null,
    rarity      varchar(255)
)