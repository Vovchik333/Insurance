package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import work_with_DB.ConnectorDB;

public class ShowContractsSpecificTypeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private ListView<String> listInformation;

    @FXML
    private ListView<String> listTypes;

    private int derivative_id;
    private String type;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list_types = FXCollections.observableArrayList();
        String[] types = {"Liability insurance", "Personal insurance", "Property insurance"};
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivatives.setItems(list);
        list_types.addAll(types);
        listTypes.setItems(list_types);
        buttonActivate.setOnAction(event -> ShowContractsSpecificType());
    }

    @FXML
    void clickMouseDerivative(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null)
            derivative_id = Integer.parseInt(action);
    }

    @FXML
    void clickMouseType(MouseEvent event) {
        type = listTypes.getSelectionModel().getSelectedItem();
    }

    private void ShowContractsSpecificType(){
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showTable(String.format("SELECT * FROM Obligations WHERE type_contract = '%s' AND " +
                       "derivative_id = %d", type, derivative_id), 1);
        list.addAll(arrayList);
        listInformation.setItems(list);
        Logger.getGlobal().info("contracts of the specific type are successfully displayed");
    }

}

