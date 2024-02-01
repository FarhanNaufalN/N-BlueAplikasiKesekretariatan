<?php

include "koneksi.php"; // Sesuaikan dengan nama file konfigurasi database Anda

// Periksa koneksi database
if ($koneksi) {
    // Query untuk mendapatkan ID peminjaman terbaru
    $query = "SELECT MAX(id) AS latest_id FROM tb_peminjaman";

    $result = mysqli_query($koneksi, $query);

    if ($result) {
        $row = mysqli_fetch_assoc($result);

        // Periksa apakah kunci 'latest_id' ada dalam hasil
        if (isset($row['latest_id'])) {
            $latestID = $row['latest_id'];

            // Mengirimkan ID terbaru sebagai respons
            echo $latestID;
        } else {
            // Jika kunci 'latest_id' tidak ditemukan dalam hasil kueri
            echo "error";
        }
    } else {
        // Jika query gagal dijalankan
        echo "error";
    }

    // Menutup koneksi database
    mysqli_close($koneksi);
} else {
    // Jika koneksi database gagal
    echo "error";
}
?>
