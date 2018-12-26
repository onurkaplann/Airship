package Zeplin;
import java.util.*;
import java.io.*;
public class deoes5 {
    public double[] mesafeler;
    public int[] guzergah;
    LinkedList<Vertex> enKisaYol = new LinkedList();
    static int glztrick;
    
    public deoes5(){
        glztrick=1;
        guzergah = new int[82];
        }
    
    public void dj (int baslangic,LinkedList<Vertex> vertex_ll,int ys ,int bitis){
        double derece=0;
        mesafeler = new double[82];
        
        double[] gidilmisler = new double[82];
        int gidilemeyen_sehir_sayisi=0;
        int konum=0;
        int geldigim_yer=baslangic;
        double temp;    // mesafe_hesapla'dan gelen degeri tutmak icin
        double tut=10479;
        int sayac=0;
        guzergah[bitis]=99;
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        for (int i = 0; i < mesafeler.length; i++) {    //Mesafeler dizisini buyuk bir degerle dolduruyor , gidilmisler ' i sifirliyor.
            mesafeler[i]=10479;
            gidilmisler[i]=0;
         }
       
        guzergah[baslangic]=baslangic;                              //Baslangicin from u null olmasin diye
        mesafeler[baslangic]=1.1101110;
        konum=baslangic;
        
        for(int i = 1;i<vertex_ll.get(konum).komsu_sayisi;i++){                                 //Komsularin mesafesini gunceller
            temp=Zeplin.mesafe_hesapla(konum, vertex_ll.get(konum).komsular[i].plaka, vertex_ll); //mesafeyi temp ' e at
            
            
            derece=Zeplin.egim_hesapla(konum,vertex_ll.get(konum).komsular[i].plaka , vertex_ll);   // DERECE , ZEPLIN KABILIYETI KONTROLU
            
            if(derece>80-ys){
            temp=10479;
            //guzergah[vertex_ll.get(konum).komsular[i].plaka]=baslangic;         
            }
            
            mesafeler[vertex_ll.get(konum).komsular[i].plaka ]=temp;       //Mesafeler[] i guncelliyor
            gidilmisler[konum]=1;                                        //A yi gidilmisler e atıyor
                                                   //Konum guncelleme
            } 
        
            for(int i=1;i<82;i++){
                if( tut>mesafeler[i] && gidilmisler[i]==0 ) {
                tut=mesafeler[i];
                konum=i;
                }
                
                guzergah[konum]=baslangic;

        }
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
      
        while (true) {
            
            sayac=0;
            tut=10479;
            
            for (int i = 1; i < vertex_ll.get(konum).komsu_sayisi; i++) { //Komsularin mesafesini guncelleme
                temp=( Zeplin.mesafe_hesapla(konum, vertex_ll.get(konum).komsular[i].plaka, vertex_ll) ) + (mesafeler[konum]);   //mesafeyi temp ' e at (+)
                
                derece=Zeplin.egim_hesapla(konum,vertex_ll.get(konum).komsular[i].plaka , vertex_ll);       // DERECE , ZEPLIN KABILIYETI KONTROLU
                if(derece>80-ys){
                    temp=10479;
                }
                
                if(temp<mesafeler[vertex_ll.get(konum).komsular[i].plaka] ){   //Mesafeler[] i guncelliyor
                mesafeler[vertex_ll.get(konum).komsular[i].plaka]=temp;
                guzergah[vertex_ll.get(konum).komsular[i].plaka]=konum;   //Guzerganlari guncelliyor

                }
                gidilmisler[konum]=1;                                       //konum u gidilmisler e atıyor
                            
              }
            geldigim_yer=konum;                                         //Konum guncelleme
            for(int i=1;i<82;i++){
                if( tut>mesafeler[i] && gidilmisler[i]==0 ) {
                tut=mesafeler[i];
                konum=i;
                }
            }
            
            if(konum==geldigim_yer ){
                glztrick=0;
                break;
            }
            
            for(int i=0;i<82;i++){
                if( gidilmisler[i]==1){
                sayac++;            
                }
            }
            
            if(sayac==81){
            break;
            }
        }
        
        if(guzergah[bitis]!=99){
        glztrick=1;
       }
    } 
       
    public void mesafeler_yazdir(){     
            
        for (int i = 1; i < mesafeler.length; i++) {
            System.out.println( "Mesafeler["+i+"]   :"+this.mesafeler[i] );
        }
     }
    
    public void guzergah_yazdir(){
    for(int i=1;i<82;i++)
            System.out.println("gzrgh["+i+"]"+guzergah[i] );  
    } // Baslangıçtan bütün şehirlere giden en kısa yolu bulur,her şehirin bir önceki adımını yazar.
  
    public void en_kisa_yol_bul(int baslangic,int bitis,LinkedList<Vertex> vertex_ll ) throws IOException{
        int comp;
            
        if(glztrick==1){
            enKisaYol.add(vertex_ll.get(bitis));
            comp=guzergah[bitis];
            
            while (true) {
                
                    enKisaYol.addLast(vertex_ll.get(comp));
                    
               if(comp==baslangic || comp==guzergah[baslangic] ||comp==0){
                   
                   break;
                   
               }
               comp=guzergah[comp];
            }
            
            for(int i=0;i<enKisaYol.size()-1;i++){
                System.out.print(enKisaYol.get(i).id+" -->");
            }
            System.out.print(vertex_ll.get(baslangic).id);
            System.out.println();
        }else{
         System.out.println("Gidlemiyor...");
        }
        this.dosyaya_yaz(vertex_ll,vertex_ll.get(baslangic).plaka);
            enKisaYol.removeAll(vertex_ll);
    }
    
    public double[] mesafeler_dondur(){
       return mesafeler;
   }
    
   public void dosyaya_yaz(LinkedList<Vertex> vertex_ll,int baslangic) throws IOException{
       
    File file = new File("dosya.txt");
    if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        
        for(int i=1;i<enKisaYol.size()-1;i++){
            bWriter.write("plaka : "+enKisaYol.get(i-1).plaka+" lat: "+enKisaYol.get(i-1).x+" long: "+enKisaYol.get(i-1).y);
            bWriter.write(" "+Zeplin.mesafe_hesapla(enKisaYol.get(i-1).plaka,enKisaYol.get(i).plaka,vertex_ll )+" ");
            bWriter.write("plaka : "+enKisaYol.get(i).plaka  +" lat: "+enKisaYol.get(i).x+" long: "+enKisaYol.get(i).y );
        bWriter.newLine();
        }
        bWriter.write("plaka : "+enKisaYol.get(enKisaYol.size()-2).plaka+" lat: "+enKisaYol.get(enKisaYol.size()-2).x+" long: "+enKisaYol.get(enKisaYol.size()-2).y);
        bWriter.write(" "+Zeplin.mesafe_hesapla(enKisaYol.get(enKisaYol.size()-2).plaka ,baslangic , vertex_ll ) );
        bWriter.write("plaka : "+baslangic+" lat: "+vertex_ll.get(baslangic).x+" long: "+vertex_ll.get(baslangic).y );
        
        bWriter.close();
   }
}
