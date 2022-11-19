package derivatives;

import work_with_DB.ConnectorDB;

public class Derivative {
    private int derivative_id;
    private int count_contracts;

    public Derivative(int derivative_id, int count_contracts) {
        this.derivative_id = derivative_id;
        this.count_contracts = count_contracts;
    }

    public void insertDerivative(){
        ConnectorDB.updateTable(String.format("INSERT INTO Derivatives(ID, count_contracts) " +
                "VALUES (%d, %d)", derivative_id, count_contracts));
    }

    public static void addContract(int der_id){
        ConnectorDB.updateTable("UPDATE Derivatives SET count_contracts = count_contracts + 1 " +
                "WHERE ID = " + der_id);
    }

    public static void difContract(int der_id){
        ConnectorDB.updateTable("UPDATE Derivatives SET count_contracts = count_contracts - 1 " +
                "WHERE ID = " + der_id);
    }
}


