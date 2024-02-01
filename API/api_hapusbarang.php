<?php

include "koneksi.php";

$id = $_POST['id'];
$sql = "DELETE FROM tb_barang WHERE id = '$id'";

$query = mysqli_query($koneksi, $sql);
if ($query){
    echo json_encode(array(
        'status' => 'success',
    ));
} else {
    echo "Gagal";
}