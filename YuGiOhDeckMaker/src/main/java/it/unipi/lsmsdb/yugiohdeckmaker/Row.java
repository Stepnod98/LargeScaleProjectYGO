package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Row {
    private final SimpleStringProperty info;
    private Button button;

    Row(String info) {
        this.info = new SimpleStringProperty(info);
        this.button = new Button();
    }

    public String getInfo(){
        return info.get();
    }

    public void setInfo(String name){
        info.set(name);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button){
        this.button = button;
    }

}
