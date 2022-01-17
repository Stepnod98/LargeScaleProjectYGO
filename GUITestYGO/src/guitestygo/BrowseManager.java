/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Stefano
 */
public class BrowseManager {
    public static BorderPane viewList(TextField tf, List l){
        ObservableList<String> data = FXCollections.observableArrayList(l); //add the values of the list!!
        FilteredList<String> filteredData = new FilteredList<>(data, s -> false);
        //Set the filter Predicate whenever the filter changes.
        String filter = tf.getText(); 
        if(filter == null || filter.length() == 0) {
            filteredData.setPredicate(s -> false);
            return null;
        }
        else {
            filteredData.setPredicate(s -> s.contains(filter));
        }
        ListView<String> lv = new ListView<>(filteredData);
        for(int i = 0; i < filteredData.size(); i++){
            String current = filteredData.get(i);
            lv.getSelectionModel().getSelectedItem();
            lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    tf.setText(lv.selectionModelProperty().getValue().getSelectedItem());
                }
            });
        }
        BorderPane content = new BorderPane(lv);
        //content.setTop(tf);
        /*if(filteredData.isEmpty()){
            content.setMaxHeight(0);
        }*/
        return content;
    }
}
