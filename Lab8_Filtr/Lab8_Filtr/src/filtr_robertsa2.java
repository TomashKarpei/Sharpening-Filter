import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class filtr_robertsa2 {

 BufferedImage image;
 int width;
 int height;

 public filtr_robertsa2() {

 try {
 //odczyt obrazu z pliku

 File input = new File("Ratusz_Bia�ystok_rok_2013.jpg");

 image = ImageIO.read(input);
 width = image.getWidth();
 height = image.getHeight();

 int[][] M = new int[][] { {0,0,0}, {0,1,0}, {0,-1,0}};

 int pomoc_r, pomoc_g, pomoc_b;

 //odczyt pixeli obrazu w dw�ch p�tlach po kolumnach i wierszach

 for(int i=1; i<height-1; i++){
 for(int j=1; j<width-1; j++){
   
   pomoc_r = 0;
   pomoc_g = 0;
   pomoc_b = 0;


   for (int k=-1;k<=1;k++) {
     for (int l=-1; l<=1;l++) {

       //odczyt sk�adowych koloru RGB

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


 if (pomoc_r>=0 && pomoc_r <=255) {}
 else pomoc_r = 0;

 if (pomoc_g>=0 && pomoc_g <=255) {}
 else pomoc_g = 0;

 if (pomoc_b>=0 && pomoc_b <=255) {}
 else pomoc_b = 0;

   

 Color newColor = new Color(pomoc_r, pomoc_g, pomoc_b );
 image.setRGB(j,i,newColor.getRGB());



 } //koniec dw�ch p�tli po kolumnach i wierszach obrazu
 }

 //zapis do pliku zmodyfikowanego obrazu

 File ouptut = new File("filtr_robertsa2.jpg");
 ImageIO.write(image, "jpg", ouptut);

 } catch (Exception e) {}
 }



 static public void main(String args[]) throws Exception
 {
 filtr_robertsa2 obj = new filtr_robertsa2();
 }
}