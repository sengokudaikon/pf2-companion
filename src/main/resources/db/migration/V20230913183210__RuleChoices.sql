CREATE TABLE IF NOT EXISTS rule_choices
(
    id      uuid PRIMARY KEY,
    rule_id uuid NOT NULL,
    name    text NOT NULL,
    value   text NOT NULL
)