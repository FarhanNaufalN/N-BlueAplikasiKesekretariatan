package com.example.nblueproject;

public class DetailPinjam {
    private static DetailPinjam instance;
    private String iddetail;
    private String idpeminjamanbrg;
    private String idbrg;
    private String jml_barang;// Tambahkan variabel untuk menyimpan nama


    private DetailPinjam() {
        // Private constructor to prevent instantiation
    }

    public static synchronized DetailPinjam getInstance() {
        if (instance == null) {
            instance = new DetailPinjam();
        }
        return instance;
    }
    public String getIddetail() {
        return iddetail;
    }

    public void  setIddetail(String iddetail){
        this.iddetail = iddetail;
    }
    public String getIdpeminjamanbrg() {
        return idpeminjamanbrg;
    }
    public String getIdbrg() {
        return idbrg;
    }

    public void  setIdpeminjamanbrg(String idpeminjamanbrg){
        this.idpeminjamanbrg = idpeminjamanbrg;
    }

    public void  setIdbrg(String idbrg){
        this.idbrg = idbrg;
    }

    public String getJml_barang() {
        return jml_barang;
    }

    public void setJml_barang(String jmlBarang) {
        this.jml_barang = jmlBarang;
    }


}
