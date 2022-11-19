package tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ShowContractsSpecificTypeTest {

    @Test
    void showContractsSpecificType() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        String type = "Liability insurance";
        int der_id = 1;
        ArrayList<String> arrayList = ConnectorDB.showTable(
                String.format("SELECT * FROM Obligations WHERE type_contract = '%s' AND " +
                        "derivative_id = %d", type, der_id), 1);
        assertNotNull(arrayList);
        ConnectorDB.closeConnection();
    }
}