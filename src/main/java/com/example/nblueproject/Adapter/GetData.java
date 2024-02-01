package com.example.nblueproject.Adapter;

public class GetData {
    String nim="", nama ="",jabatan ="",angkatan="",telp="",jurusan="", ukm="", alamat="";
    public GetData( String nim, String nama,  String jabatan, String angkatan, String telp){

        this.nama=nama;
        this.nim=nim;
        this.jurusan=jurusan;
        this.angkatan=angkatan;
        this.ukm=ukm;
        this.telp=telp;
        this.alamat=alamat;
        this.jabatan=jabatan;

    }




    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
    public String getJurusan() {
        return jurusan;
    }

    public String getUkm() {
        return ukm;
    }
    public String getAlamat() {
        return alamat;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public String getTelp() {
        return telp;
    }


}
