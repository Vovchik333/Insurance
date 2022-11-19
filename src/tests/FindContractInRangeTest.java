package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FindContractInRangeTest {

    @Test
    void findContractInRange() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int derivative_id = 1;
        String type = "payment_amount";
        double start = 19999.99;
        double end = 21000;
        ArrayList<String> arrayList = ConnectorDB.showTable(String.format("SELECT * FROM Obligations WHERE " +
                "%s > " + start + " AND %s < " + end + " AND derivative_id = %d", type, type, derivative_id), 1);
        assertNotNull(arrayList);
        String paymentAmount = arrayList.get(13);
        double findValue = Double.parseDouble(paymentAmount.substring(paymentAmount.indexOf(":") + 1));
        assertTrue(findValue > start && findValue < end);
        ConnectorDB.closeConnection();
    }
}