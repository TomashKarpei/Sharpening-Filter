import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;

public class filtr_med {

 BufferedImage image;
 int width;
 int height;

 public filtr_med() {

 try {
 //odczyt obrazu z pliku

 File input = new File("Ratusz_Bia³ystok_rok_2013.jpg");

 image = ImageIO.read(input);
 width = image.getWidth();
 height = image.getHeight();
 int p = 4;
 
 int[][] r = new int[width][height];
 int[][] g = new int[width][height];
 int[][] b = new int[width][height];
 int[] pomoc_r = new int[] {0,0,0,0,0,0,0,0,0};
 int[] pomoc_g = new int[] {0,0,0,0,0,0,0,0,0};
 int[] pomoc_b = new int[] {0,0,0,0,0,0,0,0,0};
 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach

 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++){


	 
   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {
    	 
       //odczyt sk³adowych koloru RGB

        Color c = new Color(image.getRGB(j+k, i+l));

        int red = (int)(c.getRed());
        int green = (int)(c.getGreen());
        int blue = (int)(c.getBlue());

       pomoc_r[k+l+2] = red;
       pomoc_g[k+l+2] = green;
       pomoc_b[k+l+2] = blue;
   }
   }
   //filtrowanie od najmiejszej do najwiêkszej wartoœci kolorów  
   Arrays.sort(pomoc_r);
   Arrays.sort(pomoc_g);
   Arrays.sort(pomoc_b);
   

  //System.out.println("\n" + pomoc_r[p] +" "+ pomoc_g[p] + " " + pomoc_b[p]);


   if (pomoc_r[p] > 255) {
	   pomoc_r[p] = 255;
   }else if (pomoc_r[p] < 0){
	   pomoc_r[p] = 0;
   }
   if (pomoc_g[p] > 255) {
	   pomoc_g[p] = 255;
   }else if (pomoc_g[p] < 0){
	   pomoc_g[p] = 0;
   }
   if (pomoc_b[p] > 255) {
	   pomoc_b[p] = 255;
   }else if (pomoc_b[p] < 0){
	   pomoc_b[p] = 0;
   }

   r[j][i] = pomoc_r[p];
   g[j][i] = pomoc_g[p];
   b[j][i] = pomoc_b[p];
  
 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
 }
 for(int q = 0; q < height; q++){
	   for(int w = 0; w < width; w++){
	   Color newColor = new Color(r[w][q], g[w][q], b[w][q]);
	   image.setRGB(w,q,newColor.getRGB());
	   }
	   }
 //zapis do pliku zmodyfikowanego obrazu

 File ouptut = new File("filtr_med.jpg");
 ImageIO.write(image, "jpg", ouptut);

 } catch (Exception e) {}
 }



 static public void main(String args[]) throws Exception
 {
 filtr_med obj = new filtr_med();
 }
}