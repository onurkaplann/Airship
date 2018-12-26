package Zeplin;

import java.io.IOException;
import java.util.*;
import java.math.*;

public class Zeplin {

    public Zeplin() {

    }

    public static double egim_hesapla(int a, int b, LinkedList<Vertex> vertex_ll) {  //ys=yolcu sayısı
        double mesafe = mesafe_hesapla(a, b, vertex_ll);
        double kot = (vertex_ll.get(a).z) - (vertex_ll.get(b).z);
        if (kot < 0) {         //Mutlak deger
            kot = kot * (-1.0);
        }
        //kot+=50;
        double derece = Math.atan2(kot, mesafe);
        derece = Math.toDegrees(derece);
        if (derece < 0) {       //Mutlak deger
            derece = derece * (-1.0);
        }
        return derece;
    }

    public static double mesafe_hesapla(int a, int b, LinkedList<Vertex> vertex_ll) {
        double lat = vertex_ll.get(a).y;
        double lat1 = vertex_ll.get(b).y;
        double longitute = vertex_ll.get(a).x;
        double longitute1 = vertex_ll.get(b).x;
        double theta = longitute - longitute1;
        double miles = Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(lat1)) + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(theta));
        miles = Math.acos(miles);
        miles = Math.toDegrees(miles);
        miles = miles * 60 * 1.1515;
        double km = miles * 1.609344;
        return km;

    }

    public static double mesafe_hesapla(int a, int b, LinkedList<Vertex> vertex_ll,int ys) {
        double mesafe = mesafe_hesapla(a, b, vertex_ll);
        double derece = egim_hesapla(a, b, vertex_ll);
        double km = mesafe * (1 / Math.cos(derece));

        if (km < 0) {      //Mutlak deger
            km = km * (-1);
        }
        return km;

    }

    public static void elli_kar_problemi(int baslangic, int bitis, deoes5 bir, LinkedList<Vertex> vertex_ll) throws IOException {  //Dosya
        double mesafeler[] = new double[82];
        double maliyet = 0.0;
        double gelir = 0.0;
        double bilet_fiyatı = 0.0;

        for (int i = 10; i < 55; i += 10) {
            bir.dj(baslangic, vertex_ll, i, bitis);
            mesafeler = bir.mesafeler_dondur();

            if (mesafeler[bitis] == 10479) {
                System.out.println(i + "Yolcu ile gidilebilir yol :  Gidilemiyor...");
            } else {

                maliyet = mesafeler[bitis] * 10;
                gelir = maliyet * 1.5;
                bilet_fiyatı = gelir / i;
                System.out.println(i + " yolcu ile %50 kâr elde etmek için bilet fiyati :" + bilet_fiyatı);
                System.out.print(i + " Yolcu ile gidilebilir yol :");
                bir.en_kisa_yol_bul(baslangic, bitis, vertex_ll);
                
            }
        }
    }

    public static void kac_yolcu(int bilet_fiyati_int,int baslangic,int bitis, deoes5 bir, LinkedList<Vertex> vertex_ll) throws IOException {
        
        double mesafeler[] = new double[82];
        double en_cok_kar[] = new double[51];
        
        for(int i=0;i<51;i++){
            en_cok_kar[i]=0;
        }
        int sayac=0;
        double maliyet = 0.0;
        double gelir = 0.0;
        double bilet_fiyatı_double = (int)(double) bilet_fiyati_int;
        double yuzde_kac_kar;
        
         for(int i=5;i<51;i++){
            bir.dj(baslangic, vertex_ll, i, bitis);
            mesafeler = bir.mesafeler_dondur();

            if (mesafeler[bitis] == 10479) {                    //Bitis e giden yol yoksa ;
                    System.out.println(i+" Yolcu ile gidilebilir yol :  Gidilemiyor...");
                    break;
                } else {
            sayac++;
            maliyet=mesafeler[bitis]*10;        //Maliyet hesapla
            gelir=i*bilet_fiyatı_double;        //Bilet fiyati hesapla
            yuzde_kac_kar= ((gelir-maliyet)/maliyet)*100;   //Yuzde kac kar ettigini hesapla
            en_cok_kar[i]=yuzde_kac_kar;            //Ettigin kârı dizide tut.
            
           }
         }
          if(sayac !=0 ){
            int max_index=0;
            double max=0;

            for(int i=5;i<51;i++){                  // EN COK KAC YOLCU ILE KAR ETTIGINI BUL)(max_index)      
                if(max < en_cok_kar[i] ){
                    max=en_cok_kar[i];
                    max_index=i;
                }
            }
            System.out.println("");
            System.out.println("");
            System.out.println("En çok kâr ettigim yolcu sayisi ile ettigim kâr : "+max);
            System.out.println("En çok kâr ettigim yolcu sayisi : "+max_index);
            System.out.println("");
            System.out.println("En çok kâr ettigim yol : ");
            bir.dj(baslangic, vertex_ll, max_index, bitis);
            bir.en_kisa_yol_bul(baslangic, bitis, vertex_ll);
            
            System.out.println("");
            System.out.println("");
          }   
     }
}
