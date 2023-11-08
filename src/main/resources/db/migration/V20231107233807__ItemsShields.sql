create table if not exists items_shields
(
    id               uuid primary key,
    item_id          uuid references items (id) not null,
    ac_bonus         int                        not null,
    hp               int                        not null,
    hardness         int                        not null,
    broken_threshold int                        not null,
    hidden           boolean                    not null default false
)