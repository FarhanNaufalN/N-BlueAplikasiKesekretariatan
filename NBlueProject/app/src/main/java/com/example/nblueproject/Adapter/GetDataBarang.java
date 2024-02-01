package com.example.nblueproject.Adapter;

public class GetDataBarang {

    public GetDataBarang( String id, String nama,  String jml_barang, String ukm){

        this.nama=nama;
        this.id=id;
        this.jml_barang=jml_barang;
        this.ukm=ukm;


    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJml_barang() {
        return jml_barang;
    }

    public void setJml_barang(String jml_barang) {
        this.jml_barang = jml_barang;
    }

    public String getUkm() {
        return ukm;
    }

    public void setUkm(String ukm) {
        this.ukm = ukm;
    }

    String id="", nama ="",jml_barang ="", ukm="";
}
