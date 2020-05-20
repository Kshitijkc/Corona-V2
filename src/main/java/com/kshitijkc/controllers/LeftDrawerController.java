package com.kshitijkc.controllers;

import com.kshitijkc.components.LeftDrawer;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

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
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        circularImage.setRadius((primaryScreenBounds.getWidth() * (40.0)) / 1366.0);
    }

    private void setImage() {
        Image img = new Image("/logo/me.jpg");
        circularImage.setFill(new ImagePattern(img));
    }
}
