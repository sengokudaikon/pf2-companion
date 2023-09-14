CREATE TABLE char_ancestry_traits
(
    id          UUID PRIMARY KEY,
    ancestry_id UUID REFERENCES char_ancestries (id) DEFAULT NULL,
    trait_id    UUID REFERENCES traits (id)          DEFAULT NULL
);