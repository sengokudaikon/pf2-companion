create table if not exists char_class_weapons
(
    id           uuid primary key,
    class_id     uuid         not null references char_classes (id),
    weapon_class varchar(255) not null,
    proficiency  varchar(255) not null
)