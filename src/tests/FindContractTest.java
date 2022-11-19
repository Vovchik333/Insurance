package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FindContractTest {

    @Test
    void findContract() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int id = 1, derivative_id = 1;
        ArrayList<String> arrayList = ConnectorDB.showTable(String.format(
                "SELECT * FROM Obligations WHERE contract_id = %d AND derivative_id = %d", id, derivative_id), 1);
        assertNotNull(arrayList);
        ConnectorDB.closeConnection();
    }
}