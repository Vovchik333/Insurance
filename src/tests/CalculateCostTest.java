package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;
import static org.junit.jupiter.api.Assertions.*;

class CalculateCostTest {

    @Test
    void calculateCost() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int der_id = 1;
        double res = ConnectorDB.getValueDouble("SELECT SUM(payment_amount) AS sumCost FROM Obligations "
                + "WHERE derivative_id = " + der_id, "sumCost");
        assertTrue(res > 0);
        ConnectorDB.closeConnection();
    }
}