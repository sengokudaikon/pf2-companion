CREATE TABLE IF NOT EXISTS actions(
    id uuid PRIMARY KEY,
    name varchar(255) NOT NULL,
    image varchar(255) NOT NULL,
    description text NOT NULL,
    action_type varchar(50) NOT NULL,
    action_number integer default null,
    category varchar(50) NOT NULL,
    frequency varchar(255) default '',
    requirements text default '',
    content_src varchar(255) default '',
    trigger text default ''
)