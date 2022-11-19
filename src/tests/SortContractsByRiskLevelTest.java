package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortContractsByRiskLevelTest {

    @Test
    void sortContractsByRiskLevel() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int der_id = 1;
        ArrayList<String> arrayList = ConnectorDB.showTable(
                "SELECT * FROM Obligations WHERE derivative_id = " + der_id + " ORDER BY risk_lvl DESC", 1);
        assertNotNull(arrayList);
        ConnectorDB.closeConnection();
    }
}