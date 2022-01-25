/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import java.util.ArrayList;

import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


public class DeckLayout {
    public static final int tileSize = 40;
    public static final int height = 10;
    public static final int width = 5;
    private CardTile[][] cardBoard = new CardTile[width][height];
    private static Group tileGroup = new Group();
    private Pane root = new Pane();
    public DeckLayout(){
       root.setPrefSize(width * tileSize, height * tileSize);
       tileGroup.getChildren().clear();
       root.getChildren().addAll(tileGroup);     
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                CardTile tile = new CardTile(x, y);
                cardBoard[x][y] = tile;
                tileGroup.getChildren().add(tile);
            }
       }
    }
    public DeckLayout(Deck d){
       root.setPrefSize(width * tileSize, height * tileSize);
       tileGroup.getChildren().clear();
       root.getChildren().addAll(tileGroup); 
       int cont = 0;
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //controlla quando arrivi a 40, le ultime 10 sono extra cards!
                CardTile tile;
                if (cont < 40) {
                    if ((d.getCards().size() > cont)) {
                        tile = new CardTile(x, y, d.getCards().get(cont).getImgURL(), d.getCards().get(cont).getTitle());
                    } else {
                        tile = new CardTile(x, y);
                    }
                }else {
                    if (d.getECards().size() > (cont-40)) {
                        tile = new CardTile(x, y, d.getECards().get(cont-40).getImgURL(), d.getECards().get(cont-40).getTitle());
                    } else {
                        tile = new CardTile(x, y);
                    }
                }
                cardBoard[x][y] = tile;
                tileGroup.getChildren().add(tile);
                cont++;

            }

       }
    }
    public Parent getGameParent() {
        return root;
    }
    
    public CardTile[][] getBoard(){
        return cardBoard;
    }

}
