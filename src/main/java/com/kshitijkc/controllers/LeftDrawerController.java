package com.kshitijkc.controllers;

import com.kshitijkc.components.AppConfig;
import com.kshitijkc.components.LeftDrawer;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftDrawerController implements Initializable {
    public VBox leftDrawerPane;
    public Circle circularImage;
    public ScrollPane scrollPane;
    public VBox scrollBox;
    public RowConstraints scrollPaneContainer;
    public StackPane horizontalBarContainer;
    public Separator separator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LeftDrawer.Elements.leftDrawerPane = leftDrawerPane;
        LeftDrawer.Elements.circularImage = circularImage;
        LeftDrawer.Elements.scrollPane = scrollPane;

        configDrawer();
    }

    private void configDrawer() {
        setPane();
        setImageSize();
        setImage();
    }

    private void setPane() {
        leftDrawerPane.setPrefHeight((AppConfig.currentHeight * (450.0)) / AppConfig.defaultHeight);
        leftDrawerPane.setPadding(new Insets((AppConfig.currentHeight * (20.0)) / AppConfig.defaultHeight,0,0,0));
        horizontalBarContainer.setPrefHeight((AppConfig.currentHeight * (30.0)) / AppConfig.defaultHeight);
        separator.setPadding(new Insets(0, (AppConfig.currentWidth * (20.0)) / AppConfig.defaultWidth, 0, (AppConfig.currentWidth * (20.0)) / AppConfig.defaultWidth));
        scrollBox.setPrefWidth((AppConfig.currentWidth * (100.0)) / AppConfig.defaultWidth);
        scrollBox.setPrefHeight((AppConfig.currentHeight * (250.0)) / AppConfig.defaultHeight);
        scrollPaneContainer.setPrefHeight((AppConfig.currentHeight * (270.0)) / AppConfig.defaultHeight);
    }

    private void setImageSize() {
        circularImage.setRadius((AppConfig.currentWidth * (40.0)) / AppConfig.defaultWidth);
        System.out.println("Height : " + AppConfig.currentHeight + ", Width : " + AppConfig.currentWidth);
    }

    private void setImage() {
        Image img = new Image("/logo/me.jpg");
        circularImage.setFill(new ImagePattern(img));
    }
}
