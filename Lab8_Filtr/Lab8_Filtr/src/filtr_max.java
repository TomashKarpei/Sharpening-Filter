import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;

public class filtr_max {

 BufferedImage image;
 int width;
 int height;

 public filtr_max() {

 try {
 //odczyt obrazu z pliku

 File input = new File("Ratusz_Bia³ystok_rok_2013.jpg");

 image = ImageIO.read(input);
 width = image.getWidth();
 height = image.getHeight();
 int rmax, gmax, bmax;
 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach
 int[][] r = new int[width][height];
 int[][] g = new int[width][height];
 int[][] b = new int[width][height];
 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++) {
	 rmax = 0;
	 gmax = 0;
	 bmax = 0;
	 
   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {
    	 
       //odczyt sk³adowych koloru RGB

        Color c = new Color(image.getRGB(j+k, i+l));

        int red = (int)(c.getRed());
        int green = (int)(c.getGreen());
        int blue = (int)(c.getBlue());

        if (rmax < red) rmax = red;
        if (gmax < green) gmax = green;
        if (bmax < blue) bmax = blue;
   }
   }
   
 

   if (rmax>255) {
   rmax=255;
   }else if (rmax < 0){
   rmax=0;
   }
   if (gmax>255) {
   gmax=255;
   }else if (gmax < 0){
   gmax=0;
   }
   if (bmax>255) {
   bmax=255;
   }else if (bmax < 0){
   bmax=0;
   }
   
   r[j][i] = rmax;
   g[j][i] = gmax;
   b[j][i] = bmax;

 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
 }
 
 for(int q = 0; q < height; q++){
	   for(int w = 0; w < width; w++){
	   Color newColor = new Color(r[w][q], g[w][q], b[w][q]);
	   image.setRGB(w,q,newColor.getRGB());
	   }
	   }


 //zapis do pliku zmodyfikowanego obrazu

 File ouptut = new File("filtr_max.jpg");
 ImageIO.write(image, "jpg", ouptut);

 } catch (Exception e) {}
 }



 static public void main(String args[]) throws Exception
 {
 filtr_max obj = new filtr_max();
 }
}