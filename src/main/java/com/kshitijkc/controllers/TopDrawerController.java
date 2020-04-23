package com.kshitijkc.controllers;

import com.kshitijkc.resources.TopDrawer;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static com.kshitijkc.resources.Main.Elements.drawersStack;
import static com.kshitijkc.resources.TopDrawer.Elements.topDrawer;

public class TopDrawerController implements Initializable {
    public StackPane topDrawerPane;
    public AnchorPane topDrawerSticker;
    public Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TopDrawer.Elements.topDrawerPane = topDrawerPane;
        TopDrawer.Elements.topDrawerSticker = topDrawerSticker;
        TopDrawer.Elements.time = time;

        TopDrawer.setAnimation();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        drawersStack.toggle(topDrawer);
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        TopDrawer.Elements.topDrawerPane.setCursor(Cursor.DEFAULT);
        time.setTextFill(Color.rgb(255, 255, 255));
        TopDrawer.scaleTranslateUp();
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        System.out.println("Mouse Exited TopDrawer");
        TopDrawer.scaleTranslateDown();
        time.setTextFill(Color.rgb(190, 190, 190));
    }
}
