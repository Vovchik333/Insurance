package menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class DeleteDerivative implements MenuItem{

    @Override
    public String getInformation() {
        return "delete derivative";
    }

    @Override
    public void execute() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/deleteDerivative_scene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("delete derivative");
            stage.show();
            Logger.getGlobal().info("The window \"delete derivative\" successfully created.");
        }catch (IOException e){
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}
