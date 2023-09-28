CREATE TABLE IF NOT EXISTS background_skills(
    id uuid primary key,
    background_id uuid references backgrounds(id),
    skill varchar(50) not null,
    skill_proficiency varchar(50) not null default 'UNTRAINED'
)