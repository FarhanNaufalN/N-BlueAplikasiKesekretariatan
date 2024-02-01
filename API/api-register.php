<?php

include 'koneksi.php';

$nim = $_POST['nim'];
$nama = $_POST['nama'];
$jurusan = $_POST['jurusan'];
$angkatan = $_POST['angkatan'];
$ukm = $_POST['ukm'];
$telp = $_POST['telp'];
$alamat = $_POST['alamat'];

$jabatan = $_POST['jabatan'];

$queryRegister = "SELECT * FROM tb_anggota WHERE nim = '".$nim."'"; 

$msql = mysqli_query ($koneksi, $queryRegister);

$result = mysqli_num_rows($msql);

if(!empty($nim) && !empty($nama) && !empty($jurusan) && !empty($angkatan) && !empty($ukm) && !empty($telp) && !empty($alamat) && !empty($jabatan)) {
    if ($result == 0) {
        $regis = "INSERT INTO tb_anggota (nim, nama, jurusan, angkatan, ukm, telp,alamat, jabatan)
        VALUES ('$nim', '$nama', '$jurusan', '$angkatan', '$ukm', '$telp', '$alamat', '$jabatan')";

        $msqlRegis = mysqli_query ($koneksi, $regis);

        echo"Daftar Berhasil";
    } else{
        echo "Nim Sudah Terdaftar";
    }

} else{
    echo "Semua Data Harus Di Isi";
}