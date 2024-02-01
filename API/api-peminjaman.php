<?php
// Include konfigurasi database
include "koneksi.php"; // Sesuaikan dengan nama file konfigurasi database Anda

// Mendapatkan data dari aplikasi Android
$id_peminjam = $_POST['id_peminjam']; // Sesuaikan dengan nama parameter yang dikirim dari Android
$ukm = $_POST['ukm']; // Sesuaikan dengan nama parameter yang dikirim dari Android
 // Sesuaikan dengan nama parameter yang dikirim dari Android

// Query untuk memasukkan data peminjaman ke dalam database
$query = "INSERT INTO tb_peminjaman (id_peminjam, ukm, status) VALUES ('$id_peminjam', '$ukm', 'Pending')";

// Eksekusi query
if(mysqli_query($koneksi, $query)) {
    // Jika query berhasil dijalankan
    $response['status'] = "success";
    $response['message'] = "Peminjaman berhasil diajukan";
} else {
    // Jika query gagal dijalankan
    $response['status'] = "error";
    $response['message'] = "Peminjaman gagal diajukan";
}

// Mengirimkan response sebagai JSON
echo json_encode($response);

// Menutup koneksi database
mysqli_close($koneksi);
?>
