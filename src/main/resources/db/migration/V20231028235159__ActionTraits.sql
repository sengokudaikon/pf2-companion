create table if not exists action_traits
(
    id        uuid primary key,
    action_id uuid not null references actions (id),
    trait_id  uuid not null references traits (id)
)