create table if not exists char_class_armour
(
    id                 uuid primary key,
    class_id           uuid         not null references char_classes (id),
    armour_class       varchar(255) not null,
    armour_proficiency varchar(255) not null
)