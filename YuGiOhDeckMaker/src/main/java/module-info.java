module it.unipi.lsmsdb.yugiohdeckmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires org.neo4j.driver;
    requires java.desktop;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.logging;
    requires javafx.swing;

    //opens it.unipi.lsmsdb.yugiohdeckmaker to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker.DBManagers;
    opens it.unipi.lsmsdb.yugiohdeckmaker.DBManagers to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;
    opens it.unipi.lsmsdb.yugiohdeckmaker.Exceptions to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker.Layouts;
    opens it.unipi.lsmsdb.yugiohdeckmaker.Layouts to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker.Controller;
    opens it.unipi.lsmsdb.yugiohdeckmaker.Controller to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker.Entities;
    opens it.unipi.lsmsdb.yugiohdeckmaker.Entities to javafx.fxml;
}