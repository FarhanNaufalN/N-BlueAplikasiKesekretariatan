<?php

include 'koneksi.php';

$email = $_GET['email'];

// Melakukan kueri SQL untuk mendapatkan nama pengguna dan UKM berdasarkan email
$query = "SELECT nim, nama, ukm FROM tb_anggota WHERE email='$email'";
$result = $koneksi->query($query);

if ($result->num_rows > 0) {
    // Mendapatkan hasil kueri dan mengembalikan nama pengguna dan UKM sebagai respons
    $row = $result->fetch_assoc();
    echo "Nim: " . $row['nim'] ."Nama: " . $row['nama'] . ", UKM: " . $row['ukm'];
} else {
    // Email tidak ditemukan, mengembalikan pesan kesalahan
    echo "Email tidak ditemukan";
}

// Menutup koneksi database
$koneksi->close();
?>

