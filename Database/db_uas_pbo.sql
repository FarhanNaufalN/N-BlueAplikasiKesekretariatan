-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2024 at 06:10 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_uas_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_anggota`
--

CREATE TABLE `tb_anggota` (
  `nim` int(11) NOT NULL,
  `nama` varchar(250) NOT NULL,
  `jurusan` varchar(250) NOT NULL,
  `angkatan` int(4) NOT NULL,
  `ukm` varchar(255) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_anggota`
--

INSERT INTO `tb_anggota` (`nim`, `nama`, `jurusan`, `angkatan`, `ukm`, `telp`, `alamat`, `email`, `password`, `jabatan`) VALUES
(884, 'vsvs', 'S1 Pendidikan Guru Sekolah Dasar', 848, 'HIMA PGSD', '9494994', 'shsh', '', '', 'sbjs'),
(6666, 'bem', 'S1 Pendidikan Multimedi', 1, 'BEM', '288556877', '2ghjjgf', 'bem', '123', 'staff'),
(215555, 'Lucky', 'S1 Pendidikan Guru Sekolah Dasar', 20, 'DPM', '6454848848', 'gahsjan', 'dpm', '123', 'ketua'),
(2078889, 'Muadz', 'TEKKOM', 21, 'BEM', '0877982332', 'Cibiru', 'muadz@mail.com', '123', 'Presiden'),
(2101915, 'Dinda Juliana', 'S1 Teknik Komputer', 2021, 'HIMA TEKKOM', '089643320065', 'Majalaya, Bandung', 'dinda', '123', 'Sekretaris  '),
(2102555, 'Farhan Naufal', 'S1 Teknik Komputer', 2021, 'HIMA TEKKOM', '081395538770', 'Jatinangor', 'tekkom@mail.com', '123', 'Ketua DE'),
(2102999, 'Ilham Badiuzaman', 'TEKKOM', 2021, 'HIMA TEKKOM', '08771328381', 'Cibiru', 'ilham@mail.com', '2102999HIMA TEKKOM2021', 'Bendahara'),
(2103000, 'aji', 'Multimedia', 0, 'HIMA PEDIA', '0877982332', 'Cibiru', 'pedia', '123', 'Ketua'),
(2103001, 'Calzy', 'RPL', 2021, 'HIMA RPL', '08779823322', 'Cibiru', 'rpl', '123', 'Ketua'),
(2777889, 'Rifty', 'TEKKOM', 2020, 'BOMA', '292838813081203', 'JAKARTA', 'boma\r\n', '123', 'Ketua');

-- --------------------------------------------------------

--
-- Table structure for table `tb_barang`
--

CREATE TABLE `tb_barang` (
  `id` int(11) NOT NULL,
  `ukm` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jml_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_barang`
--

INSERT INTO `tb_barang` (`id`, `ukm`, `nama`, `jml_barang`) VALUES
(1, 'DPM', 'Tripod', 6),
(2, 'HIMA RPL', 'Audio Mixer', 4),
(5, 'DPM', 'Kamera', 8),
(6, 'HIMA RPL', 'Proyektor', 9),
(7, 'HIMA RPL', 'Printer', 9),
(9, 'BEM', 'Meja', 5),
(10, 'BEM', 'Tenda', 19),
(11, 'BEM', 'Kursi', 11),
(13, 'HIMA TEKKOM', 'Speaker', 4),
(14, 'HIMA TEKKOM', 'Batre', 20),
(15, 'HIMA TEKKOM', 'Proyektor', 16),
(19, 'BEM', 'Printer', 5),
(20, 'HIMA TEKKOM', 'Tongkat', 6),
(21, 'BEM', 'Laptop', 5),
(22, 'HIMA TEKKOM', 'Keyboard', 4),
(23, 'HIMA TEKKOM', 'Kabel HDMI', 4),
(24, 'HIMA RPL', 'Kamera', 6),
(25, 'HIMA RPL', 'Sapu', 5),
(26, 'HIMA TEKKOM', 'Laptop', 12);

-- --------------------------------------------------------

--
-- Table structure for table `tb_department`
--

CREATE TABLE `tb_department` (
  `id` int(250) NOT NULL,
  `ukm` varchar(250) NOT NULL,
  `department` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_department`
--

INSERT INTO `tb_department` (`id`, `ukm`, `department`) VALUES
(2, 'HIMA TEKKOM', 'MIKAT'),
(3, 'HIMA TEKKOM', 'Kewirausahaan'),
(4, 'HIMA TEKKOM', 'Sekretaris'),
(5, 'HIMA TEKKOM', 'Bendahara'),
(6, 'HIMA TEKKOM', 'P2M'),
(7, 'HIMA TEKKOM', 'ADVOKESMA'),
(8, 'HIMA TEKKOM', 'MEDINFO'),
(9, 'HIMA TEKKOM', 'KEAGAMAAN'),
(10, 'BEM', 'PSDM'),
(11, 'BEM', 'KEMENDIKAT'),
(12, 'BEM', 'KEMENFO'),
(13, 'BEM', 'KEMENKRAF'),
(19, 'BEM', 'Sekretaris'),
(20, 'HIMA PEDIA', 'Sekretaris'),
(22, 'KSR', 'Pengawas'),
(23, 'Pilih ORMAWA', 'PSDO'),
(24, 'HIMA TEKKOM', 'PSDO ');

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_peminjaman`
--

CREATE TABLE `tb_detail_peminjaman` (
  `id` int(11) NOT NULL,
  `id_peminjaman` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jml_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_detail_peminjaman`
--

INSERT INTO `tb_detail_peminjaman` (`id`, `id_peminjaman`, `id_barang`, `jml_barang`) VALUES
(1, 0, 5, 3),
(2, 2, 5, 2),
(3, 3, 10, 5),
(4, 4, 11, 4);

--
-- Triggers `tb_detail_peminjaman`
--
DELIMITER $$
CREATE TRIGGER `update_stok` AFTER INSERT ON `tb_detail_peminjaman` FOR EACH ROW BEGIN
UPDATE tb_barang SET jml_barang = jml_barang - NEW.jml_barang WHERE id = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_keuangan`
--

CREATE TABLE `tb_keuangan` (
  `id` int(11) NOT NULL,
  `Pemasukan` varchar(250) NOT NULL,
  `Departement` varchar(250) NOT NULL,
  `ukm` varchar(125) NOT NULL,
  `TanggalPemasukan` date NOT NULL,
  `Jenis_Pemasukan` varchar(25) NOT NULL,
  `Nominal` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_keuangan`
--

INSERT INTO `tb_keuangan` (`id`, `Pemasukan`, `Departement`, `ukm`, `TanggalPemasukan`, `Jenis_Pemasukan`, `Nominal`) VALUES
(3, 'Sumbangan', 'Kewirausahaan', 'HIMA TEKKOM', '2023-06-02', 'Proker', '1.000.000'),
(4, 'Project', 'Kewirausahaan', 'HIMA TEKKOM', '2023-06-15', 'Proker', '1.500.000');

-- --------------------------------------------------------

--
-- Table structure for table `tb_notif`
--

CREATE TABLE `tb_notif` (
  `id` int(99) NOT NULL,
  `ukm` varchar(255) NOT NULL,
  `nomor` int(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_notif`
--

INSERT INTO `tb_notif` (`id`, `ukm`, `nomor`) VALUES
(1, 'HIMA TEKKOM', 0),
(2, 'DPM', 0),
(5, 'HIMA RPL', 0),
(6, 'BEM', 0),
(7, 'HIMA PEDIA', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_peminjaman`
--

CREATE TABLE `tb_peminjaman` (
  `id` int(11) NOT NULL,
  `id_peminjam` int(11) NOT NULL,
  `ukm` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_peminjaman`
--

INSERT INTO `tb_peminjaman` (`id`, `id_peminjam`, `ukm`, `status`) VALUES
(1, 2103001, 'DPM', 'Pending'),
(2, 2103001, 'DPM', 'Pending'),
(3, 2102555, 'BEM', 'Accept'),
(4, 2102555, 'BEM', 'Decline');

-- --------------------------------------------------------

--
-- Table structure for table `tb_proker`
--

CREATE TABLE `tb_proker` (
  `id` int(11) NOT NULL,
  `proker` varchar(125) NOT NULL,
  `pelaksanaan` date NOT NULL,
  `department` varchar(125) NOT NULL,
  `pemasukan` varchar(125) NOT NULL,
  `nominal` varchar(125) NOT NULL,
  `ukm` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_proker`
--

INSERT INTO `tb_proker` (`id`, `proker`, `pelaksanaan`, `department`, `pemasukan`, `nominal`, `ukm`) VALUES
(9, 'DiesNatalis 3.0', '2023-07-11', 'MIKAT', 'Kas + Lembaga', '2.000.000', 'HIMA TEKKOM'),
(16, 'OrkaFest', '2023-08-17', 'KEMENDIKAT', 'Lembaga', '1.000.000', 'BEM'),
(18, 'Varsity', '2023-08-17', 'Kewirausahaan', 'Kas', '1000000', 'HIMA TEKKOM'),
(19, 'Study Club', '2023-07-29', 'MIKAT', 'Sponsor', '150000', 'HIMA TEKKOM'),
(20, 'PORAK', '2023-08-03', 'Sekretaris', 'Wakasek', '1000000', 'HIMA TEKKOM');

-- --------------------------------------------------------

--
-- Table structure for table `tb_status`
--

CREATE TABLE `tb_status` (
  `id` int(250) NOT NULL,
  `id_peminjam` int(250) NOT NULL,
  `ukm` varchar(250) NOT NULL,
  `barang` varchar(250) NOT NULL,
  `jumlah` int(250) NOT NULL,
  `status` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_ukm`
--

CREATE TABLE `tb_ukm` (
  `id` int(11) NOT NULL,
  `ukm` varchar(250) NOT NULL,
  `prodi` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_ukm`
--

INSERT INTO `tb_ukm` (`id`, `ukm`, `prodi`) VALUES
(1, 'HIMA TEKKOM', 'HIMA TEKKOM'),
(2, 'BEM', 'BEM'),
(10, 'HIMA PGSD', 'HIMA PGSD'),
(11, 'HIMA PAUD', 'HIMA PAUD'),
(12, 'HIMA RPL', 'HIMA RPL'),
(13, 'HIMA PEDIA', 'HIMA PEDIA'),
(14, 'DPM', 'DPM'),
(15, 'KSR', 'KSR'),
(16, 'BOMA', 'BOMA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_department`
--
ALTER TABLE `tb_department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_detail_peminjaman`
--
ALTER TABLE `tb_detail_peminjaman`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_keuangan`
--
ALTER TABLE `tb_keuangan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_notif`
--
ALTER TABLE `tb_notif`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_peminjaman`
--
ALTER TABLE `tb_peminjaman`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_proker`
--
ALTER TABLE `tb_proker`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_status`
--
ALTER TABLE `tb_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_ukm`
--
ALTER TABLE `tb_ukm`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  MODIFY `nim` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2777893;

--
-- AUTO_INCREMENT for table `tb_barang`
--
ALTER TABLE `tb_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `tb_department`
--
ALTER TABLE `tb_department`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `tb_detail_peminjaman`
--
ALTER TABLE `tb_detail_peminjaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_keuangan`
--
ALTER TABLE `tb_keuangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_notif`
--
ALTER TABLE `tb_notif`
  MODIFY `id` int(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_peminjaman`
--
ALTER TABLE `tb_peminjaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_proker`
--
ALTER TABLE `tb_proker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tb_status`
--
ALTER TABLE `tb_status`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_ukm`
--
ALTER TABLE `tb_ukm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
