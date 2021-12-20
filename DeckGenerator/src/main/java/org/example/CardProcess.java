package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class CardProcess {

    public static void main(String[] args){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> cardsCol = database.getCollection("cards");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("id","atk","def"));

        List<Document> cards = cardsCol.find(Filters.exists("atk")).projection(projectionFields).into(new ArrayList<Document>());


        int id;
        for(int i = 4244; i < cards.size(); i++) {
            id = cards.get(i).getInteger("id");
            System.out.println(cards.get(i).toString());
                if(cards.get(i).getString("atk")!= null && !cards.get(i).getString("atk").equals("?") && !cards.get(i).getString("atk").equals("X000")){
                    cardsCol.updateOne(eq("id", id), set("atk", Integer.parseInt(cards.get(i).getString("atk"))));
                }
                if(cards.get(i).getString("def")!= null && !cards.get(i).getString("def").equals("?") && !cards.get(i).getString("atk").equals("X000")){
                    cardsCol.updateOne(eq("id", id), set("def", Integer.parseInt(cards.get(i).getString("def"))));
                }
                System.out.println(Integer.toString(id) + " updated!");
        }

        System.out.println("Update completed!");

        /*int d = 0;
        while(cards.get(d).getInteger("id") != 252){
            d++;
        }
        //System.out.println(cards.get(d).toString());
        System.out.println(d);*/
    }

}
