package tests;

import org.junit.jupiter.api.Test;
import work_with_DB.ConnectorDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShowContractsOnePersonTest {

    @Test
    void showContractsOnePerson() throws ClassNotFoundException {
        ConnectorDB connectorDB = new ConnectorDB();
        connectorDB.setConnection();
        String surname = "Petrenko";
        String first_name = "Vasyl";
        String mid_name = "Romanovych";
        int der_id = 1;
        ArrayList<String> arrayList = ConnectorDB.showTable(
                String.format("SELECT * FROM Obligations WHERE surname_client = '%s' AND " +
                                "first_name_client = '%s' AND mid_name_client = '%s' AND derivative_id = %d",
                        surname, first_name, mid_name, der_id), 1);
        assertNotNull(arrayList);
        ConnectorDB.closeConnection();
    }
}