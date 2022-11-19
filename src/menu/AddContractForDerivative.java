package menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Logger;

public class AddContractForDerivative implements MenuItem{
    @Override
    public String getInformation() {
        return "add contract";
    }

    @Override
    public void execute() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/addContractForDerivative_scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("add contract");
            stage.show();
            Logger.getGlobal().info("The window \"add contract\" successfully created.");
        }catch (IOException e){
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}

