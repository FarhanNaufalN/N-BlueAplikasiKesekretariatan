<?php

include "koneksi.php";

// Assuming your form sends the update data using POST method
$nim = $_POST['nim'];
$nama = $_POST['nama'];
$jurusan = $_POST['jurusan'];
$angkatan = $_POST['angkatan'];
$ukm = $_POST['ukm'];
$telp = $_POST['telp'];
$alamat = $_POST['alamat'];
$jabatan = $_POST['jabatan'];

// Assuming 'nim' is the primary key for your table
$sql = "UPDATE tb_anggota 
        SET nama = '$nama', jurusan = '$jurusan', angkatan = '$angkatan', ukm = '$ukm', 
            telp = '$telp', alamat = '$alamat', jabatan = '$jabatan'
        WHERE nim = '$nim'";

$query = mysqli_query($koneksi, $sql);

if ($query) {
    echo "Update successful";
} else {
    echo "Update failed";
}
?>
