create table if not exists spells
(
    id                   uuid primary key,
    img                  varchar(50),
    name                 varchar(50),
    ability              varchar(50) default null,
    area                 varchar(50) default null,
    category             varchar(50) default 'spell',
    level                integer,
    castTime             varchar(50),
    range                varchar(50),
    cost                 varchar(50),
    damage_dice          varchar(50),
    damage_type          varchar(50),
    description          text,
    trigger              varchar(50),
    duration             varchar(50),
    has_counteract_check boolean,
    content_src          varchar(50)
)
