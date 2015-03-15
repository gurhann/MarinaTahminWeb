/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.marinatahminweb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gurhan
 */
public class Tarih {
    private String tarih;
    private int kolonSayisi;
    private List<Tahmin> gunTahminleri;

   
    public Tarih(){
        gunTahminleri = new ArrayList<>();
    }
    public Tarih (String tarih, int kolonSayisi) {
        this.tarih = tarih;
        this.kolonSayisi = kolonSayisi;
        gunTahminleri = new ArrayList<>();
    }
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String Tarih) {
        this.tarih = Tarih;
    }

    public List<Tahmin> getGunTahminleri() {
        return gunTahminleri;
    }

    public void setGunTahminleri(ArrayList<Tahmin> gunTahminleri) {
        this.gunTahminleri = gunTahminleri;
    }
    
     public int getKolonSayisi() {
        return kolonSayisi;
    }

    public void setKolonSayisi(int kolonSayisi) {
        this.kolonSayisi = kolonSayisi;
    }
    
}
