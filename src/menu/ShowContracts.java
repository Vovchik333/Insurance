package menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class ShowContracts implements MenuItem{
    @Override
    public String getInformation() {
        return "show contracts";
    }

    @Override
    public void execute() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/showContracts_scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("show contracts");
            stage.show();
            Logger.getGlobal().info("The window \"show contracts\" successfully created.");
        }catch (IOException e){
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}
