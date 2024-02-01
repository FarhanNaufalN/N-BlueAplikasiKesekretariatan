<?php

include 'koneksi.php';

// Menerima data UKM dari aplikasi Android
$ukm = $_POST['nim'];

// Melakukan filter SQL untuk mencegah SQL injection
$ukm = mysqli_real_escape_string($koneksi, $ukm);

// Membuat query untuk mengambil data berdasarkan UKM
$sql = "SELECT * FROM tb_anggota WHERE ukm = '$ukm'";
$query = mysqli_query($koneksi, $sql);

$list_data = array();

// Mengambil data hasil query
while ($data = mysqli_fetch_assoc($query)) {
    $list_data[] = array(
        'nim' => $data['nim'],
        'nama' => $data['nama'],
        'jurusan'=> $data['jurusan'],
        'angkatan' => $data['angkatan'],
        'ukm' => $data['ukm'],
        'telp' => $data['telp'],
        'alamat'=> $data['alamat'],
        'jabatan' => $data['jabatan'],
      
    );
}

// Mengirimkan data dalam format JSON ke aplikasi Android
echo json_encode(array(
    'data' => $list_data
));
?>
