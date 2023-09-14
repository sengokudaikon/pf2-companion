CREATE TABLE IF NOT EXISTS char_ancestry_physical_features (
                                                 id UUID PRIMARY KEY,
                                                 ancestry_id UUID REFERENCES char_ancestries(id),
                                                 img VARCHAR(255) NOT NULL,
                                                 level INT NOT NULL,
                                                 name VARCHAR(255) NOT NULL,
                                                 description TEXT NOT NULL
);