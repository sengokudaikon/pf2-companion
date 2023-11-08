CREATE TABLE IF NOT EXISTS char_ancestries
(
    id                  UUID PRIMARY KEY,
    img                 VARCHAR(100) NOT NULL,
    name                VARCHAR(50)  NOT NULL,
    rarity              VARCHAR(10)  NOT NULL,
    hit_points          INTEGER      NOT NULL,
    size                VARCHAR(10)  NOT NULL,
    speed               INTEGER      NOT NULL,
    description         TEXT         NOT NULL,
    vision_sense_id     uuid         NOT NULL,
    additional_sense_id uuid                  DEFAULT NULL,
    is_archived         BOOLEAN      NOT NULL DEFAULT FALSE,
    content_src         VARCHAR(100) NOT NULL,
    rules               jsonb
);