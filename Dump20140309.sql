CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: test
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
-- Table structure for table `certifieddevices`
--

DROP TABLE IF EXISTS `certifieddevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certifieddevices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `make` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `info` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certifieddevices`
--

LOCK TABLES `certifieddevices` WRITE;
/*!40000 ALTER TABLE `certifieddevices` DISABLE KEYS */;
INSERT INTO `certifieddevices` VALUES (1,'HP','Pro 3330 Microtower',998.99,'DESKTOP',NULL),(2,'HP','Pavilion Slimline 400-265d',1270.00,'DESKTOP',NULL),(3,'HP','ProBook 450 G1',1399.00,'LAPTOP',NULL),(4,'HP','Elitebook Revolve 840 G1',2799.00,'LAPTOP',NULL),(5,'DELL','PowerEdge R515 Rack Server',4599.99,'SERVER','Processor: Intel Xeon processor E5-1410 | Operating System: WINDOWS'),(6,'DELL','PowerEdge T320 ',3599.99,'SERVER','Processor: Intel Xeon processor E5-1410 | Operating System: UNIX'),(7,'HP',' LaserJet Pro 200 color MFP M276nw',799.00,'PRINTER','Print: Up to 14 ppm (draft, black A4), 14 ppm (draft, colour A4)Up to 30,000 pages per month duty cycleScan: Flatbed, Up to 1200 dpi, scan size (flatbed): 216 x 297 mmCopy: Up to 99 copies; Copy resolution (black text) up to 600 x 600 dpi ; Copy resolution (color text and graphics) Up to 600 x 600Fax transmission speed: 33.6 kbpsMemory: 256MBConnectivity: 1 Hi-Speed USB 2.0 port; 1 Host USB; 1 Ethernet 10/100Base-TX network port; 1 RJ -11 (Fax); 1 RJ-11 (Phone only)'),(8,'HP','LaserJet Pro 300 color MFP M375nw',1399.00,'PRINTER','Print: Up to 18 ppm (draft, black A4), 18 ppm (draft, colour A4)Up to 30,000 pages per month duty cycleScan: Flatbed, Up to 1200 dpi, scan size (flatbed): 216 x 297 mmCopy: Up to 99 copies; Copy resolution (black text) up to 300 x 300 dpi ; Copy resolution (color text and graphics) Up to 300 x 300Fax transmission speed: 33.6 kbpsMemory: 192MBConnectivity: 1 Hi-Speed USB 2.0 port; 1 Host USB port (front-panel); 1 Fast Ethernet 10/100Base-TX network port; 1 WiFi 802.11 b/g/n; 1 RJ -11 (Fax); 1 RJ-11 (Phone only)'),(10,'TEST','TEST',999.00,'TEST','TEST'),(11,'HP','Pavilion 2',99.00,'DESKTOP','TEST'),(13,'APPLE','MacBook Air',999.99,'LAPTOP','TEST'),(14,'APPLE','MacBookPro',8888.98,'LAPTOP','TEST');
/*!40000 ALTER TABLE `certifieddevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `dept` varchar(45) DEFAULT NULL,
  `roles` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES ('111','Tom','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','IT','REQUESTER'),('222','Dick','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','Finanace','APPROVER'),('333','Harry','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','Procurement','PROCUREMENT'),('444','Dave','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','Logistics','WAREHOUSE');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` int(11) DEFAULT NULL,
  `dept` varchar(45) DEFAULT NULL,
  `approved` varchar(45) DEFAULT NULL,
  `stockid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,11,'IT','NO',11),(2,2,'IT','YES',6),(3,11,'IT','YES',4),(4,11,'IT','NO',NULL),(5,11,'IT','NO',NULL),(6,10,'IT','YES',13),(7,4,'IT','YES',NULL),(8,4,'IT','YES',14),(9,12,'IT',NULL,NULL),(10,12,'IT',NULL,NULL),(11,13,'IT','NO',NULL),(12,13,'IT','YES',10),(13,2,'IT','YES',5),(14,7,'IT','YES',8),(15,7,'IT','YES',7),(16,10,'IT','YES',12);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stockid` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` int(11) DEFAULT NULL,
  `serialno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stockid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,1,'1'),(2,1,'2'),(3,1,'3'),(4,11,'11'),(5,2,'222'),(6,2,'223'),(7,7,'655746'),(8,7,'PRINTER'),(9,13,'SERIAL'),(10,13,'ait'),(11,10,'TEST'),(12,10,'TEST'),(13,10,'123'),(14,4,'0315HP');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-09  2:40:28
