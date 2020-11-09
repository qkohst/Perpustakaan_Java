-- phpMyAdmin SQL Dump
-- version 2.11.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 12, 2015 at 02:00 PM
-- Server version: 5.0.45
-- PHP Version: 5.2.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `perpustakaandb`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id` int(4) NOT NULL auto_increment,
  `no_anggota` varchar(10) collate latin1_general_ci NOT NULL,
  `nama` varchar(50) collate latin1_general_ci NOT NULL,
  `jk` varchar(15) collate latin1_general_ci NOT NULL,
  `telp` varchar(15) collate latin1_general_ci NOT NULL,
  `alamat` varchar(100) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id`, `no_anggota`, `nama`, `jk`, `telp`, `alamat`) VALUES
(1, 'A-1', 'Budi', 'Laki-laki', '0879837373', 'TJ'),
(2, 'A-2', 'Gatot', 'Laki-laki', '098984747', 'JT'),
(3, 'A-3', 'Ayu', 'Perempuan', '0898333988', 'Tanjung Seneng');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int(4) NOT NULL auto_increment,
  `kode` varchar(15) collate latin1_general_ci NOT NULL,
  `judul` varchar(50) collate latin1_general_ci NOT NULL,
  `pengarang` varchar(35) collate latin1_general_ci NOT NULL,
  `penerbit` varchar(35) collate latin1_general_ci NOT NULL,
  `isbn` varchar(30) collate latin1_general_ci NOT NULL,
  `stok` int(4) NOT NULL,
  `kategori_id` int(4) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `kode`, `judul`, `pengarang`, `penerbit`, `isbn`, `stok`, `kategori_id`) VALUES
(1, 'Aga-Gat-Ele-3', 'Kumpulan Hadist', 'Elexmedia', 'Gatot', '9848484', 3, 2),
(2, 'Kom-Max-Jam-2', 'Belajar Java Dasar', 'Jamiat Abdillah', 'Maxindo', '09848484', 79, 1),
(4, 'Kom-Max-Mar-3', 'Jaringan', 'Marmud', 'Maxindo', '90983738', 86, 1),
(5, 'Kom-Ele-Sue-4', 'Belajar Windows', 'Suef', 'Elexmedia', '93838383', 78, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(4) NOT NULL auto_increment,
  `nama` varchar(50) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama`) VALUES
(1, 'Komputer'),
(2, 'Agama'),
(3, 'Majalah'),
(4, 'Komik');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` int(4) NOT NULL auto_increment,
  `no_pinjam` varchar(15) collate latin1_general_ci NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `anggota_id` int(4) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `no_pinjam`, `tgl_pinjam`, `tgl_kembali`, `anggota_id`) VALUES
(1, 'P1412-1', '2014-12-09', '2014-12-19', 1),
(2, 'P1412-2', '2014-12-08', '2014-12-09', 2),
(3, 'P1214-3', '2014-12-30', '2014-12-31', 3),
(4, 'P1214-4', '2014-12-30', '2014-12-31', 2),
(5, 'P1214-5', '2014-12-30', '2014-12-31', 1),
(6, 'P0115-6', '2015-01-06', '2015-01-07', 3),
(7, 'P0115-7', '2015-01-06', '2015-01-07', 2);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_detail`
--

CREATE TABLE `peminjaman_detail` (
  `id_pinjam` int(4) NOT NULL,
  `id_buku` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `peminjaman_detail`
--

INSERT INTO `peminjaman_detail` (`id_pinjam`, `id_buku`) VALUES
(6, 4),
(6, 2),
(7, 5),
(7, 4);

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id` int(4) NOT NULL auto_increment,
  `nama` varchar(50) collate latin1_general_ci NOT NULL,
  `telp` varchar(15) collate latin1_general_ci NOT NULL,
  `alamat` varchar(50) collate latin1_general_ci NOT NULL,
  `username` varchar(35) collate latin1_general_ci NOT NULL,
  `password` varchar(35) collate latin1_general_ci NOT NULL,
  `hak_akses` varchar(30) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id`, `nama`, `telp`, `alamat`, `username`, `password`, `hak_akses`) VALUES
(3, 'Budi', '0', 'Tanjung Seneng', 'budi', 'budi', 'Admin'),
(4, 'Ayu', '0', 'Kedaton', 'ayu', 'ayu', 'Operator');

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
  `max_buku` int(2) NOT NULL,
  `batas_pinjam` int(2) NOT NULL,
  `denda` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`max_buku`, `batas_pinjam`, `denda`) VALUES
(3, 1, 3000);
