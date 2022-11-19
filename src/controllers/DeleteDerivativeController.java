package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import derivatives.Derivative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import work_with_DB.ConnectorDB;

public class DeleteDerivativeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonActivate;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private TextArea outInfo;

    private int derivative_id ;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        list.addAll(arrayList);
        listDerivatives.setItems(list);
        buttonActivate.setOnAction(event -> deleteDerivative());
    }

    @FXML
    void clickMouseDerivative(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null)
            derivative_id = Integer.parseInt(action);
    }

    private void deleteDerivative(){
        try {
            ConnectorDB.updateTable(String.format("DELETE FROM Derivatives WHERE ID = %d", derivative_id));
            outInfo.setText("the derivative " + derivative_id + " successful delete!");
            Logger.getGlobal().info("derivative successfully deleted.");
        } catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}

