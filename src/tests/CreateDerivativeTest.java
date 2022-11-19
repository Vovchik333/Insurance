package tests;

import derivatives.Derivative;
import obligations.Contract;
import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateDerivativeTest {

    @Test
    void createDerivative() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        int id_for_derivative = ConnectorDB.getValueInt(
                "SELECT MAX(ID) AS maxId FROM Derivatives","maxId") + 1;
        int countExpected = ConnectorDB.getValueInt(
                "SELECT COUNT(*) AS max FROM Derivatives", "max") + 1;
        Derivative derivative = new Derivative(id_for_derivative,0);
        derivative.insertDerivative();
        assertNotNull(derivative);
        int countActual = ConnectorDB.getValueInt(
                "SELECT COUNT(*) AS max FROM Derivatives", "max");
        assertEquals(countExpected, countActual);
        ConnectorDB.updateTable("DELETE FROM Derivatives WHERE ID = " + id_for_derivative);
        ConnectorDB.closeConnection();
    }
}