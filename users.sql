-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Φιλοξενητής: localhost
-- Χρόνος δημιουργίας: 01 Φεβ 2015 στις 14:52:38
-- Έκδοση διακομιστή: 5.6.21
-- Έκδοση PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Βάση δεδομένων: `tzoura`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(20) NOT NULL,
  `username` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `full_name` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `country` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `city` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `track_count` int(20) NOT NULL,
  `public_favorites_count` int(20) NOT NULL,
  `followers_count` int(20) NOT NULL,
  `followings_count` int(20) NOT NULL,
  `likes_count` int(20) NOT NULL,
  `comments_count` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
