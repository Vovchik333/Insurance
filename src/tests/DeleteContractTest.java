package tests;

import derivatives.Derivative;
import obligations.Contract;
import obligations.PropertyInsurance;
import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import static org.junit.jupiter.api.Assertions.*;

class DeleteContractTest {

    @Test
    void deleteContract() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int id_for_contract = ConnectorDB.getValueInt(
                "SELECT MAX(contract_id) AS maxId FROM Obligations","maxId") + 1;
        int countExpected = ConnectorDB.getValueInt(
                "SELECT count_contracts FROM Derivatives WHERE ID = " + 1, "count_contracts");
        Contract.insertContract(createContract(id_for_contract));
        Derivative.addContract(1);
        Contract contract = createContract(id_for_contract);
        assertNotNull(contract);
        ConnectorDB.updateTable("DELETE FROM Obligations WHERE contract_id = "+ id_for_contract);
        Derivative.difContract(1);
        int countActual = ConnectorDB.getValueInt(
                "SELECT count_contracts FROM Derivatives WHERE ID = " + 1, "count_contracts");
        assertEquals(countExpected, countActual);
        ConnectorDB.closeConnection();
    }

    public Contract createContract(int contract_id) {
        String type_contract = "Personal insurance";
        String name_organization = "ComfortInsure";
        String address_organization = "St. Kulparkivska, 200a, Lviv";
        String surnameClient = "Petrenko";
        String firstName_client = "Dmytro";
        String midName_client = "Oleksiyovych";
        String addressClient = "Kulparkivska, 243, Lviv";
        int passportId = 73821;
        int validity_insure = 200;
        String currency = "uah";
        String insureEvent = "insure car";
        String insureObject = "car";
        double paymentAmount = 30000;
        double contributions_client = paymentAmount / validity_insure;
        double riskLevel = 27;
        return new PropertyInsurance(contract_id, name_organization, address_organization, surnameClient,
                firstName_client, midName_client, addressClient, passportId, validity_insure,
                currency, insureEvent, insureObject, paymentAmount, contributions_client, riskLevel,
                type_contract, 1);
    }
}