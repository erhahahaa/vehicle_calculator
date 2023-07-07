-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2022 at 12:47 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kalkulator_mobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand_mobil`
--

CREATE TABLE `brand_mobil` (
  `id_brand_mobil` int(11) NOT NULL,
  `nama_brand_mobil` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brand_mobil`
--

INSERT INTO `brand_mobil` (`id_brand_mobil`, `nama_brand_mobil`) VALUES
(69, 'Honda'),
(70, 'Suzuki'),
(1, 'Toyota');

-- --------------------------------------------------------

--
-- Table structure for table `detail_mobil`
--

CREATE TABLE `detail_mobil` (
  `id_detail_mobil` int(11) NOT NULL,
  `fk_tipe_mobil` varchar(50) NOT NULL,
  `thn_pembuatan` int(4) NOT NULL,
  `piston_diameter` decimal(11,2) DEFAULT NULL,
  `stroke_length` decimal(11,2) DEFAULT NULL,
  `piston_speed` decimal(11,2) DEFAULT NULL,
  `compression_ratio` decimal(11,2) DEFAULT NULL,
  `maximum_rpm` decimal(11,2) DEFAULT NULL,
  `carbuerator_diameter` decimal(11,2) DEFAULT NULL,
  `battery_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_mobil`
--

INSERT INTO `detail_mobil` (`id_detail_mobil`, `fk_tipe_mobil`, `thn_pembuatan`, `piston_diameter`, `stroke_length`, `piston_speed`, `compression_ratio`, `maximum_rpm`, `carbuerator_diameter`, `battery_name`) VALUES
(27, 'Honda Jazz RS CVT', 2017, '73.00', '89.00', '21.20', '10.30', '8000.00', NULL, 'Aki Kalsium'),
(29, 'Suzuki Ertiga GX Hybrid AT', 2022, '74.00', '85.00', '22.40', '10.00', '8000.00', NULL, 'Aki Hybrid'),
(30, 'Toyota Agya TRD 1.0 G M/T', 2020, '72.00', '72.00', '23.20', '10.00', '8000.00', NULL, 'Aki Maintenance Free'),
(31, 'Toyota Avanza 1.3 G M/T', 2022, '72.00', '80.00', '21.30', '11.00', '8000.00', NULL, 'Aki Basah'),
(32, 'Grand New CRV 2.4 A/T', 2013, '87.00', '99.00', '23.30', '10.60', '8000.00', NULL, 'Aki Basah');

-- --------------------------------------------------------

--
-- Table structure for table `konsumer`
--

CREATE TABLE `konsumer` (
  `id_konsumer` int(11) NOT NULL,
  `nama_konsumer` varchar(50) NOT NULL,
  `no_telp` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mekanik`
--

CREATE TABLE `mekanik` (
  `id_mekanik` int(11) NOT NULL,
  `nama_mekanik` varchar(50) NOT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mekanik`
--

INSERT INTO `mekanik` (`id_mekanik`, `nama_mekanik`, `tgl_masuk`, `alamat`) VALUES
(1, 'Rahmat Hidayatullah', '2022-12-16', 'Mojokerto'),
(2, 'Violia Ruana Nur\'aini Sagita', '2022-12-17', 'Sidoarjo'),
(3, 'Aryanti Nur Anisah', '2022-12-18', 'Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `rekam`
--

CREATE TABLE `rekam` (
  `id_rekam` int(11) NOT NULL,
  `fk_konsumer` varchar(50) NOT NULL,
  `fk_mobil` varchar(50) NOT NULL,
  `fk_mekanik` varchar(50) NOT NULL,
  `plat_nomor` varchar(20) NOT NULL,
  `piston_diameter` decimal(11,2) DEFAULT NULL,
  `stroke_length` decimal(11,2) DEFAULT NULL,
  `total_cylinder` decimal(11,2) DEFAULT NULL,
  `TDC_volume` decimal(11,2) DEFAULT NULL,
  `Spark_volume` decimal(11,2) DEFAULT NULL,
  `BDC_volume` decimal(11,2) DEFAULT NULL,
  `engine_rotation` decimal(11,2) DEFAULT NULL,
  `compression_ratio` decimal(11,2) DEFAULT NULL,
  `piston_speed` decimal(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tipe_mobil`
--

CREATE TABLE `tipe_mobil` (
  `id_tipe_mobil` int(11) NOT NULL,
  `fk_brand_mobil` varchar(20) DEFAULT NULL,
  `tipe_mobil` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tipe_mobil`
--

INSERT INTO `tipe_mobil` (`id_tipe_mobil`, `fk_brand_mobil`, `tipe_mobil`) VALUES
(50, 'Honda', 'Grand New CRV 2.4 A/T'),
(51, 'Honda', 'Honda Jazz RS CVT'),
(52, 'Toyota', 'Toyota Avanza 1.3 G M/T'),
(56, 'Suzuki', 'Suzuki Ertiga GX Hybrid AT'),
(57, 'Toyota', 'Toyota Agya TRD 1.0 G M/T');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand_mobil`
--
ALTER TABLE `brand_mobil`
  ADD PRIMARY KEY (`id_brand_mobil`),
  ADD UNIQUE KEY `nama_brand_mobil` (`nama_brand_mobil`);

--
-- Indexes for table `detail_mobil`
--
ALTER TABLE `detail_mobil`
  ADD PRIMARY KEY (`id_detail_mobil`),
  ADD KEY `fk_tipe_mobil` (`fk_tipe_mobil`);

--
-- Indexes for table `konsumer`
--
ALTER TABLE `konsumer`
  ADD PRIMARY KEY (`id_konsumer`),
  ADD UNIQUE KEY `nama_konsumer_2` (`nama_konsumer`),
  ADD KEY `nama_konsumer` (`nama_konsumer`);

--
-- Indexes for table `mekanik`
--
ALTER TABLE `mekanik`
  ADD PRIMARY KEY (`id_mekanik`),
  ADD KEY `nama_mekanik` (`nama_mekanik`);

--
-- Indexes for table `rekam`
--
ALTER TABLE `rekam`
  ADD PRIMARY KEY (`id_rekam`),
  ADD KEY `fk_konsumer` (`fk_konsumer`) USING BTREE,
  ADD KEY `fk_mobil` (`fk_mobil`) USING BTREE,
  ADD KEY `fk_mekanik` (`fk_mekanik`) USING BTREE;

--
-- Indexes for table `tipe_mobil`
--
ALTER TABLE `tipe_mobil`
  ADD PRIMARY KEY (`id_tipe_mobil`),
  ADD UNIQUE KEY `tipe_mobil` (`tipe_mobil`),
  ADD KEY `fk_brand_mobil` (`fk_brand_mobil`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand_mobil`
--
ALTER TABLE `brand_mobil`
  MODIFY `id_brand_mobil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `detail_mobil`
--
ALTER TABLE `detail_mobil`
  MODIFY `id_detail_mobil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `konsumer`
--
ALTER TABLE `konsumer`
  MODIFY `id_konsumer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `mekanik`
--
ALTER TABLE `mekanik`
  MODIFY `id_mekanik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `rekam`
--
ALTER TABLE `rekam`
  MODIFY `id_rekam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `tipe_mobil`
--
ALTER TABLE `tipe_mobil`
  MODIFY `id_tipe_mobil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_mobil`
--
ALTER TABLE `detail_mobil`
  ADD CONSTRAINT `detail_mobil_ibfk_1` FOREIGN KEY (`fk_tipe_mobil`) REFERENCES `tipe_mobil` (`tipe_mobil`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rekam`
--
ALTER TABLE `rekam`
  ADD CONSTRAINT `rekam_ibfk_1` FOREIGN KEY (`fk_konsumer`) REFERENCES `konsumer` (`nama_konsumer`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_ibfk_2` FOREIGN KEY (`fk_mekanik`) REFERENCES `mekanik` (`nama_mekanik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rekam_ibfk_3` FOREIGN KEY (`fk_mobil`) REFERENCES `detail_mobil` (`fk_tipe_mobil`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tipe_mobil`
--
ALTER TABLE `tipe_mobil`
  ADD CONSTRAINT `tipe_mobil_ibfk_1` FOREIGN KEY (`fk_brand_mobil`) REFERENCES `brand_mobil` (`nama_brand_mobil`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
