package com.example.nblueproject;

public class User {
    private static User instance;
    private String email;
    private String ukm;
    private String nama;
    private String nim;// Tambahkan variabel untuk menyimpan nama

    private String idpeminjam;

    private String idbarang;
    private String stockbarang;

    private String jumlahpinjamm;

    private User() {
        // Private constructor to prevent instantiation
    }

    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    public String getStockbarang() {
        return stockbarang;
    }

    public void  setStockbarang(String stockbarang){
        this.stockbarang = stockbarang;
    }
    public String getEmail() {
        return email;
    }
    public String getIdpeminjam() {
        return idpeminjam;
    }

    public void  setIdpeminjam(String idpeminjam){
        this.idpeminjam = idpeminjam;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void  setIdbarang(String idbarang){
        this.idbarang = idbarang;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUkm() {
        return ukm;
    }

    public void setUkm(String ukm) {
        this.ukm = ukm;
    }

    // Tambahkan method untuk mendapatkan nama
    public String getNama() {
        return nama;
    }

    // Tambahkan method untuk mengatur nama
    public void setNama(String nama) {
        this.nama = nama;
    }


    // Tambahkan method untuk mendapatkan nim
    public String getNim() {
        return nim;
    }

    // Tambahkan method untuk mengatur nim
    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJumlahpinjamm() {
        return jumlahpinjamm;
    }

    public void  setJumlahpinjamm(String jumlahpinjamm){
        this.jumlahpinjamm = jumlahpinjamm;
    }


}