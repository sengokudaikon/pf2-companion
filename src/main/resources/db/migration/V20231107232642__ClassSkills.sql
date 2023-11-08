create table if not exists char_class_skills
(
    id          uuid primary key,
    class_id    uuid         not null references char_classes (id),
    skill_id    varchar(255) not null,
    proficiency varchar(255) not null
)