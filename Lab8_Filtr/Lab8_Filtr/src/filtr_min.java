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
 int rmin, gmin, bmin;
 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach
 int[][] r = new int[width][height];
 int[][] g = new int[width][height];
 int[][] b = new int[width][height];
 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++) {
	 rmin = 255;
	 gmin = 255;
	 bmin = 255;
	 
   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {
    	 
       //odczyt sk³adowych koloru RGB

        Color c = new Color(image.getRGB(j+k, i+l));

        int red = (int)(c.getRed());
        int green = (int)(c.getGreen());
        int blue = (int)(c.getBlue());

        if (rmin > red) rmin = red;
        if (gmin > green) gmin = green;
        if (bmin > blue) bmin = blue;
   }
   }
   
 

   if (rmin>255) {
   rmin=255;
   }else if (rmin < 0){
   rmin=0;
   }
   if (gmin>255) {
   gmin=255;
   }else if (gmin < 0){
   gmin=0;
   }
   if (bmin>255) {
   bmin=255;
   }else if (bmin < 0){
   bmin=0;
   }
   
   r[j][i] = rmin;
   g[j][i] = gmin;
   b[j][i] = bmin;

 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
 }
 
 for(int q = 0; q < height; q++){
	   for(int w = 0; w < width; w++){
	   Color newColor = new Color(r[w][q], g[w][q], b[w][q]);
	   image.setRGB(w,q,newColor.getRGB());
	   }
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