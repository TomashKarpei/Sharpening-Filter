import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;

public class filtr_min {

 BufferedImage image;
 int width;
 int height;

 public filtr_min() {

 try {
 //odczyt obrazu z pliku

 File input = new File("Ratusz_Bia³ystok_rok_2013.jpg");

 image = ImageIO.read(input);
 width = image.getWidth();
 height = image.getHeight();
 int p = 0;
 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach

 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++){
	 int[] pomoc_r = new int[] {0,0,0,0,0,0,0,0,0};
	 int[] pomoc_g = new int[] {0,0,0,0,0,0,0,0,0};
	 int[] pomoc_b = new int[] {0,0,0,0,0,0,0,0,0};
	 int g = 0;
	 
   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {
    	 
       //odczyt sk³adowych koloru RGB

        Color c = new Color(image.getRGB(j+k, i+l));

        int red = (int)(c.getRed());
        int green = (int)(c.getGreen());
        int blue = (int)(c.getBlue());

       pomoc_r[g] = red;
       pomoc_g[g] = green;
       pomoc_b[g] = blue;
       g++;
   }
   }
   //filtrowanie od najmiejszej do najwiêkszej wartoœci kolorów  
   Arrays.sort(pomoc_g);
   Arrays.sort(pomoc_b);

  //System.out.println("\n" + pomoc_r[p] +" "+ pomoc_g[p] + " " + pomoc_b[p]);


 if (pomoc_r[p] >=0 && pomoc_r[p] <=255) {}
 else pomoc_r[p] = 0;

 if (pomoc_g[p] >=0 && pomoc_g[p] <=255) {}
 else pomoc_g[p] = 0;

 if (pomoc_b[p] >=0 && pomoc_b[p] <=255) {}
 else pomoc_b[p] = 0;

   

 Color newColor = new Color(pomoc_r[p], pomoc_g[p], pomoc_b[p]);
 image.setRGB(j,i,newColor.getRGB());



 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
 }

 //zapis do pliku zmodyfikowanego obrazu

 File ouptut = new File("filtr_min.jpg");
 ImageIO.write(image, "jpg", ouptut);

 } catch (Exception e) {}
 }



 static public void main(String args[]) throws Exception
 {
 filtr_min obj = new filtr_min();
 }
}