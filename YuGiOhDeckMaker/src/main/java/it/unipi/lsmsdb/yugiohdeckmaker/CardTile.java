/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 *
 * @author Stefano
 */
public class CardTile extends StackPane {
    private static Image image = null;
    private static String imgLocation = "file:./../img/backCard.png";;
    public CardTile(int x, int y){
        relocate(x * 1.0* DeckLayout.tileSize, y * 1.1* DeckLayout.tileSize);
        ImageView cardImg = new ImageView(imgLocation);

        double decrementation = 1.5;

        cardImg.setFitWidth(DeckLayout.tileSize);
        cardImg.setFitHeight(DeckLayout.tileSize);

        cardImg.setTranslateX(decrementation/2);
        cardImg.setTranslateY(decrementation/2);

        getChildren().addAll(cardImg);     
    }
    public void setImage(String newImg){
        imgLocation = newImg;
    }
   /* public static void saveImage(String imageUrl, String destinationFile){
       BufferedImage image = null;  
       try {
              URL url = new URL(imageUrl);
              image = ImageIO.read(url);
              File file = new File("C:\\firefox.jpg"); // just an object
              //FileWriter fw = new FileWriter(file); // create an actual file
              ImageIO.write(image,"jpg", file);
              //If successful, process the message
          } catch (IOException e) {
              System.out.println("Unable to retrieve Image!!!");
              e.printStackTrace();
          }
      }*/
       /*URL url = new URL(imageUrl);
       InputStream is = url.openStream();
       OutputStream os = new FileOutputStream(destinationFile);

       byte[] b = new byte[2048];
       int length;

       while ((length = is.read(b)) != -1) {
           os.write(b, 0, length);
       }
       is.close();
       os.close();*/
       /*
       Image image = null;
        URL url = new URL("http://bks6.books.google.ca/books?id=5VTBuvfZDyoC&printsec=frontcover&img=1& zoom=5&edge=curl&source=gbs_api");
        image = ImageIO.read(url);
        jXImageView1.setImage(image);
       */
}
