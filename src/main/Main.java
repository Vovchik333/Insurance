package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import loggers.InsureLog;
import work_with_DB.ConnectorDB;

import java.io.IOException;
import java.util.logging.*;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) throws ClassNotFoundException{
        Logger logger = Logger.getGlobal();
        InsureLog.configureLogger(logger);
        logger.info("The main class has started work.");
        ConnectorDB cdb = new ConnectorDB();
        cdb.setConnection();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/main_scene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Insure desktop app");
        stage = primaryStage;
        primaryStage.show();
    }
}


