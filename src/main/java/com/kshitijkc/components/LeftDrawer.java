package com.kshitijkc.components;

import com.jfoenix.controls.JFXDrawer;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class LeftDrawer {
    public static class Elements {
        public static VBox leftDrawerPane = null;
        public static Circle circularImage = null;
        public static ScrollPane scrollPane = null;
        public static JFXDrawer leftDrawer = null;
        public static VBox scrollBox = null;
        public static RowConstraints scrollPaneContainer = null;
        public static StackPane horizontalBarContainer = null;
        public static Separator separator = null;
    }

    public static void closeDrawer() {
        if(Elements.leftDrawer.isOpening() || Elements.leftDrawer.isOpened()) {
            System.out.println("Closing LeftDrawer");
            Elements.leftDrawer.close();
        }
    }

    public static void configDrawer() {
        adjustResolution();
        setImage();
    }

    private static void adjustResolution() {
        Elements.leftDrawerPane.setPrefHeight((AppConfig.currentHeight * (450.0)) / AppConfig.defaultHeight);
        Elements.leftDrawerPane.setPadding(new Insets((AppConfig.currentHeight * (20.0)) / AppConfig.defaultHeight,0,0,0));
        Elements.circularImage.setRadius((AppConfig.currentWidth * (40.0)) / AppConfig.defaultWidth);
        Elements.horizontalBarContainer.setPrefHeight((AppConfig.currentHeight * (30.0)) / AppConfig.defaultHeight);
        Elements.separator.setPadding(new Insets(0, (AppConfig.currentWidth * (20.0)) / AppConfig.defaultWidth, 0, (AppConfig.currentWidth * (20.0)) / AppConfig.defaultWidth));
        Elements.scrollBox.setPrefWidth((AppConfig.currentWidth * (100.0)) / AppConfig.defaultWidth);
        Elements.scrollBox.setPrefHeight((AppConfig.currentHeight * (250.0)) / AppConfig.defaultHeight);
        Elements.scrollPaneContainer.setPrefHeight((AppConfig.currentHeight * (270.0)) / AppConfig.defaultHeight);
    }

    private static void setImage() {
        Image img = new Image("/logo/me.jpg");
        Elements.circularImage.setFill(new ImagePattern(img));
    }
}
