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
    private String title;
    private String imageUrl;
    private String atkString;
    private String defString;
    private int atk;
    private int def;
    private List<String> types;
    private List<String> archetypes;

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

        if(d.get("types") != null){
            try {
                if(d.getInteger("atk") != null) {
                    this.atk = d.getInteger("atk");
                }
            } catch (ClassCastException exc) {
                if(d.getString("atk") != null){
                    this.atkString = d.getString("atk");
                }
            }
            try{
                if(d.getInteger("def") != null) {
                    this.def = d.getInteger("def");
                }
            } catch (ClassCastException exc) {
                if(d.getString("def") != null){
                    this.defString = d.getString("def");
                }
            }
            List<String> documentList = d.get("types", List.class);
            this.types = new ArrayList<>();
            types.addAll(documentList);
        }
        if(d.get("archetypes") != null){
            List<String> documentList = d.get("archetypes", List.class);
            this.archetypes = new ArrayList<>();
            archetypes.addAll(documentList);
        }
    }


    public String getTitle(){
        return title;
    }
    public String getImgURL(){
        return imageUrl;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public List<String> getTypes() {
        return types;
    }

    public Document toDocument(){

        if(types != null) {

            Document doc;

            if(atkString == null && defString == null){
                doc = new Document("title", this.title)
                        .append("types", this.types)
                        .append("atk", this.atk)
                        .append("def", this.def)
                        .append("imageUrl", this.imageUrl);
            }else if (atkString != null && defString == null){
                doc = new Document("title", this.title)
                        .append("types", this.types)
                        .append("atk", this.atkString)
                        .append("def", this.def)
                        .append("imageUrl", this.imageUrl);
            } else if (atkString == null && defString != null){
                doc = new Document("title", this.title)
                        .append("types", this.types)
                        .append("atk", this.atk)
                        .append("def", this.defString)
                        .append("imageUrl", this.imageUrl);
            } else{
                doc = new Document("title", this.title)
                        .append("types", this.types)
                        .append("atk", this.atkString)
                        .append("def", this.defString)
                        .append("imageUrl", this.imageUrl);
            }
            if(archetypes != null)
                doc.append("archetypes", this.archetypes);
            return doc;
        }else{

            Document doc = new Document("title", this.title)
                    .append("imageUrl", this.imageUrl);
            if(archetypes != null)
                doc.append("archetypes", this.archetypes);

            return doc;
        }
    }

}
