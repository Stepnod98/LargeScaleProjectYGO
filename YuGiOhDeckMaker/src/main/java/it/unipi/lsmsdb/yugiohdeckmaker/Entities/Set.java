/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Entities;

public class Set {
    private String number;
    private String setName;
    private String rarity;
    public Set(String number, String setName, String rarity){
        this.number = number;
        this.setName = setName;
        this.rarity = rarity;
    }
    public String getNumber(){
        return number;
    }
    public String getSetName(){
        return setName;
    }
    public String getRarity(){
        return rarity;
    }
}
