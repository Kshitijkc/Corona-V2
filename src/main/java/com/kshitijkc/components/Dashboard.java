package com.kshitijkc.components;

import com.jfoenix.controls.JFXDrawersStack;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Dashboard {
    public static class Elements {
        public static GridPane indicator = null;
        public static JFXDrawersStack drawersStack = null;
    }

    public static boolean isMouseExited = false;

    public static void configDrawerStack() {
        adjustResolution();
    }

    private static void adjustResolution() {
        Elements.indicator.setPadding(new Insets(0, 0, (AppConfig.currentHeight * (4.0)) / AppConfig.defaultHeight, 0));
    }
}
