package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import work_with_DB.ConnectorDB;

public class FindContractController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField inputId;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private ListView<String> listInformation;

    private int derivative_id;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivatives.setItems(list);
        buttonActivate.setOnAction(event -> findContract());
    }

    @FXML
    void clickMouseDerivative(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null)
            derivative_id = Integer.parseInt(action);
    }

    private void findContract(){
        try {
            int id = Integer.parseInt(inputId.getText());
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<String> arrayList = ConnectorDB.showTable(String.format(
                    "SELECT * FROM Obligations WHERE contract_id = %d AND derivative_id = %d", id, derivative_id), 1);
            list.addAll(arrayList);
            listInformation.setItems(list);
            Logger.getGlobal().info("contract successfully found");
        }catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}

