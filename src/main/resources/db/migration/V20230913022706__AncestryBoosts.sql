CREATE TABLE IF NOT EXISTS char_ancestry_flaws (
                                     id UUID PRIMARY KEY,
                                     ancestry_id UUID REFERENCES char_ancestries(id),
                                     flawed_ability varchar NOT NULL default 'None',
                                     homebrew_id INTEGER DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS char_ancestry_boosts (
                                      id UUID PRIMARY KEY,
                                      ancestry_id UUID REFERENCES char_ancestries(id),
                                      boosted_ability varchar NOT NULL default 'Anything',
                                      homebrew_id INTEGER DEFAULT NULL
);