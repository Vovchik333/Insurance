package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import work_with_DB.ConnectorDB;

public class ShowContractsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private ListView<String> listInformation;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivatives.setItems(list);
    }

    @FXML
    void clickMouse(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null){
            ObservableList<String> list = FXCollections.observableArrayList();
            int der_id = Integer.parseInt(action);
            ArrayList<String> arrayList = ConnectorDB.showTable("SELECT * FROM Obligations WHERE derivative_id = " + der_id, 1);
            list.addAll(arrayList);
            listInformation.setItems(list);
            Logger.getGlobal().info("contracts are successfully displayed");
        }
    }

}

