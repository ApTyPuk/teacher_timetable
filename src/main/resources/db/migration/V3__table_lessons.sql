CREATE TABLE lessons (
    id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    lesson_name VARCHAR(50) NOT NULL,
    teachers_id BIGINT(20) NOT NULL,
    group_id BIGINT(20) NOT NULL,
    duration_minutes BIGINT(20) NOT NULL,
    lesson_date DATETIME NOT NULL
);