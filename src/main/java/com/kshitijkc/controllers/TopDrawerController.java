package com.kshitijkc.controllers;

import com.jfoenix.controls.JFXProgressBar;
import com.kshitijkc.components.AppConfig;
import com.kshitijkc.components.TopDrawer;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

import static com.kshitijkc.components.Dashboard.Elements.drawersStack;
import static com.kshitijkc.components.TopDrawer.Elements.topDrawer;

public class TopDrawerController implements Initializable {
    public StackPane topDrawerPane;
    public AnchorPane topDrawerSticker;
    public Label time;
    public JFXProgressBar timeLine;
    public GridPane stickerContainer;
    public GridPane timeContainer;
    public GridPane timelineContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TopDrawer.Elements.topDrawerPane = topDrawerPane;
        TopDrawer.Elements.topDrawerSticker = topDrawerSticker;
        TopDrawer.Elements.time = time;
        TopDrawer.Elements.timeLine = timeLine;

        TopDrawer.setAnimation();
        configDrawer();
    }

    private void configDrawer() {
        setPane();
    }

    private void setPane() {
        stickerContainer.setPadding(new Insets(0, (AppConfig.currentWidth * (115.0)) / AppConfig.defaultWidth, (AppConfig.currentHeight * (150.0)) / AppConfig.defaultHeight, (AppConfig.currentWidth * (20.0)) / AppConfig.defaultWidth));
        if(AppConfig.currentHeight > 425.0) {
            timeContainer.setPadding(new Insets(0, 0, 0, (AppConfig.currentWidth * (40.0)) / AppConfig.defaultWidth));
            time.setFont(new Font("SansSerif Regular", (AppConfig.currentWidth * (41.0)) / AppConfig.defaultWidth));
            timelineContainer.setPadding(new Insets((AppConfig.currentHeight * (40.0)) / AppConfig.defaultHeight, 0, 0, (AppConfig.currentWidth * (33.0)) / AppConfig.defaultWidth));
            timeLine.setMaxWidth((AppConfig.currentWidth * (60.0)) / AppConfig.defaultWidth);
            timeLine.setPrefHeight((AppConfig.currentHeight * (3.0)) / AppConfig.defaultHeight);
        }
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
