<?php

include 'koneksi.php';

$email = $_POST['email'];
$password = $_POST['password'];

$cek = "SELECT * FROM tb_anggota WHERE email = '$email' AND password = '$password'";
$msql = mysqli_query($koneksi, $cek);
$result = mysqli_num_rows($msql);

if (!empty($email) && !empty($password)) {
    if ($result == 0) {
        echo json_encode(array(
            'message' => 'Email dan Password Tidak Sesuai'
        ));
    } else {
        // Ambil UKM dari hasil query
        $data = mysqli_fetch_assoc($msql);
        $ukm = $data['ukm'];
        $nim = $data['nim'];
        $nama = $data['nama'];
        

        // Kirim respons beserta UKM
        echo json_encode(array(
            'message' => 'Selamat Datang',
            'ukm' => $ukm,
            'nim' => $nim,
            'nama'=> $nama,
            
        ));
    }
} else {
    echo json_encode(array(
        'message' => 'Ada Data Yang Masih Kosong'
    ));
}

?>

