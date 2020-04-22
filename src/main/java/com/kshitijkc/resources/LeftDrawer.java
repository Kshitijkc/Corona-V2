package com.kshitijkc.resources;

import com.jfoenix.controls.JFXDrawer;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class LeftDrawer {
    public static class Elements {
        public static VBox leftDrawerPane = null;
        public static Circle circularImage = null;
        public static ScrollPane scrollPane = null;
        public static JFXDrawer leftDrawer = null;
    }

    public static void closeDrawer() {
        if(Elements.leftDrawer.isOpening() || Elements.leftDrawer.isOpened()) {
            System.out.println("Closing LeftDrawer");
            Elements.leftDrawer.close();
        }
    }
}
