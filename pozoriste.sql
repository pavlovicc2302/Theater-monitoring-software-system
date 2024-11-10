-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2024 at 02:51 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pozoriste`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `AdministratorID` bigint(10) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`AdministratorID`, `Username`, `Password`) VALUES
(1, 'Ana', 'vienna'),
(2, 'Sara', 'bec');

-- --------------------------------------------------------

--
-- Table structure for table `glumac`
--

CREATE TABLE `glumac` (
  `GlumacID` bigint(20) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Telefon` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `glumac`
--

INSERT INTO `glumac` (`GlumacID`, `Ime`, `Prezime`, `Email`, `Telefon`) VALUES
(1, 'Branimir', 'Brstina', 'branimir@brstina.com', '0611212122'),
(2, 'Nebojsa', 'Ilić', 'nebojsa@ilic.com', '0621212127'),
(3, 'Ivana', 'Zecević', 'ivana@zecevic.com', '0631212122'),
(4, 'Branimir', 'Popović', 'branimir@popovic.com', '0631212121'),
(5, 'Vojin', 'Ćetković', 'vojin@cetkovic.com', '0633433434'),
(6, 'Pavle', 'Pekić', 'pavle@pekic.com', '063596379'),
(7, 'Anđelika', 'Simić', 'andja@simic.com', '0643332212'),
(8, 'Zoran', 'Cvijanović', 'zoki@cvijanovic.com', '0656664354'),
(9, 'Miloš', 'Đorđević', 'milos@djordjevic.com', '061235646'),
(10, 'Svetozar', 'Cvetković', 'svetozar@cvetkovic.com', '0657788961'),
(11, 'Dubravka', 'Mijatović', 'dubravka@mijatovic.com', '0691235436'),
(12, 'Natalija', 'Stepanović', 'natalija@stepanovic.com', '0671234512'),
(13, 'Jelena', 'Ilić', 'jelena@ilic.com', '0683452670'),
(14, 'Dejan', 'Dedić', 'dejan@dedic.com', '0623451236'),
(15, 'Milica', 'Janevski', 'milica@janevski.com', '0603462780');

-- --------------------------------------------------------

--
-- Table structure for table `glumacprojekcije`
--

CREATE TABLE `glumacprojekcije` (
  `GlumacID` bigint(20) NOT NULL,
  `ProjekcijaID` bigint(20) NOT NULL,
  `Napomena` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `glumacprojekcije`
--

INSERT INTO `glumacprojekcije` (`GlumacID`, `ProjekcijaID`, `Napomena`) VALUES
(1, 9, 'Glumice'),
(1, 15, 'Igraće ulogu Marka'),
(1, 16, 'Glumi ulogu \"Ona\" u ovoj projekcije predstave'),
(1, 19, 'Igrace ulogu dede'),
(1, 20, 'Glumice ulogu dede.'),
(5, 13, 'Glumice ulogu Oca'),
(8, 1, 'Igraće ulogu dede'),
(8, 18, 'Glumice ulogu Karlo'),
(9, 17, 'Glumice ulogu \"ON\"'),
(13, 14, 'Glumice ulogu \'Ona\''),
(15, 12, 'Bice zamena');

-- --------------------------------------------------------

--
-- Table structure for table `predstava`
--

CREATE TABLE `predstava` (
  `PredstavaID` bigint(20) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  `Opis` varchar(200) NOT NULL,
  `TrajanjeMinuti` int(200) DEFAULT NULL,
  `BrojCinova` int(200) DEFAULT NULL,
  `Reditelj` varchar(100) NOT NULL,
  `ZanrID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `predstava`
--

INSERT INTO `predstava` (`PredstavaID`, `Naziv`, `Opis`, `TrajanjeMinuti`, `BrojCinova`, `Reditelj`, `ZanrID`) VALUES
(1, 'Kuća', '„Gde ti je kuća? Pod jedan, ovde, na selu? Pod dva, u Beogradu? Ili pod tri, u Kanadi? Izaberi jedan od tri ponuđena odgovora!\"', 90, 3, 'Voja Brajović', 1),
(2, 'Junaci doba krize', 'U drami Junaci doba krize Vil Arberi preispituje da li su moguća prijateljstva između ljudi na različitim ideološkim pozicijama.', 100, 5, 'Andrea Pjević', 1),
(5, 'Sada nije juli', 'Ova ljubavna priča dešava se na jednom ostrvu (i na putu do ostrva), pod nemilosrdnim, letnjim suncem, ON (20) i ONA (20), posle duže razdvojenosti, pokušavaju da evociraju nekadašnju romantičnu ljuba', 75, 4, 'Luka Grbić', 2),
(6, 'Nestajanje', 'Nestajanje je priča o odnosima roditelja i dece, o položaju dece u sistemu edukacije, o svetu odraslih koji zanemarije decu i pre svega o zlostavljanju dece. ', 110, 6, 'Sanja Mitrović', 2);

-- --------------------------------------------------------

--
-- Table structure for table `projekcija`
--

CREATE TABLE `projekcija` (
  `ProjekcijaID` bigint(20) NOT NULL,
  `Datum` date DEFAULT NULL,
  `Vreme` time DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `SalaID` bigint(20) NOT NULL,
  `PredstavaID` bigint(20) NOT NULL,
  `AdministratorID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projekcija`
--

INSERT INTO `projekcija` (`ProjekcijaID`, `Datum`, `Vreme`, `Status`, `SalaID`, `PredstavaID`, `AdministratorID`) VALUES
(1, '2024-09-29', '15:00:00', 'Prikazuje se', 2, 2, 1),
(9, '2024-12-19', '18:35:00', 'Prikazuje se', 1, 2, 2),
(12, '2024-11-15', '20:45:00', 'Otkazana', 1, 1, 1),
(13, '2024-12-09', '19:37:00', 'Prikazuje se', 1, 1, 2),
(14, '2024-11-11', '21:31:00', 'Otkazana', 1, 5, 2),
(15, '2024-09-18', '21:30:00', 'Otkazana', 1, 6, 1),
(16, '2024-09-15', '20:30:00', 'Prikazuje se', 1, 5, 1),
(17, '2024-09-23', '14:30:00', 'Prikazuje se', 3, 5, 1),
(18, '2024-09-25', '21:00:00', 'Prikazuje se', 4, 6, 1),
(19, '2024-09-28', '20:30:00', 'Prikazuje se', 4, 1, 1),
(20, '2024-10-29', '19:45:00', 'Prikazuje se', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `SalaID` bigint(20) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  `Kapacitet` int(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` (`SalaID`, `Naziv`, `Kapacitet`) VALUES
(1, 'Sala Balkanski spijun', 200),
(2, 'Sala Nacionalna klasa', 250),
(3, 'Sala Bata Stojkovic', 300),
(4, 'Sala Milena Dravic', 250);

-- --------------------------------------------------------

--
-- Table structure for table `uloga`
--

CREATE TABLE `uloga` (
  `PredstavaID` bigint(20) NOT NULL,
  `RbUloge` bigint(20) NOT NULL,
  `Opis` varchar(200) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  `GlumacID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `uloga`
--

INSERT INTO `uloga` (`PredstavaID`, `RbUloge`, `Opis`, `Naziv`, `GlumacID`) VALUES
(1, 6, 'Nema opisa', 'Deda', 1),
(1, 7, 'Nema opisa', 'Otac', 2),
(1, 8, 'Nema opisa', 'Ćerka', 3),
(2, 9, 'Nema opisa', 'Đina', 7),
(2, 10, 'Nema opisa', 'Džastin', 8),
(2, 12, 'Nema opisa', 'Kevin Grbić', 9),
(2, 13, 'Nema opisa', 'Tereza', 3),
(5, 20, 'Nema opisa', 'On', 4),
(5, 21, 'Nema opisa', 'Ona', 3),
(6, 22, 'Nema opisa', 'Karlo', 10),
(6, 23, 'Nema opisa', 'Vera', 11),
(6, 24, 'Nema opisa', 'Tea', 12),
(6, 25, 'Nema opisa', 'Marko', 14),
(6, 26, 'Nema opisa', 'Ana', 13),
(6, 27, 'Nema opisa', 'Alisa', 15);

-- --------------------------------------------------------

--
-- Table structure for table `zanr`
--

CREATE TABLE `zanr` (
  `ZanrID` bigint(20) NOT NULL,
  `Naziv` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `zanr`
--

INSERT INTO `zanr` (`ZanrID`, `Naziv`) VALUES
(1, 'Drama'),
(2, 'Komedija');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`AdministratorID`);

--
-- Indexes for table `glumac`
--
ALTER TABLE `glumac`
  ADD PRIMARY KEY (`GlumacID`);

--
-- Indexes for table `glumacprojekcije`
--
ALTER TABLE `glumacprojekcije`
  ADD PRIMARY KEY (`GlumacID`,`ProjekcijaID`),
  ADD KEY `gp_projekcija_pk1_fk1` (`ProjekcijaID`);

--
-- Indexes for table `predstava`
--
ALTER TABLE `predstava`
  ADD PRIMARY KEY (`PredstavaID`),
  ADD KEY `predstava_zanr_fk1` (`ZanrID`);

--
-- Indexes for table `projekcija`
--
ALTER TABLE `projekcija`
  ADD PRIMARY KEY (`ProjekcijaID`),
  ADD KEY `projekcija_predstava_fk1` (`PredstavaID`),
  ADD KEY `projekcija_sala_fk2` (`SalaID`),
  ADD KEY `projekcija_admin_fk3` (`AdministratorID`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`SalaID`);

--
-- Indexes for table `uloga`
--
ALTER TABLE `uloga`
  ADD PRIMARY KEY (`PredstavaID`,`RbUloge`),
  ADD KEY `RbUloge` (`RbUloge`),
  ADD KEY `uloga_glumac_fk1` (`GlumacID`);

--
-- Indexes for table `zanr`
--
ALTER TABLE `zanr`
  ADD PRIMARY KEY (`ZanrID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `AdministratorID` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `glumac`
--
ALTER TABLE `glumac`
  MODIFY `GlumacID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `predstava`
--
ALTER TABLE `predstava`
  MODIFY `PredstavaID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `projekcija`
--
ALTER TABLE `projekcija`
  MODIFY `ProjekcijaID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `sala`
--
ALTER TABLE `sala`
  MODIFY `SalaID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `uloga`
--
ALTER TABLE `uloga`
  MODIFY `RbUloge` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `zanr`
--
ALTER TABLE `zanr`
  MODIFY `ZanrID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `glumacprojekcije`
--
ALTER TABLE `glumacprojekcije`
  ADD CONSTRAINT `gp_glumac_pk1_fk2` FOREIGN KEY (`GlumacID`) REFERENCES `glumac` (`GlumacID`),
  ADD CONSTRAINT `gp_projekcija_pk1_fk1` FOREIGN KEY (`ProjekcijaID`) REFERENCES `projekcija` (`ProjekcijaID`);

--
-- Constraints for table `predstava`
--
ALTER TABLE `predstava`
  ADD CONSTRAINT `predstava_zanr_fk1` FOREIGN KEY (`ZanrID`) REFERENCES `zanr` (`ZanrID`);

--
-- Constraints for table `projekcija`
--
ALTER TABLE `projekcija`
  ADD CONSTRAINT `projekcija_admin_fk3` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`),
  ADD CONSTRAINT `projekcija_predstava_fk1` FOREIGN KEY (`PredstavaID`) REFERENCES `predstava` (`PredstavaID`),
  ADD CONSTRAINT `projekcija_sala_fk2` FOREIGN KEY (`SalaID`) REFERENCES `sala` (`SalaID`);

--
-- Constraints for table `uloga`
--
ALTER TABLE `uloga`
  ADD CONSTRAINT `uloga_glumac_fk1` FOREIGN KEY (`GlumacID`) REFERENCES `glumac` (`GlumacID`),
  ADD CONSTRAINT `uloga_predstava_pk1_fk1` FOREIGN KEY (`PredstavaID`) REFERENCES `predstava` (`PredstavaID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
