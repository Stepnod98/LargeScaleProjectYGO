/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Stefano
 */
public class DeckLayout {
    public static final int tileSize = 40;
    public static final int height = 5;
    public static final int width = 10;
    private static CardTile[][] cardBoard = new CardTile[width][height];
    private static Group tileGroup = new Group();
    private Pane root = new Pane();
    public DeckLayout(){
       root.setPrefSize(width * tileSize, height * tileSize);
       tileGroup.getChildren().clear();
       root.getChildren().addAll(tileGroup);
       //CardTile tile1 = new CardTile(0, 0, "https://vignette2.wikia.nocookie.net/yugioh/images/b/b3/FlamvellFiend-DREV-EN-C-1E.jpg", "Flamvell Fiend");
       //tileGroup.getChildren().add(tile1);
       //CardTile tile2 = new CardTile(0, 1, "https://vignette1.wikia.nocookie.net/yugioh/images/9/9f/Jigabyte-SDMP-EN-C-1E.png", "JigaByte");
       //tileGroup.getChildren().add(tile2);
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                /*if(y == 0 && x == 0){
                    continue;
                }
                if(y == 1 && x == 0){
                    continue;
                }*/
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
                CardTile tile = new CardTile(x, y);
                cardBoard[x][y] = tile;
                tileGroup.getChildren().add(tile);
            }
       }
    }
    public Parent getGameParent() {
        return root;
    }
    
    public static CardTile[][] getBoard(){
        return cardBoard;
    }
}
