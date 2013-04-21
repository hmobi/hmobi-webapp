-- hMOBI Database Schema

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=TRADITIONAL;

DROP SCHEMA IF EXISTS hMOBI;
CREATE SCHEMA hMOBI;
USE hMOBI;

-- Account Table: To be used for Cinics/Practices/Labs etc. Organization Level:
CREATE TABLE account (
  account_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  record_type VARCHAR(30) NOT NULL,
  Name VARCHAR(100) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (account_id),
  KEY idx_account_record_type (record_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Address Table: Will store the addresses/Location for various Organizations (Accounts)
CREATE TABLE address (
  address_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  accnt_id INT(11) UNSIGNED NOT NULL,
  address VARCHAR(50) NOT NULL,
  address2 VARCHAR(50) DEFAULT NULL,
  state VARCHAR(30) NOT NULL,
  city VARCHAR(50) NOT NULL,
  postal_code VARCHAR(10) DEFAULT NULL,
  phone_mobile VARCHAR(20) NOT NULL,
  country VARCHAR(50),
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (address_id),
  KEY con_accnt_id_idx (accnt_id),
  KEY idx_address_city (city),
  KEY idx_address_state (state),
  CONSTRAINT addr_acc_id FOREIGN KEY (accnt_id) REFERENCES account (account_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Person Table: Stores the Contact Information of HCP (Medical Professionals, Doctor's staff) and Patients
CREATE TABLE person (
  person_id INT(11) UNSIGNED NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  professional_type VARCHAR(30) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (person_id),
  KEY idx_person_last_name (last_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Account Person Intersection table for M:M relationship
CREATE TABLE acc_per_int (
  acc_per_int_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  accnt_id INT(11) UNSIGNED NOT NULL,
  person_id INT(11) UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (acc_per_int_id),
  KEY idx_accnt_id (accnt_id),
  KEY idx_person_id (person_id),
  CONSTRAINT accper_person_id FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT accper_accnt_id FOREIGN KEY (accnt_id) REFERENCES account (account_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Profile Table 1:1 with person
CREATE TABLE profile (
  profile_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  per_id INT(11) UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (profile_id),
  KEY idx_profile_per_id (per_id),
  CONSTRAINT profile_per_id FOREIGN KEY (per_id) REFERENCES person (person_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- User Table 1:1 with Person
CREATE TABLE user (
  user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  per_id INT(11) UNSIGNED DEFAULT NULL,
  first_name VARCHAR(45) DEFAULT NULL,
  last_name VARCHAR(45) DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(40) BINARY NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id),
  KEY idx_user_per_id (per_id),
  KEY idx_User_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- User Table 1:1 with User to store preferences
CREATE TABLE user_preferences (
  user_pref_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT(11) UNSIGNED NOT NULL,
  persisit_login BOOLEAN DEFAULT NULL,
  geolocation VARCHAR(100) DEFAULT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_pref_id),
  KEY idx_userpref_user__id (user_id)
  CONSTRAINT userpref_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
