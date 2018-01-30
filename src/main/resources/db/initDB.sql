SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema repair_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema repair_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `repair_service` DEFAULT CHARACTER SET utf8 ;
USE `repair_service` ;

-- -----------------------------------------------------
-- Table `repair_service`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_service`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `role` ENUM('CLIENT', 'MANAGER', 'MASTER', 'ADMIN') NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `repair_service`.`malfunction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_service`.`malfunction_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `repair_day` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `repair_service`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_service`.`application` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `creation_date` DATE NOT NULL,
  `malfunction_type` INT(11) NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `client_id` INT(11) NOT NULL,
  `manager_id` INT(11) NULL DEFAULT NULL,
  `master_id` INT(11) NULL DEFAULT NULL,
  `price` BIGINT(20) NULL DEFAULT NULL,
  `status` ENUM('TODO', 'INPROGRESS', 'DONE', 'CANCELED') NOT NULL,
  `completion_date` DATE NULL DEFAULT NULL,
  `service_comment` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `malfunction_type_application_fk_idx` (`malfunction_type` ASC),
  INDEX `client_id_user_fk_idx` (`client_id` ASC),
  INDEX `manager_user_fk_idx` (`manager_id` ASC),
  INDEX `master_user_fk_idx` (`master_id` ASC),
  CONSTRAINT `client_id_user_fk`
    FOREIGN KEY (`client_id`)
    REFERENCES `repair_service`.`user` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `malfunction_type_application_fk`
    FOREIGN KEY (`malfunction_type`)
    REFERENCES `repair_service`.`malfunction_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `manager_user_fk`
    FOREIGN KEY (`manager_id`)
    REFERENCES `repair_service`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `master_user_fk`
    FOREIGN KEY (`master_id`)
    REFERENCES `repair_service`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `repair_service`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_service`.`review` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) NOT NULL,
  `client_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `review_client_user_fk_idx` (`client_id` ASC),
  CONSTRAINT `review_client_user_fk`
    FOREIGN KEY (`client_id`)
    REFERENCES `repair_service`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
