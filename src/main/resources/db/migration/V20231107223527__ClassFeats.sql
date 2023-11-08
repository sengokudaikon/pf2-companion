create table if not exists char_class_features
(
    id                  uuid primary key,
    class_id            uuid         not null references char_classes (id),
    img                 text,
    name                varchar(255) not null,
    action_type         varchar(255) not null,
    actions             varchar(255) not null,
    category            varchar(255) not null,
    description         text         not null,
    level               int          not null,
    rules               jsonb,
    content_src         text,
    type                varchar(255) not null default 'class_feat',
    can_select_multiple boolean      not null default false,
    is_default          boolean      not null default false
)