<?php

include 'koneksi.php';

// Menerima data UKM dari aplikasi Android
$ukm = $_POST['ukm'];

// Melakukan filter SQL untuk mencegah SQL injection
$ukm = mysqli_real_escape_string($koneksi, $ukm);

// Membuat query untuk mengambil data berdasarkan UKM
$sql = "SELECT * FROM tb_barang WHERE ukm = '$ukm'";
$query = mysqli_query($koneksi, $sql);

$list_data = array();

// Mengambil data hasil query
while ($data = mysqli_fetch_assoc($query)) {
    $list_data[] = array(
        'id' => $data['id'],
        'nama' => $data['nama'],
        'jml_barang'=> $data['jml_barang'],
        'ukm' => $data['ukm']
    );
}

// Mengirimkan data dalam format JSON ke aplikasi Android
echo json_encode(array(
    'data' => $list_data
));
?>
