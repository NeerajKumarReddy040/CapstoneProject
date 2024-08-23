-- Create table script for Academy
CREATE TABLE coaching_academy_details (
    academy_id INT NOT NULL AUTO_INCREMENT,
    academy_name VARCHAR(255) NOT NULL,
    sport_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20),
    email_address VARCHAR(255),
    postcode VARCHAR(10),
    PRIMARY KEY (academy_id)
);


