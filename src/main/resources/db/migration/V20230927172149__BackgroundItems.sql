CREATE TABLE IF NOT EXISTS background_items
(
    id            uuid primary key,
    background_id uuid references backgrounds (id),
    feat          uuid references feats (id)   default null,
    action        uuid references actions (id) default null,
    spell         uuid references spells (id)  default null
)