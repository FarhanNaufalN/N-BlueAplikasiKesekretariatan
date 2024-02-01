<?php

include "koneksi.php";

$nim = $_POST['nim'];
$sql = "DELETE FROM tb_anggota WHERE nim = '$nim'";

$query = mysqli_query($koneksi, $sql);
if ($query){
    echo json_encode(array(
        'status' => 'success',
    ));
} else {
    echo "Gagal";
}
