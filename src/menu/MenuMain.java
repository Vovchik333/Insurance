package menu;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MenuMain {
    private static Map<String, MenuItem> menuItems = new LinkedHashMap<>();

    public MenuMain(){
        menuItems.put("create derivative", new CreateDerivative());
        menuItems.put("add contract", new AddContractForDerivative());
        menuItems.put("show contracts one person", new ShowContractsOnePerson());
        menuItems.put("show contracts specific type", new ShowContractsSpecificType());
        menuItems.put("show contracts", new ShowContracts());
        menuItems.put("show financial position", new ShowFinancialPosition());
        menuItems.put("find contract", new FindContract());
        menuItems.put("find contract in range", new FindContractInRange());
        menuItems.put("delete contract", new DeleteContract());
        menuItems.put("delete derivative", new DeleteDerivative());
        menuItems.put("update contract", new UpdateContract());
        menuItems.put("calculate cost", new CalculateCost());
        menuItems.put("sort contracts by risk level", new SortContractsByRiskLevel());
        menuItems.put("exit", new Exit());
    }

    public static Map<String, MenuItem> getMenuItems() {
        return menuItems;
    }

    public void execute(String command){
        MenuItem item = menuItems.get(command);
        if(item != null)
            menuItems.get(command).execute();
        else
            System.out.println("Incorrect command! Please, try again.");
    }
}
