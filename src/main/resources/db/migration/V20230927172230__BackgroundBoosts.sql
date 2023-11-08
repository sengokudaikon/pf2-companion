CREATE TABLE IF NOT EXISTS background_boosts
(
    id              uuid PRIMARY KEY,
    background_id   uuid REFERENCES backgrounds (id),
    boosted_ability varchar(20) NOT NULL
)