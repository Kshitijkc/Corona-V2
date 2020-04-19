package com.kshitijkc.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class MainController implements Initializable {
    public JFXDrawersStack drawersStack;
    public GridPane indicator;
    private JFXDrawer leftDrawer;
    private JFXDrawer topDrawer;
    private static final String LEFT = "LEFT";
    private static final String TOP = "TOP";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FlowPane content = new FlowPane();
        JFXButton leftButton = new JFXButton(LEFT);
        JFXButton topButton = new JFXButton(TOP);
        content.getChildren().addAll(leftButton, topButton);

        // Left Drawer Pane
        leftDrawer = new JFXDrawer();
        StackPane leftDrawerPane = null;
        try {
            leftDrawerPane = FXMLLoader.load(getClass().getResource("/fxml/LeftDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        leftDrawer.setSidePane(leftDrawerPane);
        leftDrawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getWidth() * (100.0 / 1366.0));
        leftDrawer.setResizeContent(true); // cool trick to move the main content to sideways when opening
        leftDrawer.setOverLayVisible(true); // enables drawer to close when clicked in main area when opened
//        leftDrawer.setResizableOnDrag(false); // sets the drawer to be resized and I don't need this

        // Top Drawer Pane
        topDrawer = new JFXDrawer();
        StackPane topDrawerPane = null;
        try {
            topDrawerPane = FXMLLoader.load(getClass().getResource("/fxml/TopDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        topDrawer.setPadding(new Insets(0, 3, 0, 3));
        topDrawer.setSidePane(topDrawerPane);
        topDrawer.setDirection(JFXDrawer.DrawerDirection.TOP); // sets the drawer to open from top
        topDrawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getHeight() * (425.0 / 768.0));
        topDrawer.setResizeContent(false); // cool trick to move the main content to sideways when opening
        topDrawer.setOverLayVisible(true); // enables drawer to close when clicked in main area when opened

        drawersStack.setContent(content);

        leftDrawer.setId(LEFT);
        topDrawer.setId(TOP);

        leftButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(leftDrawer));
        topButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(topDrawer));
    }

    public void showMenuItem(MouseEvent mouseEvent) {
    }

    public void hideMenuItem(MouseEvent mouseEvent) {
    }
}
