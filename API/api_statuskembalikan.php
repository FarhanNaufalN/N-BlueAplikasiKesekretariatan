<?php

include "koneksi.php";

// Menerima data dari aplikasi Android
$id = $_POST['id'];
$ukm = $_POST['ukm'];

// Melakukan filter SQL untuk mencegah SQL injection
$id = mysqli_real_escape_string($koneksi, $id);
$ukm = mysqli_real_escape_string($koneksi, $ukm);

// Query untuk melakukan pembaruan status menjadi 'Accept' dan mengubah nilai 'ukm'
$query = "UPDATE tb_peminjaman SET status = 'DiKembalikan', ukm = '$ukm' WHERE id = '$id'";

// Eksekusi query
if (mysqli_query($koneksi, $query)) {
    $response['status'] = "success";
    $response['message'] = "Status peminjaman berhasil diupdate";
} else {
    $response['status'] = "error";
    $response['message'] = "Gagal melakukan pembaruan status peminjaman";
}

// Mengirimkan response sebagai JSON
echo json_encode($response);

// Menutup koneksi database
mysqli_close($koneksi);

?>