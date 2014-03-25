CREATE DATABASE  IF NOT EXISTS `project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `project`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `patient_user_id` char(20) NOT NULL,
  `doctor_user_id` char(20) NOT NULL,
  `scheduled_date` datetime NOT NULL,
  `status` enum('scheduled','cancelled','done') NOT NULL,
  `proc` char(30) NOT NULL,
  `estimated_length` time NOT NULL,
  PRIMARY KEY (`patient_user_id`,`doctor_user_id`,`scheduled_date`),
  KEY `doctor_user_id` (`doctor_user_id`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`patient_user_id`) REFERENCES `doctor_patient` (`patient_user_id`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`doctor_user_id`) REFERENCES `doctor_patient` (`doctor_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `user_id` char(20) NOT NULL,
  `specialization` char(30) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('cc','general surgeon');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_patient`
--

DROP TABLE IF EXISTS `doctor_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_patient` (
  `patient_user_id` char(20) NOT NULL,
  `doctor_user_id` char(20) NOT NULL,
  `permission` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`patient_user_id`,`doctor_user_id`),
  KEY `doctor_user_id` (`doctor_user_id`),
  CONSTRAINT `doctor_patient_ibfk_1` FOREIGN KEY (`patient_user_id`) REFERENCES `patient` (`user_id`),
  CONSTRAINT `doctor_patient_ibfk_2` FOREIGN KEY (`doctor_user_id`) REFERENCES `doctor` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_patient`
--

LOCK TABLES `doctor_patient` WRITE;
/*!40000 ALTER TABLE `doctor_patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_staff`
--

DROP TABLE IF EXISTS `doctor_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_staff` (
  `doctor_user_id` char(20) NOT NULL,
  `staff_user_id` char(20) NOT NULL,
  `permission` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`doctor_user_id`,`staff_user_id`),
  KEY `staff_user_id` (`staff_user_id`),
  CONSTRAINT `doctor_staff_ibfk_1` FOREIGN KEY (`doctor_user_id`) REFERENCES `doctor` (`user_id`),
  CONSTRAINT `doctor_staff_ibfk_2` FOREIGN KEY (`staff_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_staff`
--

LOCK TABLES `doctor_staff` WRITE;
/*!40000 ALTER TABLE `doctor_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `user_id` char(20) NOT NULL,
  `address` char(30) NOT NULL,
  `current_health` text NOT NULL,
  `ohip` char(12) NOT NULL,
  `phone` tinyint(10) NOT NULL,
  `sin` tinyint(9) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('pb','address2','bad','def',9,8),('ra','address1','good','abc',10,9),('td','address4','good','jkl',7,6);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `name` char(30) NOT NULL,
  `alias` char(30) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES ('acetaminophen','Tylenol NO.3','Used to treat mild to moderate pain.');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `first_name` char(20) NOT NULL,
  `last_name` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `type` enum('patient','doctor','staff','financial officer') NOT NULL,
  `email` char(30) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('cc','Charles','C','charlesc','patient','cc@gmail.com'),('pb','Peter','B','peterb','patient','pb@gmail.com'),('ra','Ron','A','rona','patient','ra@gmail.com'),('td','Ted','D','tedd','patient','td@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitationrecord`
--

DROP TABLE IF EXISTS `visitationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitationrecord` (
  `patient_user_id` char(20) NOT NULL,
  `doctor_user_id` char(20) NOT NULL,
  `visit_date` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `length_of_visit` time NOT NULL,
  `proc` char(30) DEFAULT NULL,
  `scheduling_of_treatment` datetime DEFAULT NULL,
  `freeform_comments` text,
  `surgery_performed` char(30) DEFAULT NULL,
  `diagnosis` text NOT NULL,
  `prescription_name` char(20) NOT NULL,
  PRIMARY KEY (`patient_user_id`,`doctor_user_id`,`visit_date`,`updated_at`),
  KEY `doctor_user_id` (`doctor_user_id`),
  KEY `prescription_name` (`prescription_name`),
  CONSTRAINT `visitationrecord_ibfk_1` FOREIGN KEY (`patient_user_id`) REFERENCES `doctor_patient` (`patient_user_id`),
  CONSTRAINT `visitationrecord_ibfk_2` FOREIGN KEY (`doctor_user_id`) REFERENCES `doctor_patient` (`doctor_user_id`),
  CONSTRAINT `visitationrecord_ibfk_3` FOREIGN KEY (`prescription_name`) REFERENCES `prescription` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitationrecord`
--

LOCK TABLES `visitationrecord` WRITE;
/*!40000 ALTER TABLE `visitationrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitationrecord` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-24 18:01:59
