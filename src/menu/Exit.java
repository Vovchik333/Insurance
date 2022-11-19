package menu;

import work_with_DB.ConnectorDB;

import java.util.logging.Logger;

public class Exit implements MenuItem{
    @Override
    public String getInformation() {
        return "exit";
    }

    @Override
    public void execute(){
        ConnectorDB.closeConnection();
        Logger.getGlobal().info("The program successfully ended.");
        System.exit(0);
    }
}
