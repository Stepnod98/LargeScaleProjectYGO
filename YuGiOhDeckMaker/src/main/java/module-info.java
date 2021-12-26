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

    opens it.unipi.lsmsdb.yugiohdeckmaker to javafx.fxml;
    exports it.unipi.lsmsdb.yugiohdeckmaker;
}