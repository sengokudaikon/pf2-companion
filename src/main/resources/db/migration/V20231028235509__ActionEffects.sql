create table if not exists action_effects
(
    id        uuid primary key,
    action_id uuid not null references actions (id),
    effect_id uuid not null references feat_effects (id)
)