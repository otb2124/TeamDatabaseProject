/*
SQLyog Community v12.4.3 (32 bit)
MySQL - 8.0.35 : Database - db2_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db2_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db2_project`;

/*Table structure for table `courses` */

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `UserId` int NOT NULL,
  `CourseId` int NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `CourseNumber` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`CourseId`),
  KEY `fk_c_u` (`UserId`),
  CONSTRAINT `fk_c_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `courses` */

/*Table structure for table `grants` */

DROP TABLE IF EXISTS `grants`;

CREATE TABLE `grants` (
  `UserId` int NOT NULL,
  `GrantId` int NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Citation` varchar(145) DEFAULT NULL,
  `Tease` text,
  `Amount` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`GrantId`),
  KEY `fk_g_u` (`UserId`),
  CONSTRAINT `fk_g_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `grants` */

insert  into `grants`(`UserId`,`GrantId`,`Year`,`Citation`,`Tease`,`Amount`,`Status`) values 
(1,1,'11-12','CER: Encouraging STEM Studies with Informatics” to National Science Foundation, PI-Steve Zilora, Co-PI- Robert Parody','Show kids how to use computing tools to track their favorite bands, movies, etc.','$500k','Submitted'),
(1,2,'10-11','Sub-award to RGH R01 project” from NIH, PI-Steve Zilora','Work with RGH to address ear infections in kids.','$28k','Awarded');

/*Table structure for table `kudos` */

DROP TABLE IF EXISTS `kudos`;

CREATE TABLE `kudos` (
  `UserId` int NOT NULL,
  `KudoId` int NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Kudo` text,
  PRIMARY KEY (`UserId`,`KudoId`),
  KEY `fk_k_u` (`UserId`),
  CONSTRAINT `fk_k_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kudos` */

insert  into `kudos`(`UserId`,`KudoId`,`Year`,`Kudo`) values 
(1,1,'11-12','Ran bootcamp for project management'),
(1,2,'11-12','wrote several new courses for semesters');

/*Table structure for table `pubs` */

DROP TABLE IF EXISTS `pubs`;

CREATE TABLE `pubs` (
  `UserId` int NOT NULL,
  `PubId` int NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Citation` varchar(145) DEFAULT NULL,
  `Tease` text,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`PubId`),
  KEY `fk_p_u` (`UserId`),
  CONSTRAINT `fk_p_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pubs` */

insert  into `pubs`(`UserId`,`PubId`,`Year`,`Citation`,`Tease`,`Status`) values 
(1,1,'11-12','Informatics Minor for Non-Computer Students”, ACM SIGITE’11, West Point, NY, 10/2011','Bring digital literacy to social science students.','Published'),
(1,2,'11-12','\"STEM Collaboration Cubed”, ASEE/IEEE Frontiers in Education Conference 2011, Rapid City, South Dakota','Teach informatics to HS kids.','Published');

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `UserId` int NOT NULL,
  `ServiceId` int NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`ServiceId`),
  KEY `fk_s_u` (`UserId`),
  CONSTRAINT `fk_s_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `service` */

insert  into `service`(`UserId`,`ServiceId`,`Year`,`Description`,`Role`) values 
(1,1,'11-12','SIGCSE 2012 Conference','Reviewer'),
(1,2,'11-12','2012 ASEE Annual Conference','Reviewer'),
(1,3,'11-12','Institute Eisenhart Committee ','Chair');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `FName` varchar(45) DEFAULT NULL,
  `LName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Pswd` varchar(45) DEFAULT NULL,
  `Role` int DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`UserId`,`FName`,`LName`,`Email`,`Pswd`,`Role`) values 
(1,'Steve','Zilora','sjz@it.rit.edu','5f47859188a602594556580532e814a3',1),
(2,'Dan','Bogaard','dsb@it.rit.edu','f4f6172eb26581952a70d7199bfd2ddb',3),
(3,'Karen','Griffith','kdgvks@rit.edu','084387d79f1cae0cecd9a8eaccbd23b3',2),
(4,'Baltasar','Fernandez','bf4680@g.rit.edu','a2e10d9799fb5263ff1c27cd912e0c4a',2),
(5,'3','3','3','eccbc87e4b5ce2fe28308fd9f2a7baf3',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
