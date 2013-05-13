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


-- Account user Intersection table for M:M relationship
CREATE TABLE acc_user_int (
  acc_user_int_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  accnt_id INT(11) UNSIGNED NOT NULL,
  user_id INT(11) UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (acc_user_int_id),
  KEY idx_accnt_id (accnt_id),
  KEY idx_user_id (user_id),
  CONSTRAINT accuser_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT accuser_accnt_id FOREIGN KEY (accnt_id) REFERENCES account (account_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Profile Table 1:1 with user
CREATE TABLE profile (
  profile_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT(11) UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (profile_id),
  KEY idx_profile_user_id (user_id),
  CONSTRAINT profile_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- User Table 1:1 with user
CREATE TABLE user (
  user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) DEFAULT NULL,
  last_name VARCHAR(45) DEFAULT NULL,
  email VARCHAR(50) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(40) DEFAULT NULL,
  professional_type VARCHAR(30) DEFAULT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id),
  KEY idx_User_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- User Table 1:1 with User to store preferences
CREATE TABLE user_preferences (
  user_pref_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT(11) UNSIGNED NOT NULL,
  persist_login BOOLEAN DEFAULT NULL,
  geolocation VARCHAR(100) DEFAULT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_pref_id),
  KEY idx_userpref_user__id (user_id),
  CONSTRAINT userpref_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE events (
  events_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT(11) UNSIGNED NOT NULL,
  addr_id INT(11) UNSIGNED NOT NULL,
  title VARCHAR(40) NOT NULL,
  notes TEXT DEFAULT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_on DATETIME NOT NULL,
  PRIMARY KEY(events_id),
  INDEX start_date(start_date),
  CONSTRAINT events_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT events_addr_id FOREIGN KEY (addr_id) REFERENCES address (address_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


