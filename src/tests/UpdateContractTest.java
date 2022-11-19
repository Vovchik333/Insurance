package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UpdateContractTest {

    @Test
    void updateContract() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int id = 1;
        String value = "'ETC'";
        int oldID = ConnectorDB.getValueInt(
                "SELECT contract_id FROM Obligations WHERE contract_id = " + 1, "contract_id");
        String oldValue = ConnectorDB.getValueString(
                "SELECT currency FROM Obligations WHERE contract_id = " + 1, "currency");
        ConnectorDB.updateTable(String.format("UPDATE Obligations SET %s = %s WHERE contract_id = %d",
                "currency", value, id));
        int newID = ConnectorDB.getValueInt(
                "SELECT contract_id FROM Obligations WHERE contract_id = " + 1, "contract_id");
        String newValue = ConnectorDB.getValueString(
                "SELECT currency FROM Obligations WHERE contract_id = " + 1, "currency");
        assertEquals(oldID, newID);
        assertNotEquals(oldValue, newValue);
        ConnectorDB.updateTable(String.format("UPDATE Obligations SET %s = 'uah' WHERE contract_id = %d",
                "currency", id));
        ConnectorDB.closeConnection();
    }
}