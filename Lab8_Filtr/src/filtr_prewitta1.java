import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class filtr_prewitta1 {

 BufferedImage image;
 int width;
 int height;

 public filtr_prewitta1() {

 try {
 //odczyt obrazu z pliku

 File input = new File("Ratusz_Bia³ystok_rok_2013.jpg");

 image = ImageIO.read(input);
 width = image.getWidth();
 height = image.getHeight();

 int[][] M = new int[][] { {1,1,1}, {0,0,0}, {-1,-1,-1}};
 int[][] r = new int[width][height];
 int[][] g = new int[width][height];
 int[][] b = new int[width][height];

 int pomoc_r, pomoc_g, pomoc_b;

 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach

 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++){
   
   pomoc_r = 0;
   pomoc_g = 0;
   pomoc_b = 0;


   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {

       //odczyt sk³adowych koloru RGB

        Color c = new Color(image.getRGB(j+k, i+l));

        int red = (int)(c.getRed());
        int green = (int)(c.getGreen());
        int blue = (int)(c.getBlue());

       pomoc_r += red  * M[k+1][l+1];
       pomoc_g += green * M[k+1][l+1];
       pomoc_b += blue * M[k+1][l+1];

   }
   }

   pomoc_r = (int) Math.abs(pomoc_r);
   pomoc_g = (int) Math.abs(pomoc_g);
   pomoc_b = (int) Math.abs(pomoc_b);


 //System.out.println("\n" + pomoc_r +" "+ pomoc_g + " " + pomoc_b );


 if (pomoc_r > 255) {pomoc_r = 255;}
 else if(pomoc_r < 0) pomoc_r = 0;

 if (pomoc_g > 255) {pomoc_g = 255;}
 else if (pomoc_g < 0) pomoc_g = 0;

 if (pomoc_b > 255) {pomoc_b = 255;}
 else if (pomoc_b < 0) pomoc_b = 0;
 
 r[j][i] = pomoc_r;
 g[j][i] = pomoc_g;
 b[j][i] = pomoc_b;
  
 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
 }
 
 for(int q = 0; q < height; q++){
	   for(int w = 0; w < width; w++){
	   Color newColor = new Color(r[w][q], g[w][q], b[w][q]);
	   image.setRGB(w,q,newColor.getRGB());
	   }
	   }

 //zapis do pliku zmodyfikowanego obrazu

 File ouptut = new File("filtr_prewitta1.jpg");
 ImageIO.write(image, "jpg", ouptut);

 } catch (Exception e) {}
 }



 static public void main(String args[]) throws Exception
 {
 filtr_prewitta1 obj = new filtr_prewitta1();
 }
}