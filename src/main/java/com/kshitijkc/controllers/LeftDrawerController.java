package com.kshitijkc.controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftDrawerController implements Initializable {
    public StackPane drawerPane;
    public Circle circularImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("/logo/me.jpg");
        circularImage.setFill(new ImagePattern(img));
    }
}
