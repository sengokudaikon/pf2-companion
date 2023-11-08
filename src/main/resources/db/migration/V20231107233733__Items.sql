create table if not exists items
(
    id                 uuid primary key,
    name               varchar(255) not null,
    description        text,
    cost               int          not null,
    weight             int          not null,
    level              int          not null,
    rarity             varchar(255) not null,
    item_type          varchar(255) not null,
    craft_requirements text,
    usage              text,
    hands              varchar(255),
    size               varchar(255)
)