/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.marinatahminweb.model;

/**
 *
 * @author gurhan-pc
 */
public class Liman {
    private int id;
    private String isim;

    public Liman() {
        
    }
    public Liman(int id, String isim) {
        this.id = id;
        this.isim = isim;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }
    
    
}
