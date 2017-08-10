CREATE DATABASE  IF NOT EXISTS `students_ms` ;

USE `students_ms`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `account_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `fk_account_role_idx` (`role_id`),
  CONSTRAINT `fk_accountrole_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_accountrole_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE `discipline` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `discipline` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `duration` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `term_discipline`
--

DROP TABLE IF EXISTS `term_discipline`;
CREATE TABLE `term_discipline` (
  `term_id` int(10) unsigned NOT NULL,
  `discipline_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`term_id`,`discipline_id`),
  KEY `fk_term_discipline_idx` (`discipline_id`),
  CONSTRAINT `fk_termdiscipline_term` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_termdiscipline_discipline` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `groupe` varchar(255) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mark` int(2) unsigned DEFAULT NULL,
   `term_id` int(10) unsigned NOT NULL,
   `student_id` int(10) unsigned NOT NULL,
  `discipline_id` int(10) unsigned NOT NULL,
  KEY `fk_mark_idx` (`id`),
  CONSTRAINT `fk_mark_term` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mark_discipline` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mark_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Insert default data
--
INSERT INTO `account` (`login`,`password`) VALUES ("admin","admin123");
INSERT INTO `account` (`login`,`password`) VALUES ("student","student123");
INSERT INTO `role` (`role`) VALUE ("ADMIN");
INSERT INTO `role` (`role`) VALUE ("STUDENT");
INSERT INTO `account_role` (`account_id`,`role_id`) VALUES (1,1);
INSERT INTO `account_role` (`account_id`,`role_id`) VALUES (2,2)