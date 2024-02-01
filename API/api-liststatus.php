<?php

include 'koneksi.php';

// Menerima data UKM dari aplikasi Android
$ukm = $_POST['ukm'];

// Melakukan filter SQL untuk mencegah SQL injection
$ukm = mysqli_real_escape_string($koneksi, $ukm);

// Membuat query untuk mengambil data berdasarkan UKM
$sql = "SELECT tb_peminjaman.id, tb_anggota.nama, tb_anggota.ukm, tb_peminjaman.status, tb_barang.nama AS nama_barang, tb_barang.jml_barang, tb_detail_peminjaman.jml_barang AS jml_barang_detail
        FROM tb_peminjaman
        JOIN tb_anggota ON tb_anggota.nim = tb_peminjaman.id_peminjam
        JOIN tb_detail_peminjaman ON tb_detail_peminjaman.id_peminjaman = tb_peminjaman.id
        JOIN tb_barang ON tb_barang.id = tb_detail_peminjaman.id_barang
        WHERE tb_anggota.ukm = '$ukm'";

$query = mysqli_query($koneksi, $sql);

$list_data = array();

// Mengambil data hasil query
while ($data = mysqli_fetch_assoc($query)) {
    $list_data[] = array(
        'id' => $data['id'],
        'nama' => $data['nama'],
        'ukm' => $data['ukm'],
        'nama_barang' => $data['nama_barang'],
        'jml_barang' => $data['jml_barang_detail'],
        'status' => $data['status'],
         // Menggunakan jumlah barang dari tb_detail_peminjam
    );
}

// Mengirimkan data dalam format JSON ke aplikasi Android
echo json_encode(array(
    'data' => $list_data
));
?>
