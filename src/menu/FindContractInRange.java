package menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class FindContractInRange implements MenuItem{
    @Override
    public String getInformation() {
        return "find contract in range";
    }

    @Override
    public void execute() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/findContractInRange_scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("find contract in range");
            stage.show();
            Logger.getGlobal().info("The window \"find contract in range\" successfully created.");
        }catch (IOException e){
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}
