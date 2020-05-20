package com.kshitijkc.controllers;

import com.kshitijkc.components.AppConfig;
import com.kshitijkc.components.LeftDrawer;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftDrawerController implements Initializable {
    public VBox leftDrawerPane;
    public Circle circularImage;
    public ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LeftDrawer.Elements.leftDrawerPane = leftDrawerPane;
        LeftDrawer.Elements.circularImage = circularImage;
        LeftDrawer.Elements.scrollPane = scrollPane;

        configDrawer();
    }

    private void configDrawer() {
        setImageSize();
        setImage();
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
