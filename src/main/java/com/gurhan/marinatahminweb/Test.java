/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.marinatahminweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author gurhan
 */
public class Test {

    public static void main(String args[]) throws IOException {
        List<Tarih> gunler = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.mgm.gov.tr/deniz/marina-tahmin.aspx?m=17320#sfB").get();

        Element contentDiv = doc.getElementById("govde_ust_genis_icerik_sayfa");
        Element table = contentDiv.getElementsByTag("table").first();

        //ilk tbody alinır bu tbody gün ve saatleri içerir
        Element gunlerTbody = table.getElementsByTag("tbody").get(0);
        gunleriHazirla(gunler, gunlerTbody);
        saatleriHazirla(gunler, gunlerTbody);

        //ikinci tbody kısmında ise tahmin bilgileri bulunur
        Element tahminTbody = table.getElementsByTag("tbody").get(1);
        havaDurumlariniAl(gunler, tahminTbody);
        sicakliklariAl(gunler, tahminTbody);
        ruzgarYonleriniAl(gunler, tahminTbody);
        ruzgarHizlariniAl(gunler, tahminTbody);
        dalgaRuzgarYonuAl(gunler, tahminTbody);
        dalgaRuzgarHiziAl(gunler, tahminTbody);
        dalgaYonuAl(gunler, tahminTbody);
        dalgaYuksekligiAl(gunler, tahminTbody);
        listele(gunler);

    }

    private static void listele(List<Tarih> gunler) {
        for (Tarih t : gunler) {
            System.out.println(t.getTarih());
            for (Tahmin tt : t.getGunTahminleri()) {
                System.out.println(tt.getSaat() + "\n" + tt.getHavaDurumu()
                        + " : " + tt.getSicaklik() + " : "
                        + tt.getRuzgarYonu() + " : "
                        + tt.getRuzgarHizi() + "\n"
                        + tt.getDalgaRuzgarYonu() + " : "
                        + tt.getDalgaRuzgarHizi()+ " \n "
                        + tt.getDalgaYonu() + " : "
                        + tt.getDalgaYuksekligi());
            }
        }
    }

    private static void gunleriHazirla(List<Tarih> gunler, Element tbody) {
        Elements gunlerElements = tbody.getElementsByTag("tr").get(0).getAllElements();

        for (int i = 2; i < gunlerElements.size(); i++) {
            Element e = gunlerElements.get(i);
            String tarihT = e.text();
            int kolonSayisi = Integer.parseInt(e.attr("colspan"));
            Tarih tarih = new Tarih(tarihT, kolonSayisi);
            gunler.add(tarih);
        }
    }

    private static void saatleriHazirla(List<Tarih> gunler, Element tbody) {
        Elements saatlerElements = tbody.getElementsByTag("tr").get(1).getAllElements();
        int gunSayac = 0;
        for (int i = 2; i < saatlerElements.size(); i++) {
            Element e = saatlerElements.get(i);
            if (e.hasAttr("style")) {
                gunSayac++;
            }
            String saat = e.text();
            Tahmin t = new Tahmin();
            t.setSaat(saat);
            gunler.get(gunSayac).getGunTahminleri().add(t);
        }

    }

    private static void havaDurumlariniAl(List<Tarih> gunler, Element tbody) {
        Elements havaDurumlari = tbody.getElementsByTag("tr").get(1).getElementsByTag("td");

        int gunSayac = 0;
        int tahminSayac = 0;

        for (Element e : havaDurumlari) {
            String durum;
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            if (e.hasAttr("title") && !e.attr("title").equals("")) {
                durum = e.attr("title");
            } else {
                durum = "Yağışlı";
            }

            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setHavaDurumu(durum);
            tahminSayac++;
        }
    }

    private static void sicakliklariAl(List<Tarih> gunler, Element tbody) {
        Elements sicakliklar = tbody.getElementsByTag("tr").get(2).getElementsByTag("td");

        int gunSayac = 0;
        int tahminSayac = 0;

        for (Element e : sicakliklar) {

            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            double sicaklik = Double.parseDouble(e.text());
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setSicaklik(sicaklik);
            tahminSayac++;
        }
    }

    private static void ruzgarYonleriniAl(List<Tarih> gunler, Element tbody) {
        Elements ruzgarYonleri = tbody.getElementsByTag("tr").get(3).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        String ruzgarYonu;
        for (Element e : ruzgarYonleri) {

            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            ruzgarYonu = e.attr("title");
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setRuzgarYonu(ruzgarYonu);
            tahminSayac++;
        }
    }

    private static void ruzgarHizlariniAl(List<Tarih> gunler, Element tbody) {

        Elements ruzgarHizlari = tbody.getElementsByTag("tr").get(4).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        String ruzgarHizi;
        for (Element e : ruzgarHizlari) {
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            ruzgarHizi = e.attr("title");
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setRuzgarHizi(ruzgarHizi);
            tahminSayac++;
        }
    }

    private static void dalgaRuzgarYonuAl(List<Tarih> gunler, Element tbody) {

        Elements dalgaRuzgarYonleri = tbody.getElementsByTag("tr").get(6).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        String dalgaRuzgarYonu;

        for (Element e : dalgaRuzgarYonleri) {
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            dalgaRuzgarYonu = e.attr("title");
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setDalgaRuzgarYonu(dalgaRuzgarYonu);
            tahminSayac++;
        }
    }
    
    private static void dalgaRuzgarHiziAl(List<Tarih> gunler, Element tbody) {
        Elements dalgaRuzgarHizlari = tbody.getElementsByTag("tr").get(7).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        String dalgaRuzgarHizi;

        for (Element e : dalgaRuzgarHizlari) {
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            dalgaRuzgarHizi = e.attr("title");
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setDalgaRuzgarHizi(dalgaRuzgarHizi);
            tahminSayac++;
        }
    }
    
    private static void dalgaYonuAl(List<Tarih> gunler, Element tbody) {
        Elements dalgaYonleri = tbody.getElementsByTag("tr").get(8).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        String dalgaYonu;

        for (Element e : dalgaYonleri) {
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            dalgaYonu = e.attr("title");
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setDalgaYonu(dalgaYonu);
            tahminSayac++;
        }
    }
    
    private static void dalgaYuksekligiAl(List<Tarih> gunler, Element tbody) {
        Elements dalgaYukseklikleri = tbody.getElementsByTag("tr").get(9).getElementsByTag("td");
        int gunSayac = 0;
        int tahminSayac = 0;
        Double dalgaYuksekligi;

        for (Element e : dalgaYukseklikleri) {
            if (e.attr("style").contains("border-left")) {
                gunSayac++;
                tahminSayac = 0;
            }
            dalgaYuksekligi = Double.parseDouble(e.text());
            gunler.get(gunSayac).getGunTahminleri().get(tahminSayac).setDalgaYuksekligi(dalgaYuksekligi);
            tahminSayac++;
        }
    }
}
