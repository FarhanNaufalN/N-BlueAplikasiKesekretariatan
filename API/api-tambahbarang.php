<?php
include 'koneksi.php';

// Ambil ID UKM dari sesi (sesuaikan dengan nama sesi yang Anda gunakan)
$ukm_id = $_POST['ukm'];

// Ambil data dari POST
$nama_barang = $_POST['nama'];
$jumlah_barang = $_POST['jml_barang'];

// Validasi input
if (empty($nama_barang) || empty($jumlah_barang)) {
    echo "Lengkapi Data";
} else {
    // Periksa apakah data sudah terdaftar
    $queryCheck = "SELECT * FROM tb_barang WHERE ukm= '$ukm_id' AND nama = '$nama_barang'";
    $msqlCheck = mysqli_query($koneksi, $queryCheck);

    if ($msqlCheck === false) {
        echo "Error: " . mysqli_error($koneksi);
    } else {
        $result = mysqli_num_rows($msqlCheck);

        if ($result == 0) {
            // Jika belum terdaftar, tambahkan data baru
            $queryInsert = "INSERT INTO tb_barang (ukm, nama, jml_barang) VALUES ('$ukm_id', '$nama_barang', '$jumlah_barang')";
            $msqlInsert = mysqli_query($koneksi, $queryInsert);

            if ($msqlInsert) {
                echo "Tambah Barang Berhasil";
            } else {
                echo "Error: " . mysqli_error($koneksi);
            }
        } else {
            // Jika sudah terdaftar, update jumlah barang
            $queryUpdate = "UPDATE tb_barang SET jml_barang = jml_barang + '$jumlah_barang' WHERE ukm = '$ukm_id' AND nama = '$nama_barang'";
            $msqlUpdate = mysqli_query($koneksi, $queryUpdate);

            if ($msqlUpdate) {
                echo "Update Jumlah Barang Berhasil";
            } else {
                echo "Error: " . mysqli_error($koneksi);
            }
        }
    }
}
?>
