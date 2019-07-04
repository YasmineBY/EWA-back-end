-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema aquadinedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema aquadinedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aquadinedb` DEFAULT CHARACTER SET latin1 ;
USE `aquadinedb` ;

-- -----------------------------------------------------
-- Table `aquadinedb`.`invitelist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`invitelist` (
                                                         `listId` INT(11) NOT NULL,
                                                         PRIMARY KEY (`listId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`event` (
                                                    `eventId` INT(11) NOT NULL,
                                                    `eventDate` VARCHAR(255) NULL DEFAULT NULL,
                                                    `eventDetails` VARCHAR(255) NULL DEFAULT NULL,
                                                    `eventName` VARCHAR(255) NULL DEFAULT NULL,
                                                    `locatie` VARCHAR(255) NULL DEFAULT NULL,
                                                    `restaurant` VARCHAR(255) NULL DEFAULT NULL,
                                                    `invitelist_listId` INT(11) NULL DEFAULT NULL,
                                                    PRIMARY KEY (`eventId`),
                                                    INDEX `FK2jcftn2npjmr95js70r3kyghv` (`invitelist_listId` ASC),
                                                    CONSTRAINT `FK2jcftn2npjmr95js70r3kyghv`
                                                        FOREIGN KEY (`invitelist_listId`)
                                                            REFERENCES `aquadinedb`.`invitelist` (`listId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`invite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`invite` (
                                                     `email` VARCHAR(255) NOT NULL,
                                                     `invitelist` INT(11) NOT NULL,
                                                     PRIMARY KEY (`email`, `invitelist`),
                                                     INDEX `FK4ae9y9mw1rkhj1nuxu0hk7mne` (`invitelist` ASC),
                                                     CONSTRAINT `FK4ae9y9mw1rkhj1nuxu0hk7mne`
                                                         FOREIGN KEY (`invitelist`)
                                                             REFERENCES `aquadinedb`.`invitelist` (`listId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`product` (
                                                      `productname` VARCHAR(255) NOT NULL,
                                                      PRIMARY KEY (`productname`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`user` (
                                                   `email` VARCHAR(255) NOT NULL,
                                                   `groupname` VARCHAR(255) NULL DEFAULT NULL,
                                                   `name` VARCHAR(255) NULL DEFAULT NULL,
                                                   `password` VARCHAR(255) NULL DEFAULT NULL,
                                                   PRIMARY KEY (`email`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`user_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`user_event` (
                                                         `User_email` VARCHAR(255) NOT NULL,
                                                         `event_eventId` INT(11) NOT NULL,
                                                         PRIMARY KEY (`User_email`, `event_eventId`),
                                                         UNIQUE INDEX `UK_fqvjdpsf8rlp31h8c7a655udm` (`event_eventId` ASC),
                                                         CONSTRAINT `FK5782v3v0rc0335iy3bdco4hfd`
                                                             FOREIGN KEY (`User_email`)
                                                                 REFERENCES `aquadinedb`.`user` (`email`),
                                                         CONSTRAINT `FKlrn0vwoj9qkjsauv3jy9pn5np`
                                                             FOREIGN KEY (`event_eventId`)
                                                                 REFERENCES `aquadinedb`.`event` (`eventId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `aquadinedb`.`user_has_invitelist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aquadinedb`.`user_has_invitelist` (
                                                                  `invitelist_email` VARCHAR(255) NOT NULL,
                                                                  `invitelists_listId` INT(11) NOT NULL,
                                                                  PRIMARY KEY (`invitelist_email`, `invitelists_listId`),
                                                                  INDEX `FKtoaupwntj9ch2o8qwcpmmnu5q` (`invitelists_listId` ASC),
                                                                  CONSTRAINT `FK820r0s36pb1fmglm51cv3rrmy`
                                                                      FOREIGN KEY (`invitelist_email`)
                                                                          REFERENCES `aquadinedb`.`user` (`email`),
                                                                  CONSTRAINT `FKtoaupwntj9ch2o8qwcpmmnu5q`
                                                                      FOREIGN KEY (`invitelists_listId`)
                                                                          REFERENCES `aquadinedb`.`invitelist` (`listId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
