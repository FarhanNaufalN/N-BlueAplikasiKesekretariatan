<?php 

include "koneksi.php";

$id = $_POST['id'];

$sql = "SELECT * FROM tb_barang WHERE id = '".$_POST['id']."'";

$query = mysqli_query($koneksi, $sql);

$data = mysqli_fetch_assoc($query);
echo json_encode(array(
    'data' => array(
        'id' => $data['id'],
        'nama' => $data['nama'],
        'jml_barang' => $data['jml_barang'],
        'ukm' => $data['ukm'],
       

    )
));