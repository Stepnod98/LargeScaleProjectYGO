/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
    private String imgLocation = "file:./../img/backCard.png";
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
    public CardTile(int x, int y, String imgUrl){
        BufferedImage image;
        try {
            URL imageUrl = new URL(imgUrl);
            try {
                image = ImageIO.read(imageUrl);
                Image img = SwingFXUtils.toFXImage(image, null);
                ImageView cardImg = new ImageView();
                cardImg.setImage(img);
                cardImg.setFitWidth(DeckLayout.tileSize);
                cardImg.setFitHeight(DeckLayout.tileSize);
                relocate(x * 1.0* DeckLayout.tileSize, y * 1.1* DeckLayout.tileSize);
                double decrementation = 1.5;
                cardImg.setTranslateX(decrementation/2);
                cardImg.setTranslateY(decrementation/2);
                getChildren().addAll(cardImg);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image from URL: " + imageUrl, e);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(CardTile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setImage(String newImg){
        imgLocation = newImg;
    }
}
