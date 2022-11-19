package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import work_with_DB.ConnectorDB;

public class CalculateCostController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listDerivative;

    @FXML
    private TextField textOutput;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivative.setItems(list);
    }

    @FXML
    void clickMouse(MouseEvent event) {
        try {
            String action = listDerivative.getSelectionModel().getSelectedItem();
            if (action != null) {
                int der_id = Integer.parseInt(action);
                double res = ConnectorDB.getValueDouble("SELECT SUM(payment_amount) AS sumCost FROM Obligations "
                        + "WHERE derivative_id = " + der_id, "sumCost");
                textOutput.setText(res + "");
                Logger.getGlobal().info("The value of the derivative has been successfully calculated.");
            }
        }catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
