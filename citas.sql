CREATE DATABASE  IF NOT EXISTS `citas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `citas`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: citas
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `catalogo_area`
--

DROP TABLE IF EXISTS `catalogo_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_area` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(3) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`,`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_area`
--

LOCK TABLES `catalogo_area` WRITE;
/*!40000 ALTER TABLE `catalogo_area` DISABLE KEYS */;
INSERT INTO `catalogo_area` VALUES (1,'001','Consulta Externa',1),(2,'002','Medicina Interna',1),(3,'003','Ginecobtetricia',1);
/*!40000 ALTER TABLE `catalogo_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_medicos`
--

DROP TABLE IF EXISTS `catalogo_medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_medicos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(50) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `primer_apellido` varchar(100) NOT NULL,
  `segundo_apellido` varchar(100) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`,`matricula`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_medicos`
--

LOCK TABLES `catalogo_medicos` WRITE;
/*!40000 ALTER TABLE `catalogo_medicos` DISABLE KEYS */;
INSERT INTO `catalogo_medicos` VALUES (1,'11111','Armando','Perez','Pedraza',0),(2,'22222','Luisillo','Sanchez','Gonzalez',0),(3,'33333','Dante','Martinez','Gonzalez',0),(4,'44444','Valencia','Diaz','Perez',0);
/*!40000 ALTER TABLE `catalogo_medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_tipocita`
--

DROP TABLE IF EXISTS `catalogo_tipocita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_tipocita` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(3) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`,`codigo`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_tipocita`
--

LOCK TABLES `catalogo_tipocita` WRITE;
/*!40000 ALTER TABLE `catalogo_tipocita` DISABLE KEYS */;
INSERT INTO `catalogo_tipocita` VALUES (1,'001','Cita',1),(2,'002','Tratamiento',1),(3,'003','Analisis',1),(4,'004','revision',1);
/*!40000 ALTER TABLE `catalogo_tipocita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_turno`
--

DROP TABLE IF EXISTS `catalogo_turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_turno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`,`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_turno`
--

LOCK TABLES `catalogo_turno` WRITE;
/*!40000 ALTER TABLE `catalogo_turno` DISABLE KEYS */;
INSERT INTO `catalogo_turno` VALUES (1,'001','Matutino',1),(2,'002','Vespertino',1),(3,'003','Sabatino',1);
/*!40000 ALTER TABLE `catalogo_turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_citas`
--

DROP TABLE IF EXISTS `datos_citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_citas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_cita` date NOT NULL,
  `hora_cita` varchar(20) NOT NULL,
  `estado_cita` varchar(20) NOT NULL,
  `estado` int NOT NULL,
  `idPaciente` varchar(150) NOT NULL,
  `idTipoCita` varchar(3) NOT NULL,
  `idConsultorio` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Citas_Paciente_idx` (`idPaciente`),
  KEY `FK_Citas_Consultorio_idx` (`idConsultorio`),
  KEY `FK_Citas_TipoCita_idx` (`idTipoCita`),
  CONSTRAINT `FK_Citas_Consultorio` FOREIGN KEY (`idConsultorio`) REFERENCES `datos_consultorio` (`codigo`),
  CONSTRAINT `FK_Citas_Paciente` FOREIGN KEY (`idPaciente`) REFERENCES `datos_paciente` (`credencial`),
  CONSTRAINT `FK_Citas_TipoCita` FOREIGN KEY (`idTipoCita`) REFERENCES `catalogo_tipocita` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_citas`
--

LOCK TABLES `datos_citas` WRITE;
/*!40000 ALTER TABLE `datos_citas` DISABLE KEYS */;
INSERT INTO `datos_citas` VALUES (1,'2024-11-01','2313','CREADA',1,'RM-0000000001','001','001');
/*!40000 ALTER TABLE `datos_citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_cliente`
--

DROP TABLE IF EXISTS `datos_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `primer_apellido` varchar(100) NOT NULL,
  `segundo_apellido` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `edad` int NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_cliente`
--

LOCK TABLES `datos_cliente` WRITE;
/*!40000 ALTER TABLE `datos_cliente` DISABLE KEYS */;
INSERT INTO `datos_cliente` VALUES (1,'Armando','Paredes','Sanchez','1997-05-13',52,'3214654316',1),(2,'Julia','Cancino','Martinez','1986-01-28',55,'5254654354',1);
/*!40000 ALTER TABLE `datos_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_consultorio`
--

DROP TABLE IF EXISTS `datos_consultorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_consultorio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(3) NOT NULL,
  `clave` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` int NOT NULL,
  `idTurno` varchar(3) NOT NULL,
  `idArea` varchar(3) NOT NULL,
  `idMedico` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `FK_Consultorio_Area_idx` (`idArea`),
  KEY `FK_Consultorio_Turno_idx` (`idTurno`),
  KEY `FK_Consultorio_Turno_idx1` (`idMedico`),
  CONSTRAINT `FK_Consultorio_Area` FOREIGN KEY (`idArea`) REFERENCES `catalogo_area` (`codigo`),
  CONSTRAINT `FK_Consultorio_Medico` FOREIGN KEY (`idMedico`) REFERENCES `catalogo_medicos` (`matricula`),
  CONSTRAINT `FK_Consultorio_Turno` FOREIGN KEY (`idTurno`) REFERENCES `catalogo_turno` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_consultorio`
--

LOCK TABLES `datos_consultorio` WRITE;
/*!40000 ALTER TABLE `datos_consultorio` DISABLE KEYS */;
INSERT INTO `datos_consultorio` VALUES (1,'001','100','Consultorio 1',1,'001','001','11111'),(2,'002','100','Consultorio 2',1,'001','001','22222'),(3,'003','200','Consultorio 1',1,'002','002','33333'),(4,'004','300','Consultorio 1',1,'003','003','44444');
/*!40000 ALTER TABLE `datos_consultorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_paciente`
--

DROP TABLE IF EXISTS `datos_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_paciente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `credencial` varchar(150) NOT NULL,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`id`,`credencial`),
  UNIQUE KEY `credencial_UNIQUE` (`credencial`),
  KEY `FK_Paciente_cliente_idx` (`idCliente`),
  CONSTRAINT `FK_Paciente_Cliente` FOREIGN KEY (`idCliente`) REFERENCES `datos_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_paciente`
--

LOCK TABLES `datos_paciente` WRITE;
/*!40000 ALTER TABLE `datos_paciente` DISABLE KEYS */;
INSERT INTO `datos_paciente` VALUES (2,'RM-0000000001',1),(3,'RM-0000000002',2);
/*!40000 ALTER TABLE `datos_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_roles`
--

DROP TABLE IF EXISTS `datos_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `estado` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Rol_Usuario_idx` (`idUsuario`),
  CONSTRAINT `FK_Rol_Usuario` FOREIGN KEY (`idUsuario`) REFERENCES `datos_usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_roles`
--

LOCK TABLES `datos_roles` WRITE;
/*!40000 ALTER TABLE `datos_roles` DISABLE KEYS */;
INSERT INTO `datos_roles` VALUES (1,'ROLE_ADMIN','Usuario Predeterminado',1,1),(2,'ROLE_USER','Usuario Predeterminado',1,2);
/*!40000 ALTER TABLE `datos_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_usuarios`
--

DROP TABLE IF EXISTS `datos_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `estado` int NOT NULL,
  `token` varchar(200) DEFAULT NULL,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  KEY `FK_Usuario_Cliente_idx` (`idCliente`),
  CONSTRAINT `FK_Usuario_Cliente` FOREIGN KEY (`idCliente`) REFERENCES `datos_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_usuarios`
--

LOCK TABLES `datos_usuarios` WRITE;
/*!40000 ALTER TABLE `datos_usuarios` DISABLE KEYS */;
INSERT INTO `datos_usuarios` VALUES (1,'prueba@mail.com','$2a$10$tvTqTBXCvZyr/JQrI5xrk.WfM3ed7MEXr5ZjDFWiXGp.aKOPjqxPC',1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxLHBydWViYUBtYWlsLmNvbSIsImlzcyI6IkNlc2FyIiwiaWF0IjoxNzMyODc3MjMwLCJleHAiOjE3MzI5NjM2MzB9.6jRJ691FKGyECtmDwAjxeDDdgnEsPgllw1jXHKwvQQs',1),(2,'prueba@linda.mx','$2a$10$WMDcKJIG3/U8587XkS.QA.JXUK7fMbciDL50bwjzFgfP9Kuhkh7AC',1,NULL,2);
/*!40000 ALTER TABLE `datos_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'citas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  8:10:39
