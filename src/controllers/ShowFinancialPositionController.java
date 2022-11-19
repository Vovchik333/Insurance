package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import work_with_DB.ConnectorDB;

public class ShowFinancialPositionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listInformation;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showTable("SELECT * FROM FinancialPosition", 2);
        list.addAll(arrayList);
        listInformation.setItems(list);
        Logger.getGlobal().info("financial position are successfully displayed.");
    }

}

