-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 15 déc. 2021 à 06:55
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `carpooling`
--

DROP TABLE IF EXISTS `carpooling`;
CREATE TABLE IF NOT EXISTS `carpooling` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `departure_date` date NOT NULL,
  `departure_location` varchar(50) NOT NULL,
  `drop_off_location` varchar(50) NOT NULL,
  `driver_name` varchar(20) NOT NULL,
  `phone_number` int(8) NOT NULL,
  `places_number` int(11) NOT NULL,
  `baggage` varchar(3) NOT NULL,
  `preference` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `carpooling`
--

INSERT INTO `carpooling` (`id`, `userid`, `departure_date`, `departure_location`, `drop_off_location`, `driver_name`, `phone_number`, `places_number`, `baggage`, `preference`) VALUES
(50, 49, '2016-01-01', 'azz', 'azaz', 'azaz', 14523658, 1, 'yes', 'NULL'),
(52, 47, '2016-01-01', 'Tunis', 'Klibia', 'Iheb', 96857412, 4, 'yes', 'NULL'),
(53, 51, '2016-01-01', 'Klibia', 'Sousse', 'Iheb', 98765432, 3, 'yes', 'NULL'),
(60, 47, '2021-12-08', 'arara', 'azaz', 'null', 3, 3, 'Yes', 'qsdqs'),
(61, 47, '2021-12-16', 'test', 'test', 'null', 1, 1, 'No', '');

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `Product_qte` int(11) NOT NULL DEFAULT '0',
  `total` float NOT NULL DEFAULT '0',
  `products` int(11) NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `products` (`products`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `colis`
--

DROP TABLE IF EXISTS `colis`;
CREATE TABLE IF NOT EXISTS `colis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `colistype_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `departure` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `prix` decimal(10,0) NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `destination` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_470BDFF9A6D10CF6` (`colistype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `colis`
--

INSERT INTO `colis` (`id`, `colistype_id`, `name`, `departure`, `quantity`, `prix`, `phone`, `email`, `date`, `image`, `destination`) VALUES
(13, 21, 'ramii', 'nabel', 13, '155', '55465875', 'rami.2000@esprit.tn', '2021-12-31', 'b33b533d9b3a76f9702a5294497b6416.jpeg', 'mestir'),
(14, 26, 'azize', 'kassrin', 500, '125', '55326558', 'aziz.mojhfd@esprit.tn', '2021-12-06', 'c9455ad2d11260ad9cc9375d0cfd2a40.jpeg', 'siliana'),
(15, 24, 'msakni', 'tunis', 50, '170', '55487956', 'msakni@gmail.com', '2021-12-04', '5e12b59d40ffd5cc77ddebb42793e443.jpeg', 'mahdia'),
(16, 26, 'montassa', 'tunis', 50, '15', '55789658', 'aziz.mojhfd@esprit.tn', '2021-12-22', '74a271d5cf714b991cf201bc9ae46eda.jpeg', 'beb sa3don'),
(18, 24, 'morade', 'TUNIS', 785278, '50', '55345878', 'MONTASA@GMAIL.COM', '2021-12-17', '15479d537e7293e6d943b6f6b92ce76f.jpeg', 'NABIL'),
(19, 28, 'zied slama', 'makthar', 500, '150', '55431584', 'mohamed@esprit.tn', '2021-12-05', '4968df3193e6b6acd5e24ccbe338d02a.jpeg', 'tunis');

-- --------------------------------------------------------

--
-- Structure de la table `colistype`
--

DROP TABLE IF EXISTS `colistype`;
CREATE TABLE IF NOT EXISTS `colistype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `colistype`
--

INSERT INTO `colistype` (`id`, `type`) VALUES
(19, 'informatique'),
(21, 'PIECE AUTO'),
(22, 'Tableau'),
(23, 'table'),
(24, 'casque'),
(25, 'food restorant'),
(26, 'livre'),
(27, 'portable'),
(28, 'types');

-- --------------------------------------------------------

--
-- Structure de la table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE IF NOT EXISTS `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `complainttype_id` int(11) DEFAULT NULL,
  `object` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5F2732B51E3CA6D2` (`complainttype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `complaint`
--

INSERT INTO `complaint` (`id`, `complainttype_id`, `object`, `description`, `email`) VALUES
(37, 1, 'réclamation _4', 'reclamation_5', 'oussema.ouerfelli@esprit.tn'),
(73, 2, 'réclamation 121', 'réclamation', 'oussema.ouerfelli@esprit.tn'),
(120, 6, 'réclamation _2', 'réclamation', 'oussema.ouerfelli@esprit.tn'),
(139, 2, 'Reclamation1', 'Validation', 'oussema.ouerfelli@esprit.tn'),
(146, 1, 'TestJava', 'Just Testi\n\n', 'ramzi.kerkeni@esprit.tn'),
(150, 1, 'test', 'hello world\n\n', 'ihebsad1998@email.com');

-- --------------------------------------------------------

--
-- Structure de la table `complainttype`
--

DROP TABLE IF EXISTS `complainttype`;
CREATE TABLE IF NOT EXISTS `complainttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `complainttype`
--

INSERT INTO `complainttype` (`id`, `type`) VALUES
(1, 'driver'),
(2, 'service'),
(3, 'other'),
(5, 'service Chauffeur'),
(6, 'price');

-- --------------------------------------------------------

--
-- Structure de la table `c_details`
--

DROP TABLE IF EXISTS `c_details`;
CREATE TABLE IF NOT EXISTS `c_details` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_cin` int(11) NOT NULL,
  `music` tinyint(1) NOT NULL,
  `climatisation` tinyint(1) NOT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `c_details`
--

INSERT INTO `c_details` (`detail_id`, `driver_cin`, `music`, `climatisation`) VALUES
(50, 14526325, 1, 0),
(52, 2145263, 1, 0),
(53, 52634185, 0, 1),
(60, 25639685, 1, 0),
(61, 12345678, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `order_list`
--

DROP TABLE IF EXISTS `order_list`;
CREATE TABLE IF NOT EXISTS `order_list` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `status` varchar(25) COLLATE utf8_bin NOT NULL DEFAULT 'not confirmed',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `cart_id` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` int(11) NOT NULL,
  `brand` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `unit` float NOT NULL,
  `price` float NOT NULL,
  `qte` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`product_id`, `category`, `brand`, `name`, `unit`, `price`, `qte`) VALUES
(1, 1, 'Fourat', 'water', 0.5, 1.5, 10),
(2, 0, 'Lays', 'Chips', 1, 3, 5),
(3, 2, 'Plaster', 'Bandaid', 10, 2, 20),
(8, 0, 'sabrine', 'water', 1, 1, 10);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `cin` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `birth_date` date NOT NULL,
  `privilege` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `cin`, `email`, `username`, `password`, `phone_number`, `birth_date`, `privilege`) VALUES
(40, 'Ahmed', 'Bouabda', '14778899', 'ahmed.bouadba@esprit.tn', 'ahmed.bouabda', '961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506', '98563218', '1894-10-22', 2),
(47, 'Ramziii', 'Kerkeni', '14001478', 'ramzi.kerkeni@esprit.tn', 'ramzi.kerkeni', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', '96589635', '1891-11-10', 0),
(49, 'Nada', 'Azzabi', '14251245', 'nada.azzabi@esprit.tn', 'nada.azzabi', '140bedbf9c3f6d56a9846d2ba7088798683f4da0c248231336e6a05679e4fdfe', '52957535', '2000-12-26', 1),
(51, 'Rasds', 'Jernew', '14253695', 'ramzi.kerkeni2@esprit.tn', 'qsdqs', 'ed968e840d10d2d313a870bc131a4e2c311d7ad09bdf32b3418147221f51a6e2', '95863698', '2000-01-01', 1),
(59, 'qsdqs', 'qsdqsd', '14521452', 'qsdjqs@sdlk.fr', 'qsdaa.aa', 'qsdkqsjl', '98569325', '1891-01-01', 2),
(60, 'Ramzitest', 'Kerkenitest', '11447711', 'ramzitest@esprit.tn', 'ramzitest', 'aa', '95587458', '1891-01-01', 1),
(61, 'Ramzitest', 'Kerkenitest', '11447811', 'ramziteat@esprit.tn', 'ramzitast', 'aa', '95588458', '1891-01-01', 1),
(62, 'Ramzi', 'Kerkeni', '14253677', 'ramzitesttte@espri.tn', 'ramzitestttt', 'aa', '29999999', '1891-01-01', 0),
(63, 'commechez', 'commechez', '14256658', 'commechez@esprit.tn', 'commechez', '3b64db95cb55c763391c707108489ae18b4112d783300de38e033b4c98c3deaf', '96856589', '1891-01-01', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carpooling`
--
ALTER TABLE `carpooling`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`userid`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`products`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `colis`
--
ALTER TABLE `colis`
  ADD CONSTRAINT `FK_470BDFF9A6D10CF6` FOREIGN KEY (`colistype_id`) REFERENCES `colistype` (`id`);

--
-- Contraintes pour la table `complaint`
--
ALTER TABLE `complaint`
  ADD CONSTRAINT `FK_5F2732B51E3CA6D2` FOREIGN KEY (`complainttype_id`) REFERENCES `complainttype` (`id`);

--
-- Contraintes pour la table `c_details`
--
ALTER TABLE `c_details`
  ADD CONSTRAINT `carpoolingid` FOREIGN KEY (`detail_id`) REFERENCES `carpooling` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `order_list`
--
ALTER TABLE `order_list`
  ADD CONSTRAINT `order_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_list_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
