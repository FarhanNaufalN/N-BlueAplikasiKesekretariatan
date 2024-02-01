package com.example.nblueproject;

public class Notif {
    private String nomor;
    private static Notif instance;
    public static synchronized Notif getInstance() {
        if (instance == null) {
            instance = new Notif();
        }
        return instance;
    }

    private Notif() {
        // Private constructor to prevent instantiation
    }
    public String getNomor() {
        return nomor;
    }

    public void  setNomor(String nomor){
        this.nomor = nomor;
    }

}
