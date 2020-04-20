package com.kshitijkc.resources;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class TopDrawer {
    public static Thread timer = null;
    public static long timeOut = 10000;
    public static boolean isVisible = false;

    public static class Element {
        public static StackPane topDrawerPane = null;
        public static AnchorPane topDrawerSticker = null;
        public static Label time = null;
    }
}
