CREATE TABLE IF NOT EXISTS char_ancestry_rules (
    id uuid PRIMARY KEY,
    ancestry_id uuid NOT NULL,
    rule_id uuid NOT NULL
)