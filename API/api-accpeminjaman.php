<?php

// Include konfigurasi database
include "koneksi.php"; // Sesuaikan dengan nama file konfigurasi database Anda

// Mendapatkan data dari aplikasi Java Swing
$id_peminjaman = $_POST['id_peminjaman'];
$action = $_POST['action'];

// Query untuk menolak atau menerima peminjaman
if ($action == "declice") {
    $updateQuery = "UPDATE tb_peminjaman SET status = 'Declice' WHERE id = '$id_peminjaman'";
} elseif ($action == "accept") {
    $updateQuery = "UPDATE tb_peminjaman SET status = 'Accept' WHERE id = '$id_peminjaman'";
} elseif ($action == "kembalikan"){
    $updateQuery = "UPDATE tb_peminjaman SET status = 'Sudah DiKembalikan' WHERE id = '$id_peminjaman'";
}

// Eksekusi query
if(mysqli_query($conn, $updateQuery)) {
    $response['status'] = "success";
    $response['message'] = "Status Peminjaman berhasil diubah";
} else {
    $response['status'] = "error";
    $response['message'] = "Status Peminjaman gagal diubah";
}

// Mengirimkan response sebagai JSON
echo json_encode($response);

// Menutup koneksi database
mysqli_close($conn);
?>
