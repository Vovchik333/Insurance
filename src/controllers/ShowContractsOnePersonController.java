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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import work_with_DB.ConnectorDB;

public class ShowContractsOnePersonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputMidName;

    @FXML
    private TextField inputSurname;

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
        buttonActivate.setOnAction(event -> ShowContractsOnePerson());
    }

    @FXML
    void clickMouse(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null)
            derivative_id = Integer.parseInt(action);
    }

    private void ShowContractsOnePerson(){
        String surname = inputSurname.getText();
        String first_name = inputFirstName.getText();
        String mid_name = inputMidName.getText();
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showTable(String.format("SELECT * FROM Obligations WHERE surname_client = '%s' AND " +
                "first_name_client = '%s' AND mid_name_client = '%s' AND derivative_id = %d",
                surname, first_name, mid_name, derivative_id), 1);
        list.addAll(arrayList);
        listInformation.setItems(list);
        Logger.getGlobal().info("contracts of one person are successfully displayed");
    }

}

