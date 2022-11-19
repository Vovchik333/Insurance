package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShowFinancialPositionTest {

    @Test
    void showFinancialPosition() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        ArrayList<String> arrayList = ConnectorDB.showTable("SELECT * FROM FinancialPosition", 2);
        assertNotNull(arrayList);
        ConnectorDB.closeConnection();
    }
}