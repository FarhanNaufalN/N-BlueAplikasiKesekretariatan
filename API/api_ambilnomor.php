<?php

include 'koneksi.php';

$ukm = $_POST['ukm'];

$id = mysqli_real_escape_string($koneksi, $ukm);
$query = "SELECT * FROM tb_notif WHERE ukm = '$ukm'";
$msql = mysqli_query($koneksi, $query);
$result = mysqli_num_rows($msql);

if ($result > 0) {
    // Ambil data dari hasil query
    $data = mysqli_fetch_assoc($msql);
    $nomor = $data['nomor'];

    // Kirim respons beserta data
    echo json_encode(array(
        'message' => 'Sukses',
        'nomor' => $nomor,
        
    ));
} else {
    echo json_encode(array(
        'message' => 'Gagal mengambil data atau data tidak ditemukan'
    ));
}

?>
