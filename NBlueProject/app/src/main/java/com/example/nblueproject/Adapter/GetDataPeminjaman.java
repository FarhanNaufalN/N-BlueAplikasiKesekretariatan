package com.example.nblueproject.Adapter;

public class GetDataPeminjaman {
    public GetDataPeminjaman( String id, String nama,  String ukm, String namabrg, String jumlahpinjam, String status){

        this.namapeminjam=nama;
        this.id=id;
        this.status = status;
        this.ukm=ukm;
        this.namabrg=namabrg;
        this.jumlahpinjam=jumlahpinjam;
        this.jumlahbarang=jumlahbarang;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamabrg() {
        return namabrg;
    }

    public void setNamabrg(String namabrg) {
        this.namabrg = namabrg;
    }

    public String getJumlahpinjam() {
        return jumlahpinjam;
    }

    public void setJumlahpinjam(String jumlahpinjam) {
        this.jumlahpinjam = jumlahpinjam;
    }

    public String getNama() {
        return namapeminjam;
    }

    public void setNama(String nama) {
        this.namapeminjam = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUkm() {
        return ukm;
    }

    public void setUkm(String ukm) {
        this.ukm = ukm;
    }

    public String getJumlahbarang() {
        return jumlahbarang;
    }

    public void setJumlahbarang(String jumlahbarang) {
        this.jumlahbarang = jumlahbarang;
    }

    String id="", namapeminjam ="",status ="", ukm="", jumlahbarang="", namabrg="", jumlahpinjam="";


}
