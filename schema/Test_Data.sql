SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE hMOBI;

-- Adding Account
TRUNCATE TABLE account;
SET AUTOCOMMIT=0;
INSERT INTO account VALUES (1,'Clinic','ABC Clinic','2013-04-09 00:00:01'),(2,'Lab','PATH LABS','2013-04-09 00:00:01'),(3,'Hospital','Princeton-Plainsboro','2013-04-09 00:00:01');
COMMIT;

-- Addresses
TRUNCATE TABLE address;
SET AUTOCOMMIT=0;
INSERT INTO address VALUES (1,1,'C-1754 Sushant Lok - I',NULL,'Harayana','Gurgaon','122002','9898989898','India','2013-04-09 00:00:01'),(2,2,'254 Sector 56',NULL,'Harayana','Gurgaon','122002','9797979797','India','2013-04-09 00:00:01'),(3,3,'B 143 Super Mart I','DLF Phase 4','Harayana','Gurgaon','122002','9696969696','India','2013-04-09 00:00:01');
COMMIT;

-- Person
TRUNCATE TABLE person;
SET AUTOCOMMIT=0;
INSERT INTO person VALUES (1,'Arti','Sinha','Physician','2013-04-09 00:00:01'),(2,'Manu','Srivastava','Patient','2013-04-09 00:00:01'),(3,'Hardy','Singh','Physician','2013-04-09 00:00:01');
COMMIT;

-- Account Person Links
TRUNCATE TABLE acc_per_int;
SET AUTOCOMMIT=0;
INSERT INTO acc_per_int VALUES (1,1,1,'2013-04-09 00:00:01'),(3,3,3,'2013-04-09 00:00:01');
COMMIT;

-- Profile
TRUNCATE TABLE profile;
SET AUTOCOMMIT=0;
INSERT INTO profile VALUES (1,1,'2013-04-09 00:00:01'),(2,2,'2013-04-09 00:00:01'),(3,3,'2013-04-09 00:00:01');
COMMIT;

-- User
TRUNCATE TABLE user;
SET AUTOCOMMIT=0;
INSERT INTO user VALUES (1,1,'Arti','Sinha','artsin@mailinator.com',true,'arti','arti','2013-04-09 00:00:01'),(2,2,'Manu','Srivastava','mansri@mailinator.com',true,'manu','manu','2013-04-09 00:00:01'),(3,3,'Hardy','Singh','harsin@mailinator.com',true,'hardy','hardy','2013-04-09 00:00:01');
COMMIT;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;