create table if not exists items_weapons
(
    id           uuid primary key,
    item_id      uuid references items (id),
    name         varchar(255) not null,
    damage_type  varchar(255) not null,
    damage_dice  varchar(255) not null,
    weapon_class varchar(255) not null,
    weapon_type  varchar(255) not null,
    range        int          not null default 0,
    reload       int                   default null
)