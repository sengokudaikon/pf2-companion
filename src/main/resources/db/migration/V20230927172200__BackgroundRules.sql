CREATE TABLE IF NOT EXISTS background_rules(
    id uuid primary key,
    background_id uuid references backgrounds(id),
    rule uuid references rules(id)
)