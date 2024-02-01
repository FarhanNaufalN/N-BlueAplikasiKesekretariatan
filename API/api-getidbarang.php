<?php

include "koneksi.php"; // Sesuaikan dengan nama file konfigurasi database Anda

// Periksa koneksi database
if ($koneksi) {
    // Query untuk mendapatkan semua ID barang dari tb_barang
    $query = "SELECT id FROM tb_barang";

    $result = mysqli_query($koneksi, $query);

    if ($result) {
        $idbarang = array();

        // Loop through the result to fetch all IDs
        while ($row = mysqli_fetch_assoc($result)) {
            $idbarang[] = $row['id'];
        }

        // Mengirimkan IDs sebagai respons dalam format JSON
        echo json_encode($idbarang);
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
