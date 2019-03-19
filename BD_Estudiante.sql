CREATE DATABASE  IF NOT EXISTS `bd_estudiante` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_estudiante`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_estudiante
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estudiante` (
  `matricula` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `paterno` varchar(30) NOT NULL,
  `materno` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES ('a1702','Rafael','Andrade ','Méndez'),('s170','Rafa','Colorado','Fernandez');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia_educativa`
--

DROP TABLE IF EXISTS `experiencia_educativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `experiencia_educativa` (
  `idExperienciaEducativa` int(11) NOT NULL AUTO_INCREMENT,
  `nrc` varchar(6) NOT NULL,
  `nombreEE` varchar(45) NOT NULL,
  `creditos` int(11) DEFAULT NULL,
  `horasTeoricas` int(11) DEFAULT NULL,
  `horasPracticas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExperienciaEducativa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia_educativa`
--

LOCK TABLES `experiencia_educativa` WRITE;
/*!40000 ALTER TABLE `experiencia_educativa` DISABLE KEYS */;
INSERT INTO `experiencia_educativa` VALUES (3,'1245','Redes',10,120,150);
/*!40000 ALTER TABLE `experiencia_educativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario` (
  `idHorario` int(11) NOT NULL AUTO_INCREMENT,
  `horaInicio` varchar(5) DEFAULT NULL,
  `horaFin` varchar(5) DEFAULT NULL,
  `dia` varchar(10) DEFAULT NULL,
  `salon` varchar(10) DEFAULT NULL,
  `idExperienciaEducativa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idHorario`),
  KEY `idExperienciaEducativa` (`idExperienciaEducativa`),
  CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`idExperienciaEducativa`) REFERENCES `experiencia_educativa` (`idexperienciaeducativa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,'7:00','9:00','Lunes','CC3',3),(5,'9:00','11:00','Martes','CC2',3);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18 23:46:26
