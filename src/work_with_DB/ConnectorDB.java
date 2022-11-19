package work_with_DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class ConnectorDB {
    private static Connection connection;
    private static Statement statement;

    public void setConnection() throws ClassNotFoundException{
        String user_name = "root";
        String password = "MySql333";
        String url = "jdbc:mysql://localhost:3306/MySql";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try{
            connection = DriverManager.getConnection(url, user_name, password);
            statement = connection.createStatement();
            Logger.getGlobal().info("The connection to the database was successful.");
        } catch (SQLException e) {
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
    }

    public static void updateTable(String query) {
        try {
            statement.execute("USE InsureDB");
            statement.executeUpdate(query);
            Logger.getGlobal().info("Table successfully updated.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
    }

    public static ArrayList<String> showTable(String query, int choice) {
        ArrayList<String> arrayList =  new ArrayList<>();
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery(query);
            if(choice == 1)
                setSelect(resultSet, arrayList);
            else
                setSelectFinance(resultSet, arrayList);
            Logger.getGlobal().info("the table successfully showed.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return  arrayList;
    }

    public static ArrayList<String> setSelect(ResultSet resultSet, ArrayList<String> list){
        try {
            while (resultSet.next()) {
                list.add("\tContract " + resultSet.getString("contract_id"));
                list.add("Organization: " + resultSet.getString("name_org"));
                list.add("Address organization: " + resultSet.getString("address_org"));
                list.add("Surname client: " + resultSet.getString("surname_client"));
                list.add("First name client: " + resultSet.getString("first_name_client"));
                list.add("Middle name client: " + resultSet.getString("mid_name_client"));
                list.add("Address client: " + resultSet.getString("address_client"));
                list.add("Passport ID: " + resultSet.getString("passport_id"));
                list.add("Date start: " + resultSet.getString("date_start"));
                list.add("Validity: " + resultSet.getString("validity"));
                list.add("Currency: " + resultSet.getString("currency"));
                list.add("Insure event: " + resultSet.getString("insure_event"));
                list.add("Insure object: " + resultSet.getString("insure_obj"));
                list.add("Payment amount: " + resultSet.getString("payment_amount"));
                list.add("Contributions client: " + resultSet.getString("contributions_client"));
                list.add("Risk level: " + resultSet.getString("risk_lvl"));
                list.add("Type of contract: " + resultSet.getString("type_contract"));
                list.add("Number of derivative: " + resultSet.getString("derivative_id") + "\n");
            }
            Logger.getGlobal().info("all value successfully set for Obligations.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return list;
    }

    public static ArrayList<String> setSelectFinance(ResultSet resultSet, ArrayList<String> list){
        StringBuilder result = new StringBuilder();
        try {
            while (resultSet.next()) {
                result.append(String.format("%3d|", resultSet.getInt("ID")));
                result.append(String.format("%15.2f|", resultSet.getDouble("Insure_Reserve")));
                result.append(String.format("%12s|\n", resultSet.getString("ModifiedDate")));
                list.add(result.toString());
            }
            Logger.getGlobal().info("all value successfully set for FinancePosition.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return list;
    }

    public static ArrayList<String> showID(String query) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println();
            arrayList.add("id");
            while (resultSet.next())
                arrayList.add(resultSet.getString("id"));
            Logger.getGlobal().info("all id successfully showed.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return arrayList;
    }

    public static String getValueString(String query, String column) {
        String res = null;
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getString(column);
            Logger.getGlobal().info("String value successfully retrieved.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static int getValueInt(String query, String column) {
        int res = 0;
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getInt(column);
            Logger.getGlobal().info("integer value successfully retrieved.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static double getValueDouble(String query, String column) {
        double res = 0;
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getDouble(column);
            Logger.getGlobal().info("double value successfully retrieved.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static double changeFinancePosition(int id, int choice) {
        double amount, cont, dif, res = 0;
        try {
            statement.execute("USE InsureDB");
            ResultSet resultSet = statement.executeQuery("SELECT payment_amount, contributions_client, " +
                    "DATEDIFF(CURRENT_DATE(), date_start) AS difDate FROM Obligations WHERE contract_id = " + id);
            resultSet.next();
            amount = resultSet.getDouble("payment_amount");
            cont = resultSet.getDouble("contributions_client");
            dif = resultSet.getDouble("difDate");
            if(choice == 1)
                res = amount;
            else
                res = dif * cont - amount;
            Logger.getGlobal().info("the term for the new value was successfully obtained.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
        return res;
    }

    public static void closeConnection() {
        try {
            connection.close();
            Logger.getGlobal().info("connection successfully closed.");
        }catch(SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            System.exit(0);
        }
    }
}
