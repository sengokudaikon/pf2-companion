CREATE TABLE IF NOT EXISTS feats
(
    id                  uuid primary key,
    name                varchar(50),
    description         text,
    action              varchar(20),
    type                varchar(20),
    level               integer,
    rarity              varchar(10),
    requirements        text,
    frequency           text,
    trigger             text,
    can_select_multiple boolean,
    is_default          boolean default false,
    skill_id            varchar(20),
    proficiency_id      varchar(20),
    content_src         varchar(100),
    is_archived         boolean default false,
    rules               jsonb
)