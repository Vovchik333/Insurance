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

public class FindContractInRangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField endRange;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private ListView<String> listInformation;

    @FXML
    private ListView<String> listTypes;

    @FXML
    private TextField startRange;

    private int derivative_id;
    private String type;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list_types = FXCollections.observableArrayList();
        String[] columnsInsure = {"passport_id", "validity",
                "payment_amount", "contributions_client", "risk_lvl"};
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivatives.setItems(list);
        list_types.addAll(columnsInsure);
        listTypes.setItems(list_types);
        buttonActivate.setOnAction(event -> findContractInRange());
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

    private void findContractInRange(){
        try {
            double start = Double.parseDouble(startRange.getText());
            double end = Double.parseDouble(endRange.getText());
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<String> arrayList = ConnectorDB.showTable(String.format("SELECT * FROM Obligations WHERE " +
                    "%s > " + start + " AND %s < " + end + " AND derivative_id = %d", type, type, derivative_id), 1);
            list.addAll(arrayList);
            listInformation.setItems(list);
            Logger.getGlobal().log(Level.INFO,"contract successfully found in range [{0}; {1}]", new Object[] {start, end});
        }catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

