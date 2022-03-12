-- --------------------------------------------------------
-- Host:                         zutjmx-sky-sql-db00005770.mdb0002418.db.skysql.net
-- Versión del servidor:         10.6.4-1-MariaDB-enterprise-log - MariaDB Enterprise Server
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para jakartaee9_curso
DROP DATABASE IF EXISTS `jakartaee9_curso`;
CREATE DATABASE IF NOT EXISTS `jakartaee9_curso` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jakartaee9_curso`;

-- Volcando estructura para tabla jakartaee9_curso.categorias
DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='Tabla de categorías';

-- Volcando datos para la tabla jakartaee9_curso.categorias: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`id`, `nombre`) VALUES
	(1, 'Deportes'),
	(2, 'Tecnología'),
	(3, 'Ropa y Calzado'),
	(4, 'Línea Blanca'),
	(5, 'Artículos para el hogar'),
	(6, 'Libros'),
	(8, 'Automotriz'),
	(10, 'Otra Categoria'),
	(11, 'Salud');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Volcando estructura para tabla jakartaee9_curso.productos
DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `sku` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_sku` (`sku`),
  KEY `fk_categoria_producto` (`categoria_id`),
  CONSTRAINT `fk_categoria_producto` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1 COMMENT='Tabla de productos para el curso jakarta EE 9';

-- Volcando datos para la tabla jakartaee9_curso.productos: ~25 rows (aproximadamente)
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`id`, `nombre`, `precio`, `fecha_registro`, `categoria_id`, `sku`) VALUES
	(1, 'ASUS VivoBook', 19999, '2021-12-03 12:00:25', 2, 'ASUSVIVOBK'),
	(2, 'IdeaPad 5 (15.6”, Intel)', 20000, '2021-12-03 12:02:20', 2, NULL),
	(3, 'ThinkPad E15', 19828, '2021-12-03 12:04:52', 2, NULL),
	(4, 'ThinkPad P1 3era Gen (15.6', 47000, '2021-12-03 12:11:52', 2, 'RFVCDEWSXQ'),
	(5, 'ThinkPad L14', 15000, '2021-12-03 12:18:24', 2, 'THINKPDL14'),
	(7, 'Tenis Lining Cloud', 1000, '2021-12-09 11:58:24', 1, NULL),
	(8, 'Jeans Casuales', 400, '2021-12-09 11:59:11', 3, NULL),
	(10, 'Huawei Y9a', 6000, '2021-12-09 00:00:00', 2, NULL),
	(11, 'Refrigerador con IOT', 12000, '2021-12-09 00:00:00', 4, 'plokijuhyg'),
	(13, 'Horno de Microondas Hamilton Beach', 2500, '2021-12-21 00:00:00', 4, NULL),
	(14, 'Laptop Dell Vostro-1720', 8000, '2021-12-21 00:00:00', 2, NULL),
	(15, 'Laptop Dell Vostro-3500', 8000, '2021-12-21 00:00:00', 2, NULL),
	(16, 'La llegada de los gatos cuánticos', 140, '2021-12-21 00:00:00', 6, NULL),
	(17, 'De los días y los años', 140, '2021-12-21 00:00:00', 6, '1234567890'),
	(18, 'Callejón de Dolores', 140, '2021-12-21 00:00:00', 6, '1234567891'),
	(20, 'Cafetera con IOT', 2500, '2021-12-21 00:00:00', 4, '86256108'),
	(22, 'Balatas', 500, '2022-01-06 00:00:00', 8, 'F06012022'),
	(24, 'Aceite De Motor', 180, '2022-01-06 00:00:00', 8, 'A06012022'),
	(26, 'Cámara Sony Cybershot RX100 V', 15199, '2022-02-18 00:00:00', 2, 'QWERASDF'),
	(27, 'Cámara Sony Cybershot W830', 3200, '2022-02-01 00:00:00', 2, 'QAZWSXEDCR'),
	(28, 'Tenis deportivo ARSR023-1 Li-Ning Negro', 1799, '2022-02-08 00:00:00', 1, 'zaqxswcde'),
	(29, 'Mesa portátil de madera (caoba)', 2500, '2022-02-13 00:00:00', 5, '0123456789'),
	(37, 'Banco de contactos Koblenz 300', 150, '2022-02-22 00:00:00', 5, 'KOBLENZ300'),
	(39, 'Alambre al pastor con queso', 60, '2022-02-22 00:00:00', 10, 'ALAMBREPAS'),
	(40, 'Quesadilla árabe', 80, '2022-02-01 00:00:00', 10, 'QUEKAARABE');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla jakartaee9_curso.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='Tabla de usuarios para el curso JakartaEE9';

-- Volcando datos para la tabla jakartaee9_curso.usuarios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `username`, `password`, `email`) VALUES
	(1, 'admin', '123456', 'admin@dominio.com.mx'),
	(2, 'zutjmx', '123456', 'zutjmx@gmail.com');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
