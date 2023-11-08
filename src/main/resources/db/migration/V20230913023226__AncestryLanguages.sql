CREATE TABLE IF NOT EXISTS char_ancestry_languages
(
    id          UUID PRIMARY KEY,
    ancestry_id UUID REFERENCES char_ancestries (id),
    language_id UUID REFERENCES languages (id),
    is_bonus    BOOLEAN NOT NULL DEFAULT FALSE
);