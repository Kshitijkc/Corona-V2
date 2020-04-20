package com.kshitijkc.controllers;

import com.kshitijkc.resources.TopDrawer;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDrawerController implements Initializable {
    public StackPane topDrawerPane;
    public AnchorPane topDrawerSticker;
    public Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TopDrawer.Element.topDrawerPane = topDrawerPane;
        TopDrawer.Element.topDrawerSticker = topDrawerSticker;
        TopDrawer.Element.time = time;
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        time.setTextFill(Color.rgb(255, 255, 255));
    }

    public void onMouseExit(MouseEvent mouseEvent) {
        time.setTextFill(Color.rgb(200, 200, 200));
    }
}
