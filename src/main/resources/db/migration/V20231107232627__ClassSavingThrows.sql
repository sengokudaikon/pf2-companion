create table if not exists char_class_saves
(
    id           uuid primary key,
    class_id     uuid references char_classes (id),
    saving_throw varchar(255) not null,
    proficiency  varchar(255) not null
)