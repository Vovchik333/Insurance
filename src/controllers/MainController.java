package controllers;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import menu.MenuItem;
import menu.MenuMain;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    private MenuMain menu;

    @FXML
    public void initialize() {
        menu = new MenuMain();
        ObservableList<String> list = FXCollections.observableArrayList();
        LinkedHashMap<String,MenuItem> commands = (LinkedHashMap<String,MenuItem>)MenuMain.getMenuItems();
        for(String item : commands.keySet())
            list.add(commands.get(item).getInformation());
        listView.setItems(list);
    }

    @FXML
    void clickMouse(MouseEvent event) {
        String action = listView.getSelectionModel().getSelectedItem();
        if(action != null)
            menu.execute(action);
    }
}