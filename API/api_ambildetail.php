<?php

include 'koneksi.php';

$id_peminjaman_detail = $_POST['id'];

$id = mysqli_real_escape_string($koneksi, $id_peminjaman_detail);
$query = "SELECT * FROM tb_detail_peminjaman WHERE id_peminjaman = '$id_peminjaman_detail'";
$msql = mysqli_query($koneksi, $query);
$result = mysqli_num_rows($msql);

if ($result > 0) {
    // Ambil data dari hasil query
    $data = mysqli_fetch_assoc($msql);
    $id_detail = $data['id'];
    $id_peminjaman = $data['id_peminjaman'];
    $id_barang = $data['id_barang'];
    $jml_barang = $data['jml_barang'];

    // Kirim respons beserta data
    echo json_encode(array(
        'message' => 'Sukses',
        'id' => $id_detail,
        'id_peminjaman' => $id_peminjaman,
        'id_barang' => $id_barang,
        'jml_barang' => $jml_barang,
    ));
} else {
    echo json_encode(array(
        'message' => 'Gagal mengambil data atau data tidak ditemukan'
    ));
}

?>
