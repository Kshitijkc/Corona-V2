package com.kshitijkc.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawersStack;
import com.kshitijkc.controllers.DashboardController;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import static com.kshitijkc.components.LeftDrawer.Elements.leftDrawer;
import static com.kshitijkc.components.LeftDrawer.buildLeftDrawer;
import static com.kshitijkc.components.TopDrawer.buildTopDrawer;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class Dashboard {
    public static class Elements {
        public static GridPane indicator = null;
        public static JFXDrawersStack drawersStack = null;
    }

    public static boolean isMouseExited = false;
    private static final String LEFT = "LEFT";

    public static void configDrawerStack(DashboardController dashboardController) {
        adjustResolution();
        buildLeftDrawer(dashboardController);
        buildTopDrawer(dashboardController);
        buildMainContent();
    }

    private static void adjustResolution() {
        Elements.indicator.setPadding(new Insets(0, 0, (AppConfig.currentHeight * (4.0)) / AppConfig.defaultHeight, 0));
    }

    private static void buildMainContent() {
        FlowPane content = new FlowPane();
        JFXButton leftButton = new JFXButton(LEFT);
        content.getChildren().addAll(leftButton);

        Elements.drawersStack.setContent(content);

        leftButton.addEventHandler(MOUSE_PRESSED, e -> Elements.drawersStack.toggle(leftDrawer));
    }
}
