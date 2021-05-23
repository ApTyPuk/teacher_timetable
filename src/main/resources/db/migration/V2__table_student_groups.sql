CREATE TABLE student_groups (
    id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    name VARCHAR(20) NOT NULL UNIQUE,
    education_year_start BIGINT(20) NOT NULL,
    education_year_end BIGINT(20) NOT NULL
);