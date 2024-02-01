<?php
include 'koneksi.php';

$ukm = $_POST['ukm'];

// Pernyataan SQL SELECT dengan kondisi
$query = "SELECT * FROM tb_notif WHERE ukm = '$ukm'";
$msqL = mysqli_query($koneksi, $query);

if ($msqL) {
    // Setelah menghapus data, kembalikan nilai kolom nomor menjadi 0
    $queryUpdateNomor = "UPDATE tb_notif SET nomor = 0 WHERE ukm = '$ukm'";
    $msqlUpdateNomor = mysqli_query($koneksi, $queryUpdateNomor);

    if ($msqlUpdateNomor) {
        echo "Data dengan ukm '$ukm' berhasil dihapus dan nilai nomor dikembalikan menjadi 0";
    } else {
        echo "Error updating nomor: " . mysqli_error($koneksi);
    }
} else {
    echo "Error deleting data: " . mysqli_error($koneksi);
}
?>
