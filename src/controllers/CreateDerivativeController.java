package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import derivatives.Derivative;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import work_with_DB.ConnectorDB;

public class CreateDerivativeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane FindContractInRange_scene;

    @FXML
    private Button buttonActive;

    @FXML
    private TextField outputId;

    @FXML
    private TextArea outputInfo;

    @FXML
    void initialize() {
        int id_for_derivative = ConnectorDB.getValueInt(
                "SELECT MAX(ID) AS maxId FROM Derivatives","maxId") + 1;
        outputId.setText(id_for_derivative + "");
        buttonActive.setOnAction(event -> createDerivative(id_for_derivative));
    }

    private void createDerivative(int id){
        (new Derivative(id, 0)).insertDerivative();
        outputInfo.setText("the derivative " + id + " successful create!");
        Logger.getGlobal().info("The derivative has been created successfully.");
    }
}

