<?php
include 'koneksi.php';

$ukm = $_POST['ukm'];

// Melakukan pengecekan apakah ada data di tabel tb_notif
$queryCheck = "SELECT * FROM tb_notif WHERE ukm = '$ukm'";
$msqlCheck = mysqli_query($koneksi, $queryCheck);
$resultCheck = mysqli_num_rows($msqlCheck);

if ($resultCheck > 0) {
    // Jika data dengan ukm tertentu sudah ada, lakukan update nomor
    $queryUpdate = "UPDATE tb_notif SET nomor = nomor + 1 WHERE ukm = '$ukm'";
    $msqlUpdate = mysqli_query($koneksi, $queryUpdate);

    if ($msqlUpdate) {
        echo "Nomor berhasil diupdate";
    } else {
        echo "Error: " . mysqli_error($koneksi);
    }
} else {
    // Jika data dengan ukm tertentu belum ada, lakukan insert dengan nomor awal 1
    $queryInsert = "INSERT INTO tb_notif (ukm, nomor) VALUES ('$ukm', 1)";
    $msqlInsert = mysqli_query($koneksi, $queryInsert);

    if ($msqlInsert) {
        echo "Data berhasil diinsert";
    } else {
        echo "Error: " . mysqli_error($koneksi);
    }
}
?>
