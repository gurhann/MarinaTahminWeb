/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.marinatahminweb.rest;

import com.gurhan.marinatahminweb.business.MarinaUcGunlukTahmin;
import com.gurhan.marinatahminweb.model.Liman;
import com.gurhan.marinatahminweb.model.Tarih;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.validation.constraints.Past;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gurhan-pc
 */
@Singleton
@Path("Marina")
public class MarinaTest {
    @EJB
    private MarinaUcGunlukTahmin marinaTahmin;
    
    private HashMap<Integer, String> limanlar;
    private HashMap<Integer, ArrayList<Tarih>> marinaUcGunlukTahminler;
    @PostConstruct
    public void init(){
        limanlar = new HashMap<>();
        limanlar.put(17612, "AKÇAKOCA");
        limanlar.put(17310, "ALANYA");
        limanlar.put(17602, "AMASRA");
        limanlar.put(17320, "ANAMUR");
        limanlar.put(17300, "ANTALYA");
        limanlar.put(17060, "ATAKÖY");
        limanlar.put(17175, "AYVALIK");
        limanlar.put(17115, "BANDIRMA");
        limanlar.put(17290, "BODRUM");
        limanlar.put(17990, "YALIKAVAK (Bodrum)");
        limanlar.put(17111, "BOZCAADA");
        limanlar.put(17112, "ÇANAKKALE");
        limanlar.put(17221, "ÇEŞME");
        limanlar.put(17233, "DİDİM");
        limanlar.put(17611, "EREĞLİ (Karadeniz)");
        limanlar.put(17296, "ECE (Fethiye)");
        limanlar.put(17375, "FİNİKE");
        limanlar.put(17034, "GİRESUN");
        limanlar.put(17067, "GÖLCÜK");
        limanlar.put(17042, "HOPA");
        limanlar.put(17024, "İNEBOLU");
        limanlar.put(17370, "İSKENDERUN");
        limanlar.put(17220, "İZMİR");
        limanlar.put(17380, "KAŞ");
        limanlar.put(17907, "KALAMIŞ");
        limanlar.put(17953, "KEMER");
        limanlar.put(17232, "KUŞADASI");
        limanlar.put(17991, "YACHT (Marmaris)");
        limanlar.put(17340, "MERSİN");
        limanlar.put(17033, "ORDU");
        limanlar.put(17040, "RİZE");
        limanlar.put(17031, "SAMSUN");
        limanlar.put(17330, "TAŞUCU");
        limanlar.put(17056, "TEKİRDAĞ");
        limanlar.put(17038, "TRABZON");
        limanlar.put(17627, "TURGUT REİS");
        limanlar.put(17026, "SİNOP");
        limanlar.put(17610, "ŞİLE");
        limanlar.put(17119, "YALOVA");
        limanlar.put(17979, "YUMURTALIK");
        limanlar.put(17022, "ZONGULDAK");
        limanlar.put(17540, "GAZİMAGOSA (K.K.T.C.)");
        limanlar.put(17510, "GİRNE (K.K.T.C.)");
        limanlar.put(16749, "RODOS (Yunanistan)");
        limanlar.put(16667, "MİDİLLİ (Yunanistan)");
        marinaUcGunlukTahminler = new HashMap<>();
    }
    
    @Schedule(second = "*", minute = "*/5", hour = "*")
    public void marinaTahminleriniGuncelle() throws IOException {
        System.out.println("timer çalıştı");
        marinaUcGunlukTahminler.clear();;
        for(Integer key : limanlar.keySet()) {
            marinaUcGunlukTahminler.put(key, marinaTahmin.ucGunlukTahminAl(key));
        }
    }
    
    @GET
    @Path("/tahminAl/{marinaId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Tarih> tahminAl(@PathParam("int") int marinaId) {
        return marinaUcGunlukTahminler.get(marinaId);
    }
}
