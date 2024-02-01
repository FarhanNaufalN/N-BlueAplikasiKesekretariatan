<?php

include "koneksi.php"; // Sesuaikan dengan nama file konfigurasi database Anda

// Mendapatkan data dari aplikasi Android
$id_peminjaman = $_POST['id_peminjaman']; // Sesuaikan dengan nama parameter yang dikirim dari Android
$id_barang = $_POST['id_barang']; // Sesuaikan dengan nama parameter yang dikirim dari Android
$jml_barang = $_POST['jml_barang']; // Sesuaikan dengan nama parameter yang dikirim dari Android

// Query untuk memasukkan detail peminjaman ke dalam database
$query = "INSERT INTO tb_detail_peminjaman (id_peminjaman, id_barang, jml_barang) VALUES ('$id_peminjaman', '$id_barang', '$jml_barang')";

// Eksekusi query
if(mysqli_query($koneksi, $query)) {
    // Jika query berhasil dijalankan
    $response['status'] = "success";
    $response['message'] = "Detail peminjaman berhasil ditambahkan";
} else {
    // Jika query gagal dijalankan
    $response['status'] = "error";
    $response['message'] = "Detail peminjaman gagal ditambahkan";
}

// Mengirimkan response sebagai JSON
echo json_encode($response);

// Menutup koneksi database
mysqli_close($koneksi);

?>
