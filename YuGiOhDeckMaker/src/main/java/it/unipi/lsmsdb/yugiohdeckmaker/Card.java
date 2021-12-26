/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import java.util.*;
import javafx.scene.image.*;

/**
 *
 * @author Stefano
 */
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
    public String getTitle(){
        return title;
    }
}
