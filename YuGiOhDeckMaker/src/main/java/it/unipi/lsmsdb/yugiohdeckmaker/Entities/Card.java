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
    private String wikiUrl;
    private String imgLocation;
    private Image image;
    private String lore;
    private List<String> archetypesRelated = new ArrayList<String>();
    private List<String> archetypes = new ArrayList<String>();
    private List<String> actions = new ArrayList<String>();
    private List<String> effectTypes = new ArrayList<String>();
    private String imageUrl;
    private List<String> tips = new ArrayList<String>();
    private List<Set> sets = new ArrayList<Set>();
    private List<String> types = new ArrayList<String>();
    private String level;
    private int atk;
    private int def;

    public Card(){

    }

    // TODO: 05/01/2022
    //if we want to create a card based on the fields present in the deck information,
    //we need to change the constructor! Cause most of the information are not present and
    //it's impossible to fetch them
    //This is the one that we can use to fetch all the information present in the deck

    /*public Card(Document d){
        this.title = d.getString("title");
        List<Document> archetypes = d.get("archetypes",List.class);
        for (Document document : archetypes) {
            this.archetypes.add(document.toString());
        }
        this.imageUrl = d.getString("imageUrl");
        List<Document> types = d.get("types",List.class);
        for (Document document : types) {
            this.types.add(document.toString());
        }
        this.atk = d.getInteger("atk");
        this.def = d.getInteger("def");
    }*/


    //This directly from mongo
    public Card(Document d){
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
    }

    public String getTitle(){
        return title;
    }
    public String getImgURL(){
        return imageUrl;
    }
}
