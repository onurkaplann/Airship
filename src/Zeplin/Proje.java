package Zeplin;
import java.io.*;
import java.util.*;


public class Proje {

    public static void main(String[] args) throws IOException {
        
        
            int baslangic , bitis , ys,k;
            Scanner sc = new Scanner(System.in);
            System.out.println("Baslangic sehrini giriniz :");
            baslangic= sc.nextInt();
            System.out.println("Varis sehrini giriniz :");
            bitis= sc.nextInt();
            System.out.println("%50 kar problemi için (1) giriniz.En çok kar problemi için (2) giriniz.Dijkstra fonksiyonu için (3) giriniz.");
            k=sc.nextInt();
            long startTime=System.currentTimeMillis();
        
        LinkedList<Vertex> vertex_ll = new LinkedList();
        vertex_ll.add(null);
        String id1;     //İsim
        double x1,y1,z1;//koordinatlar
        int komsu_sayisi1;
        int plaka1;
        
        String temp[] = new String[10];      //Gereksiz-gecici
        Vertex iller[] = new Vertex[81];    //Gereksiz-gecici
        int komsuluk[][] = new int[81][15];
        int i=0;                            //Gereksiz-gecici
    
                        //Vertexler ile ilgili lat,long gibi bilgileri dosyadan okuma
                        //Ve 81 tane vertex oluşturup linked list e ekleme
        try {
            FileReader fr = new FileReader("İller.txt");
        BufferedReader br = new BufferedReader(fr);
        String satir;
        
            while( (satir=br.readLine())!=null ){
            
            temp =satir.split(",");
            x1= Double.parseDouble(temp[0]);
            y1= Double.parseDouble(temp[1]);
            z1= Double.parseDouble(temp[3]);
            plaka1= Integer.parseInt(temp[2]);
            id1=temp[4];
           
            iller[i]=new Vertex(y1, x1, plaka1, z1, id1);   //81 tane vertex olusturma
            vertex_ll.addLast( iller[i] );                    
            i++;
            }

        } catch (IOException e) {
            System.out.println("İller dosyası bulunamadı !!");         
        }                                                      

        i=0;
        try {
            FileReader fr1 = new FileReader("komşu.txt");
            BufferedReader br1 = new BufferedReader(fr1);
            String satir1;
            
            while( (satir1=br1.readLine())!=null ){
            
            temp =satir1.split(",");
            
            for(int j=0;j<temp.length;j++)
               komsuluk[i][j]=Integer.parseInt(temp[j]);
            i++;
            }
        } catch (IOException e) {
            System.out.println("Komşu dosyası bulunamadı !!");
        }
        
        for(i=0;i<81;i++){
            int j;
            for(j=0;komsuluk[i][j]!=0;j++){}    //Komsu sayisi sayma
                
            vertex_ll.get(i+1).komsu_ekle(vertex_ll, komsuluk[i],j);
        }
            deoes5 bir = new deoes5(); 
            
            if(k==1){
                Zeplin.elli_kar_problemi(baslangic, bitis, bir, vertex_ll);
                long endTime = System.currentTimeMillis();
                long results = endTime - startTime;
                double seconds = (double) results/1000;
                System.out.println("Programın çalışma süresi:"+seconds);
            }
            
            if(k==2){
                long endTime = System.currentTimeMillis();
                System.out.println("Bilet fiyatı giriniz :");
                k=sc.nextInt();
                long results = endTime - startTime;
                long startTime1=System.currentTimeMillis();
                 Zeplin.kac_yolcu(k, baslangic, bitis, bir, vertex_ll);
                long endTime1 = System.currentTimeMillis();
                long results1 =endTime1 - startTime1;
                long results2=results+results1;
                double seconds = (double) results2/1000;
                System.out.println("Programın çalışma süresi"+seconds);
                
            }    
            if(k==3){
                long endTime = System.currentTimeMillis();
                System.out.println("Yolcu sayisini giriniz.");
                ys=sc.nextInt();
                long results = endTime - startTime;
                long startTime1=System.currentTimeMillis();
                bir.dj(baslangic, vertex_ll, ys, bitis);
                bir.en_kisa_yol_bul(baslangic, bitis, vertex_ll);
                long endTime1 = System.currentTimeMillis();
                long results1 =endTime1 - startTime1;
                long results2=results+results1;
                double seconds = (double) results2/1000;
                System.out.println("Programın çalışma süresi"+seconds);
            }
           // vertex_ll.get(78).vertex_yazdir();
           //vertex_ll.get(78).komsuluk_yazdir();
           //System.out.println("açı : "+Zeplin.egim_hesapla(44, 2, vertex_ll));
            
    }
}
