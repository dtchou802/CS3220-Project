-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Aug 08, 2018 at 08:10 PM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs3220stu02`
--

-- --------------------------------------------------------

--
-- Table structure for table `History`
--

CREATE TABLE `History` (
  `VersionID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Filepath` varchar(100) NOT NULL,
  `UploadDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ParentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `History`
--
ALTER TABLE `History`
  ADD PRIMARY KEY (`VersionID`),
  ADD KEY `fk_versionID_uploadID` (`ParentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `History`
--
ALTER TABLE `History`
  MODIFY `VersionID` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `History`
--
ALTER TABLE `History`
  ADD CONSTRAINT `fk_versionID_uploadID` FOREIGN KEY (`ParentID`) REFERENCES `Uploads` (`UploadID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
