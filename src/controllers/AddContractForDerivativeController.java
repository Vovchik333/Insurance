package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import derivatives.Derivative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import obligations.Contract;
import obligations.LiabilityInsurance;
import obligations.PersonalInsurance;
import obligations.PropertyInsurance;
import work_with_DB.ConnectorDB;

public class AddContractForDerivativeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address_client;

    @FXML
    private Button buttonActivate;

    @FXML
    private TextField first_name_client;

    @FXML
    private TextField insure_event;

    @FXML
    private TextField insure_object;

    @FXML
    private ListView<String> listDerivatives;

    @FXML
    private ListView<String> listTypes;

    @FXML
    private TextField mid_name_client;

    @FXML
    private TextArea outputText;

    @FXML
    private TextField password_id;

    @FXML
    private TextField payment_amount;

    @FXML
    private TextField risk_lvl;

    @FXML
    private TextField surname_client;

    @FXML
    private TextField validity;

    private String type;
    private int derivative_id;

    @FXML
    void initialize() {
        ObservableList<String> listDer = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT ID AS id FROM Derivatives");
        ObservableList<String> list = FXCollections.observableArrayList();
        listDer.addAll(arrayList);
        listDerivatives.setItems(listDer);
        String[] types = {"Liability insurance", "Personal insurance", "Property insurance"};
        list.addAll(types);
        listTypes.setItems(list);
        buttonActivate.setOnAction(event -> addContract());
    }

    @FXML
    void clickMouseType(MouseEvent event) {
        type = listTypes.getSelectionModel().getSelectedItem();
    }

    @FXML
    void clickMouseDerivative(MouseEvent event) {
        String action = listDerivatives.getSelectionModel().getSelectedItem();
        if(action != null)
            derivative_id = Integer.parseInt(action);
    }


    private void addContract(){
        try {
            int id_for_contract = ConnectorDB.getValueInt(
                "SELECT MAX(contract_id) AS maxId FROM Obligations","maxId") + 1;
            Contract.insertContract(createContract(id_for_contract));
            Derivative.addContract(derivative_id);
            outputText.setText("the contract " + id_for_contract + " successful add!");
            Logger.getGlobal().info("the contract has been successfully added.");
        }catch (Exception e){
            Logger.getGlobal().warning("no contract has been created.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText("no contract has been created");
            alert.showAndWait();
        }
    }

    public Contract createContract(int contract_id) {
        try {
            String type_contract = type;
            String name_organization = "ComfortInsure";
            String address_organization = "St. Kulparkivska, 200a, Lviv";
            String surnameClient = surname_client.getText();
            String firstName_client = first_name_client.getText();
            String midName_client = mid_name_client.getText();
            String addressClient = address_client.getText();
            int passportId = Integer.parseInt(password_id.getText());
            int validity_insure = Integer.parseInt(validity.getText());
            String currency = "uah";
            String insureEvent = insure_event.getText();
            String insureObject = insure_object.getText();
            double paymentAmount = Double.parseDouble(payment_amount.getText());
            double contributions_client = paymentAmount / validity_insure;
            double riskLevel = Double.parseDouble(risk_lvl.getText());
            return getTypeContract(contract_id, name_organization, address_organization, surnameClient,
                    firstName_client, midName_client, addressClient, passportId, validity_insure,
                    currency, insureEvent, insureObject, paymentAmount, contributions_client, riskLevel,
                    type_contract, derivative_id);
        }catch (Exception e){
            Logger.getGlobal().warning("an error in the input data.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return null;
    }

    private Contract getTypeContract(int contract_id, String name_organization, String address_organization,
                                            String surname_client, String first_name_client, String mid_name_client,
                                            String address_client, int passport_id, int validity,
                                            String currency, String insure_event, String insure_object, double payment_amount,
                                            double contributions_client, double riskLvl,
                                            String type_contract, int derivative_id){
        if(type_contract.equals("Liability insurance"))
            return new LiabilityInsurance(contract_id, name_organization, address_organization, surname_client,
                    first_name_client, mid_name_client, address_client, passport_id, validity,
                    currency, insure_event, insure_object, payment_amount,
                    contributions_client, riskLvl, type_contract, derivative_id);
        else if(type_contract.equals("Personal insurance"))
            return new PersonalInsurance(contract_id, name_organization, address_organization, surname_client,
                    first_name_client, mid_name_client, address_client, passport_id, validity,
                    currency, insure_event, insure_object, payment_amount,
                    contributions_client, riskLvl, type_contract, derivative_id);
        else
            return new PropertyInsurance(contract_id, name_organization, address_organization, surname_client,
                    first_name_client, mid_name_client, address_client, passport_id, validity,
                    currency, insure_event, insure_object, payment_amount,
                    contributions_client, riskLvl, type_contract, derivative_id);
    }
}

