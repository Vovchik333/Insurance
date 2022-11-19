package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import obligations.Contract;
import work_with_DB.ConnectorDB;

public class UpdateContractController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputValue;

    @FXML
    private ListView<String> listTypes;

    @FXML
    private TextArea outputText;

    private String type;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(Contract.getColumnsInsure());
        listTypes.setItems(list);
        buttonActivate.setOnAction(event -> updateContract());
    }

    @FXML
    void clickMouseType(MouseEvent event) {
        type = listTypes.getSelectionModel().getSelectedItem();
    }

    private void updateContract(){
        try {
            int id = Integer.parseInt(inputId.getText());
            String value = inputValue.getText();
            String[] columns = {"name_org", "address_org", "surname_client", "first_name_client",
                    "mid_name_client", "address_client", "date_start", "currency", "insure_event",
                    "insure_obj", "type_contract"};
            for (String str : columns) {
                if (type.equals(str)) {
                    value = "'" + value + "'";
                    break;
                }
            }
            ConnectorDB.updateTable(String.format("UPDATE Obligations SET %s = %s WHERE contract_id = %d",
                    type, value, id));
            outputText.setText("the " + type + " successful update!");
        }catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            Logger.getGlobal().info("contract is successfully updated.");
        }
    }
}


