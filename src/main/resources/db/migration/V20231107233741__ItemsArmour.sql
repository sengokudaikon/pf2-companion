create table if not exists items_armour
(
    id            uuid primary key,
    item_id       uuid         not null references items (id),
    name          text         not null,
    proficiency   varchar(255) not null,
    ac_bonus      int          not null,
    dex_cap       int          not null,
    strength      int          not null default 0,
    check_penalty int          not null default 0,
    speed_penalty int          not null default 0,
    armor_type    varchar(255) not null
)