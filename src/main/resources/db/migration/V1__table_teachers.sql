CREATE TABLE teachers (
    id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50),
    email VARCHAR(100),
    hour_rate DECIMAL(10,2)
);
