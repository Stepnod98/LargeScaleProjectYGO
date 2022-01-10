/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Entities;

import java.util.*;
import javafx.scene.image.*;
import org.bson.Document;


public class Card {
    private int id;
    private String title;
    private String imageUrl;

    public Card(){this.title = "";};

    public Card(String title){
        this.title = title;
    }

    public Card(String title, String imageUrl){
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Card(Document d){
        this.title = d.getString("title");
        this.imageUrl = d.getString("imageUrl");
    }


    //This directly from mongo
    /*public Card(Document d){
        this.id = d.getInteger("id");
        this.title = d.getString("title");
        this.wikiUrl = d.getString("wikiUrl");
        this.imgLocation = d.getString("image");
        this.lore = d.getString("lore");
        List<Document> archetypesRel = d.get("archetypesRelated",List.class);
        for (Document document : archetypesRel) {
            this.archetypesRelated.add(document.toString());
        }
        List<Document> archetypes = d.get("archetypes",List.class);
        for (Document document : archetypes) {
            this.archetypes.add(document.toString());
        }
        List<Document> actions = d.get("actions",List.class);
        for (Document document : actions) {
            this.actions.add(document.toString());
        }
        List<Document> effects = d.get("effectTypes",List.class);
        for (Document document : effects) {
            this.effectTypes.add(document.toString());
        }
        this.imageUrl = d.getString("imageUrl");
        List<Document> tips = d.get("tips",List.class);
        for (Document document : tips) {
            this.tips.add(document.getString("value"));
        }
        List<Document> sets = d.get("sets",List.class);
        for (Document document : sets) {
            this.sets.add(new Set(document.getString("number"),document.getString("setName"),document.getString("rarity")));
        }
        List<Document> types = d.get("types",List.class);
        for (Document document : types) {
            this.types.add(document.toString());
        }
        this.level = d.getString("level");
        this.atk = d.getInteger("atk");
        this.def = d.getInteger("def");
    }*/

    public String getTitle(){
        return title;
    }
    public String getImgURL(){
        return imageUrl;
    }

    public int getId() {
        return id;
    }
}
