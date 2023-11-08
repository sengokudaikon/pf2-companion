CREATE TABLE IF NOT EXISTS spells
(
    id                  uuid PRIMARY KEY,
    name                varchar(50)  NOT NULL,
    level               integer      NOT NULL,
    school              varchar(50)  NOT NULL,
    cast_time           varchar(50)  NOT NULL,
    components          varchar(50)  NOT NULL,
    range               varchar(50)  NOT NULL,
    cost                varchar(50)  NOT NULL,
    trigger             varchar(50)  NOT NULL,
    requirements        varchar(50)  NOT NULL,
    targets             varchar(50)  NOT NULL,
    duration            varchar(50)  NOT NULL,
    saves               varchar(20)  NOT NULL,
    rarity              varchar(10)  NOT NULL,
    description         text         NOT NULL,
    damage_dice         varchar(10)  NULL,
    damage_type         varchar(10)  NULL,
    heighten_every      integer      NULL,
    heighten_dice       varchar(10)  NULL,
    heighten_multiplier integer      NULL,
    is_focus            boolean default false,
    is_archived         boolean default false,
    content_src         varchar(100) NOT NULL,
    rules               jsonb
)