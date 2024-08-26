-- Create table script for learner
CREATE TABLE learner (
    learner_id INT NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(255) NOT NULL,
    gender VARCHAR(20),
    birthdate DATE,
    address VARCHAR(255),
    contact VARCHAR(20),
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (learner_id)
);

--Create table script for enrollments 
CREATE TABLE enrolments (
    enrollment_id INT NOT NULL AUTO_INCREMENT,
    learner_id INT NOT NULL,
    academy_id INT NOT NULL,
    enrollment_date DATE,
    PRIMARY KEY (enrollment_id),
    FOREIGN KEY (learner_id) REFERENCES learner(learner_id),
    
);