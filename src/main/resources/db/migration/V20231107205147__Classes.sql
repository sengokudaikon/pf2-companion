create table if not exists char_classes
(
    id                    uuid primary key,
    name                  varchar(255) not null,
    rarity                varchar(255) not null,
    description           text,
    key_attribute         varchar(255) not null,
    hit_points            int          not null,
    class_dc              int          not null,
    skill_increase_levels jsonb,
    general_feat_levels   jsonb,
    class_feat_levels     jsonb,
    skill_feat_levels     jsonb,
    rules                 jsonb,
    content_src           varchar(255) not null
)