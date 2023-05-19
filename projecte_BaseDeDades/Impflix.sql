CREATE DATABASE IF NOT EXISTS `impflix` DEFAULT COLLATE 'latin1_spanish_ci';
USE `impflix`;

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` INT(5) AUTO_INCREMENT NOT NULL,
  `nom` VARCHAR(50) NOT NULL, 
  `DNI` VARCHAR(9) NOT NULL,
  `edat_data_naixement` DATE NOT NULL,
  `adreça` VARCHAR(50) NOT NULL,
  `nacionalitat` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `telefon` INT NOT NULL,
  `num_tarjeta` VARCHAR(50) NOT NULL,
  `num_compte_banc` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_client`)
);

CREATE TABLE IF NOT EXISTS `modalitat` (
  `id_modalitat` INT(5) AUTO_INCREMENT NOT NULL,
  `tipus` ENUM('Gratuïta', 'Bàsica', 'Premium') NOT NULL,
  PRIMARY KEY (`id_modalitat`)
);

CREATE TABLE IF NOT EXISTS `produccions` (
  `id_produccio` INT AUTO_INCREMENT NOT NULL,
  `nom` varchar(50) NOT NULL, 
  `nacionalitat` VARCHAR(20) NOT NULL,
  `any` YEAR NOT NULL,
  `favorit` INT,
  PRIMARY KEY (`id_produccio`)
);

CREATE TABLE IF NOT EXISTS `genere` (
  `id_categoria` INT(5) AUTO_INCREMENT NOT NULL,
  `nom` varchar(50) NOT NULL, 
  PRIMARY KEY (`id_categoria`)
);

CREATE TABLE IF NOT EXISTS `director` (
  `id_director` INT(5) AUTO_INCREMENT NOT NULL,
  `nomdirector` varchar(50) NOT NULL, 
  `nacionalitat` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_director`)
);

CREATE TABLE IF NOT EXISTS `actors` (
  `id_actor` INT(5) AUTO_INCREMENT NOT NULL,
  `nomactor` varchar(50) NOT NULL, 
  `nacionalitat` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_actor`)
);

CREATE TABLE IF NOT EXISTS `compte` (
  `id_compte` INT(5) AUTO_INCREMENT NOT NULL,
  `usuari` VARCHAR(15) NOT NULL, 
  `contrasenya` VARCHAR(50) NOT NULL,
  `data_alta` DATE,
  `id_client` INT(5) NOT NULL,
  `id_modalitat` int(5) NOT NULL,
  PRIMARY KEY (`id_compte`),
  CONSTRAINT `FK_compte_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_compte_modalitat` FOREIGN KEY (`id_modalitat`) REFERENCES `modalitat` (`id_modalitat`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `factura` (
  `id_factura` INT(5) AUTO_INCREMENT NOT NULL,
  `data` DATE NOT NULL, 
  `preu_cost` DOUBLE(5,2) NOT NULL,
  `id_compte` INT(5) NOT NULL,
  PRIMARY KEY (`id_factura`),
  CONSTRAINT `FK_factura_compte` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`id_compte`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `pot_visualitzar` (
  `id_modalitat` INT(5) NOT NULL,
  `id_produccio` INT(5) NOT NULL, 
  PRIMARY KEY (`id_modalitat`, `id_produccio`),
  CONSTRAINT `FK_pot_visualitzar_modalitat` FOREIGN KEY (`id_modalitat`) REFERENCES `modalitat` (`id_modalitat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pot_visualitzar_produccions` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `ha_consumit` (
  `id_compte` INT(5) NOT NULL,
  `id_produccio` INT(5) NOT NULL, 
  `num_visualitzacio` INT(5) NOT NULL,
  `favorit` INT NOT NULL,
  PRIMARY KEY (`id_compte`, `id_produccio`),
  CONSTRAINT `FK_ha_consumit_compte` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`id_compte`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ha_consumit_produccio` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `pertany` (
  `id_produccio` INT(5) NOT NULL,
  `id_categoria` INT(5) NOT NULL, 
  PRIMARY KEY (`id_produccio`, `id_categoria`),
  CONSTRAINT `FK_pertany_produccions` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pertany_genere` FOREIGN KEY (`id_categoria`) REFERENCES `genere` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `pelicules` (
  `id_produccio` INT(5) AUTO_INCREMENT NOT NULL,
  `durada` INT(3) NOT NULL,
  PRIMARY KEY (`id_produccio`),
  CONSTRAINT `FK_pelicules_produccions` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `series` (
  `id_produccio` INT(5) AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (`id_produccio`),
  CONSTRAINT `FK_series_produccions` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `actuen` (
  `id_actor` INT(5) NOT NULL,
  `id_produccio` int(5) NOT NULL, 
  `protagonista?` BOOLEAN NOT NULL,
  PRIMARY KEY (id_actor, id_produccio),
  CONSTRAINT `FK_actuen_actors` FOREIGN KEY (`id_actor`) REFERENCES `actors` (`id_actor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_actuen_pelicules` FOREIGN KEY (`id_produccio`) REFERENCES `pelicules` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `episodi` (
  `id_produccio` INT(5) NOT NULL,
  `id_episodi` int(5) NOT NULL, 
  `nom` VARCHAR(50) NOT NULL,
  `durada` INT(3) NOT NULL,    
  PRIMARY KEY (id_produccio, id_episodi),
  CONSTRAINT `FK_episodi_series` FOREIGN KEY (`id_produccio`) REFERENCES `series` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `ha_consumit_episodi` (
  `id_compte` INT(5) NOT NULL,
  `id_produccio` int(5) NOT NULL, 
  `id_episodi` INT(5) NOT NULL,
  `num_visualitzacio` INT(3) NOT NULL,
  PRIMARY KEY (id_compte, id_produccio, id_episodi),
  CONSTRAINT `FK_consumit_compte` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`id_compte`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_consumit_episodi` FOREIGN KEY (id_produccio, id_episodi) REFERENCES `episodi` (id_produccio, id_episodi) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `participen` (
  `id_actor` INT(5) NOT NULL,
  `id_produccio` INT(5) NOT NULL, 
  `id_episodi` INT(5) NOT NULL,
  `protagonista?` BOOLEAN NOT NULL,
  PRIMARY KEY (id_actor, id_produccio, id_episodi),
  CONSTRAINT `FK_participen_actor` FOREIGN KEY (`id_actor`) REFERENCES `actors` (`id_actor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_participen_episodi` FOREIGN KEY (id_produccio, id_episodi) REFERENCES `episodi` (id_produccio, id_episodi) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `dirigeix_episodi` (
  `id_director` INT(5) NOT NULL,
  `id_produccio` INT(5) NOT NULL, 
  `id_episodi` INT(5) NOT NULL,
  PRIMARY KEY (id_director, id_produccio, id_episodi),
  CONSTRAINT `FK_dirigeix_episodi_director` FOREIGN KEY (`id_director`) REFERENCES `director` (`id_director`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_dirigeix_episodi` FOREIGN KEY (id_produccio, id_episodi) REFERENCES `episodi` (id_produccio, id_episodi) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `dirigeix_pelicula` (
  `id_director` INT(5) NOT NULL, 
  `id_produccio` INT(5) NOT NULL,
  `durada` INT ,
  PRIMARY KEY (id_director, id_produccio),
  CONSTRAINT `FK_dirigeix_pelicula_director` FOREIGN KEY (`id_director`) REFERENCES `director` (`id_director`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_dirigeix_pelicula` FOREIGN KEY (`id_produccio`) REFERENCES `produccions` (`id_produccio`) ON DELETE NO ACTION ON UPDATE NO ACTION
);