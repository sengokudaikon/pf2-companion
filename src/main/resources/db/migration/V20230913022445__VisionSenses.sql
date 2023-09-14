
CREATE TABLE IF NOT EXISTS char_ancestry_senses (
                                      id UUID PRIMARY KEY,
                                      name varchar NOT NULL,
                                      vision_range INTEGER NOT NULL,
                                      precision varchar NOT NULL
);