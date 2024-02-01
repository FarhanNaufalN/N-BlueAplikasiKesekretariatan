<?php 

include "koneksi.php";

$nim = $_POST['nim'];

$sql = "SELECT * FROM tb_anggota WHERE nim = '".$_POST['nim']."'";

$query = mysqli_query($koneksi, $sql);

$data = mysqli_fetch_assoc($query);
echo json_encode(array(
    'data' => array(
        
        'nim' => $data['nim'],
        'nama' => $data['nama'],
        'jurusan'=> $data['jurusan'],
        'angkatan' => $data['angkatan'],
        'ukm' => $data['ukm'],
        'telp' => $data['telp'],
        'alamat'=> $data['alamat'],
        'jabatan' => $data['jabatan'],

    )
));