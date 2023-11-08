create table if not exists char_class_specific_weapons
(
    id          uuid primary key,
    class_id    uuid references char_classes (id),
    weapon_id   uuid references items_weapons (id),
    proficiency varchar(255) not null
)