package Zeplin;
import java.util.*;
import java.math.*;

public class Vertex {
    
    public String id;
    public double x,y,z;//koordinatlar
    public int komsu_sayisi;
    public int plaka;
    public Vertex komsular[];
    
     public Vertex(){}
    
     public Vertex(double y,double x,int plaka,double z,String id){
      this.plaka=plaka;
      this.id=id;
      this.x=x;
      this.y=y;
      this.z=z;
      this.komsu_sayisi=0;
      }
     
     public void vertex_yazdir(){
         System.out.println("id:"+this.id);
         System.out.println("plaka:"+this.plaka);
         System.out.println("x:"+this.x);
         System.out.println("y:"+this.y);
         System.out.println("z:"+this.z);
     }
   
     public void komsu_ekle(LinkedList<Vertex> vertex_ll, int komsuluklar[],int i) {
      
        this.komsu_sayisi=i;
        this.komsular = new Vertex[komsu_sayisi];
        
        for(i=1;i<komsu_sayisi;i++){
        this.komsular[i]=vertex_ll.get(komsuluklar[i]);
        }
      }
    
     public void komsuluk_yazdir(){
        for(int i=1;i<this.komsu_sayisi;i++){
            System.out.println("komsular:"+komsular[i].id);
        }
    }
}
