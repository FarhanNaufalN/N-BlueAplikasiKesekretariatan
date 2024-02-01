<?php

include "koneksi.php";

// Assuming your form sends the update data using POST method
$id = $_POST['id'];
$namabarang = $_POST['nama'];
$jumlah_barang = $_POST['jml_barang'];


// Assuming 'nim' is the primary key for your table
$sql = "UPDATE tb_barang 
        SET nama = '$namabarang', jml_barang = '$jumlah_barang'
        WHERE id = '$id'";

$query = mysqli_query($koneksi, $sql);

if ($query) {
    echo "Update successful";
} else {
    echo "Update failed";
}
?>
