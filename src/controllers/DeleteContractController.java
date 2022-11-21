package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import derivatives.Derivative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import work_with_DB.ConnectorDB;

public class DeleteContractController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField inputId;

    @FXML
    private ListView<String> listReasons;

    @FXML
    private TextArea outputText;

    private String type;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] types = {"expired", "An insurance event occurred"};
        list.addAll(types);
        listReasons.setItems(list);
        buttonActivate.setOnAction(event -> deleteContract());
    }

    @FXML
    void clickMouse(MouseEvent event) {
        type = listReasons.getSelectionModel().getSelectedItem();
    }

    private void deleteContract(){
        try {
            int id = Integer.parseInt(inputId.getText()), choice;
            if (type.equals("expired"))
                choice = 1;
            else
                choice = 2;
        double reserve = ConnectorDB.getValueDouble("SELECT Insure_Reserve FROM FinancialPosition " +
                "ORDER BY ID DESC LIMIT 1", "Insure_Reserve");
        double x = ConnectorDB.changeFinancePosition(id, choice);
        ConnectorDB.updateTable("INSERT INTO FinancialPosition(Insure_Reserve, ModifiedDate) " +
                    "VALUES (" + reserve + " + " + x + ", CURRENT_DATE())");
            Derivative.difContract(ConnectorDB.getValueInt(
                    "SELECT derivative_id FROM Obligations WHERE contract_id = " + id, "derivative_id"));
            ConnectorDB.updateTable(String.format("DELETE FROM Obligations WHERE contract_id = %d", id));
            outputText.setText("the contract " + id + " successful delete!");
            Logger.getGlobal().info("contract successfully deleted.");
        } catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}


