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
        LeftDrawer.Elements.scrollBox = scrollBox;
        LeftDrawer.Elements.scrollPaneContainer = scrollPaneContainer;
        LeftDrawer.Elements.horizontalBarContainer = horizontalBarContainer;
        LeftDrawer.Elements.separator = separator;

        LeftDrawer.configDrawer();
    }
}
