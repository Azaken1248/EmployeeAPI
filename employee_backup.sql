-- MySQL dump 10.13  Distrib 8.0.45, for Linux (x86_64)
--
-- Host: localhost    Database: employee_new
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT,
  `budget` decimal(38,2) NOT NULL,
  `dept_name` varchar(255) NOT NULL,
  `location_id` bigint NOT NULL,
  `manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  KEY `FKrf9pmd4xvkiuh46soainevapk` (`location_id`),
  KEY `FKmp68t6xcos91gmiaj59fi077o` (`manager_id`),
  CONSTRAINT `FKmp68t6xcos91gmiaj59fi077o` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `FKrf9pmd4xvkiuh46soainevapk` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,500000.00,'Software',1,NULL),(2,300000.00,'Sales',2,NULL),(3,150000.00,'HR',3,NULL),(6,4500000.00,'Supply Chain',1,1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `emp_name` varchar(255) NOT NULL,
  `hire_date` date NOT NULL,
  `phone` varchar(255) NOT NULL,
  `salary` decimal(38,2) NOT NULL,
  `status` varchar(255) NOT NULL,
  `dept_id` bigint NOT NULL,
  `manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `UKfopic1oh5oln2khj8eat6ino0` (`email`),
  KEY `FKaqchbcb8i6nvtl9g6c72yba0p` (`dept_id`),
  KEY `FKou6wbxug1d0qf9mabut3xqblo` (`manager_id`),
  CONSTRAINT `FKaqchbcb8i6nvtl9g6c72yba0p` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`),
  CONSTRAINT `FKou6wbxug1d0qf9mabut3xqblo` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'john.doe@company.com','John Doe','2020-01-15','9876543210',75000.00,'ACTIVE',1,NULL),(2,'jane.smith@company.com','Jane Smith','2021-03-20','9876543211',65000.00,'ACTIVE',1,NULL),(3,'bob.johnson@company.com','Bob Johnson','2019-06-10','9876543212',70000.00,'ACTIVE',2,NULL),(4,'alice.williams@company.com','Alice Williams','2022-01-05','9876543213',60000.00,'ACTIVE',2,NULL),(5,'charlie.brown@company.com','Charlie Brown','2020-09-12','9876543214',55000.00,'ACTIVE',3,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'New York','USA','NY'),(2,'Los Angeles','USA','CA'),(3,'Chicago','USA','IL');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-15  9:50:10
