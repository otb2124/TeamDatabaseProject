/*
SQLyog Community v12.4.3 (32 bit)
MySQL - 8.0.35 : Database - facresearchdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`facresearchdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `facresearchdb`;

/*Table structure for table `authorship` */

DROP TABLE IF EXISTS `authorship`;

CREATE TABLE `authorship` (
  `facultyId` int NOT NULL,
  `paperId` int NOT NULL,
  PRIMARY KEY (`facultyId`,`paperId`),
  KEY `fk_a_f` (`facultyId`),
  KEY `fk_a_p` (`paperId`),
  CONSTRAINT `fk_a_f` FOREIGN KEY (`facultyId`) REFERENCES `faculty` (`id`),
  CONSTRAINT `fk_a_p` FOREIGN KEY (`paperId`) REFERENCES `papers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorship` */

insert  into `authorship`(`facultyId`,`paperId`) values 
(1,1),
(1,2),
(1,3),
(1,5),
(2,3);

/*Table structure for table `faculty` */

DROP TABLE IF EXISTS `faculty`;

CREATE TABLE `faculty` (
  `id` int NOT NULL,
  `fName` varchar(45) DEFAULT NULL,
  `lName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `faculty` */

insert  into `faculty`(`id`,`fName`,`lName`,`password`,`email`) values 
(1,'Steve','Zilora','5f47859188a602594556580532e814a3','sjz@it.rit.edu'),
(2,'Dan','Bogaard','f4f6172eb26581952a70d7199bfd2ddb','dsb@it.rit.edu');

/*Table structure for table `paper_keywords` */

DROP TABLE IF EXISTS `paper_keywords`;

CREATE TABLE `paper_keywords` (
  `id` int NOT NULL,
  `keyword` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`keyword`),
  KEY `pk_p` (`id`),
  CONSTRAINT `pk_p` FOREIGN KEY (`id`) REFERENCES `papers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `paper_keywords` */

insert  into `paper_keywords`(`id`,`keyword`) values 
(1,'cognitive science'),
(1,'data mining'),
(1,'database'),
(1,'human memory'),
(2,'C#'),
(2,'IIS'),
(2,'Java'),
(2,'performance'),
(2,'PHP'),
(2,'SOAP'),
(2,'Tomcat'),
(2,'web services'),
(2,'XML'),
(3,'course assignment'),
(3,'department management'),
(3,'faculty'),
(3,'tools'),
(3,'Web 2.0'),
(5,'abduction'),
(5,'curriculum'),
(5,'education'),
(5,'FITness'),
(5,'informatics'),
(5,'IT fluency');

/*Table structure for table `papers` */

DROP TABLE IF EXISTS `papers`;

CREATE TABLE `papers` (
  `id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `abstract` text,
  `citation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `papers` */

insert  into `papers`(`id`,`title`,`abstract`,`citation`) values 
(1,'TED','A new database architecture that is modeled after the human brain. It will overcome the limitations the RDBMS architecture places on data mining.',''),
(2,'Think Inside the Box! Optimizing Web Service Performance, Today','While Web Services Technology holds great promise for universal integration, several obstacles stand in the way of its acceptance. Work is being done to address these obstacles to allow adoption of web services technology in the future, but where do we stand today? In particular, what can be done today to combat the often cited problem of slow response times for web services? While XML hardware acceleration and SOAP compression schemes can improve the overall response, the authors have found that appropriate selection of client software, server software, and data structures can have a substantial impact. It is possible to have a profound impact on performance using tools that are routinely and dependably available to us now.','Zilora, Stephen, and Sai Sanjay Ketha. \"Think Inside the Box! Optimizing Web Services Performance Today.\" IEEE Communications Magazine, 46.3 (2008): 112-117.'),
(3,'Work in Progress - Bringing Sanity to the Course Assignment Process','The floor of the NY Stock Exchange, with its noise and chaos, is an apt depiction of the typical course selection meeting that many universities experience. Professors attempt to shout over their colleagues or broker deals with one another in small cabals in an attempt to garner the sections they hope to teach. When first choices fall by the wayside, quick recalculations of schedules are necessary in order to determine the next best scenario and sections to request. As inexperienced junior faculty members, the authors found this experience daunting. In response, they wrote a simple web application that allowed faculty to make their selections, and broker deals, in a calm manner over an extended time period. The application was originally written for one sub-group of about 20 faculty within the department, but its popularity quickly spread to the rest of the department and then on to other departments within the college. The course assignment and reservation system (CARS) has grown steadily over the past several years in number of users, functionality, and scope. Today, faculty can plan their teaching load, work with colleagues to find mutually beneficial schedules, and easily retrieve historical information in preparation for annual reviews, promotion, or tenure appointments. Department administrators can manage course information, prepare information for certification agencies, assign faculty to courses, and monitor faculty loads. Staff and students also benefit from interfaces permitting access to appropriate information to assist them in their planning activities. Utilizing Web 2.0 technologies, the application is enjoyable to use and gives all of the disparate users a satisfying experience.','Zilora, S.J, and D.S Bogaard. \"Work in Progress - Bringing Sanity to the Course Assignment Process.\" Frontiers in Education Conference, 2008. FIE 2008. 38th Annual, (2008)'),
(5,'Informatics minor for non-computer students','The Rochester Institute of Technology\'s School of Informatics has developed a minor in Applied Informatics that allows non-computing students from throughout the university to learn problem solving, data retrieval, and information processing and presentation skills so that they can be productive knowledge workers in the 21st century. The minor is strongly problem-oriented with students being taught how to apply deductive, inductive, and abductive reasoning, as well as fundamental information technology skills, to problems in their specific domains. It is the coursework\'s relevance and applicability to the students\' majors that eases the acquisition of these skills.','Zilora, S.J. \"Informatics minor for non-computer students\" ACM SIGITE 2011 Conference (2011)');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
