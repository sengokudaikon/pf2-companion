CREATE TABLE IF NOT EXISTS backgrounds (
    id uuid PRIMARY KEY,
    name varchar(50) NOT NULL,
    description text NOT NULL,
    rarity varchar(10) NOT NULL,
    code varchar(10) NOT NULL,
    is_archived boolean NOT NULL DEFAULT false,
    content_src varchar(100) NOT NULL,
    homebrew_id integer default null,
    version varchar(10) NOT NULL default '1.0.0',
    trained_lore varchar(50) default null
)