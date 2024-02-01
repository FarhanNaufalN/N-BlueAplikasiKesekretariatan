<?php
include 'koneksi.php';

// Ambil ID Barang dari POST
$idBarang = $_POST['id'];

// Ambil jumlah barang yang akan dikembalikan
$jmlBarang = $_POST['jml_barang'];

// Validasi input
if (!empty($idBarang) && is_numeric($jmlBarang) && $jmlBarang > 0) {

    // Ambil data detail peminjaman
    $queryDetail = "SELECT * FROM tb_detail_peminjaman WHERE id_barang = '$idBarang'";
    $msqlDetail = mysqli_query($koneksi, $queryDetail);

    if ($msqlDetail) {
        $resultDetail = mysqli_num_rows($msqlDetail);

        if ($resultDetail > 0) {
            // Ambil data detail peminjaman
            $dataDetail = mysqli_fetch_assoc($msqlDetail);
            $idPeminjaman = $dataDetail['id_peminjaman'];
            $idBarang = $dataDetail['id_barang'];
            $jmlBarangDetail = $dataDetail['jml_barang'];

            // Update jumlah barang di tabel tb_barang
            $queryUpdate = "UPDATE tb_barang SET jml_barang = jml_barang + '$jmlBarang' WHERE id = '$idBarang'";
            $msqlUpdate = mysqli_query($koneksi, $queryUpdate);

            if ($msqlUpdate) {
                // Hapus data detail peminjaman
                $queryDelete = "DELETE FROM tb_detail_peminjaman WHERE id = '$idBarang'";
                $msqlDelete = mysqli_query($koneksi, $queryDelete);

                if ($msqlDelete) {
                    echo json_encode(array(
                        'message' => 'Berhasil Kembalikan Barang'
                    ));
                } else {
                    echo json_encode(array(
                        'message' => 'Gagal menghapus data detail peminjaman'
                    ));
                }
            } else {
                echo json_encode(array(
                    'message' => 'Gagal mengupdate jumlah barang di tb_barang'
                ));
            }
        } else {
            echo json_encode(array(
                'message' => 'Data detail peminjaman tidak ditemukan'
            ));
        }
    } else {
        echo json_encode(array(
            'message' => 'Error: ' . mysqli_error($koneksi)
        ));
    }
} else {
    echo json_encode(array(
        'message' => 'Data tidak valid'
    ));
}
?>
