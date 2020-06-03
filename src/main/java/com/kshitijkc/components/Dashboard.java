package com.kshitijkc.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.kshitijkc.controllers.DashboardController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;

import static com.kshitijkc.components.LeftDrawer.Elements.leftDrawer;
import static com.kshitijkc.components.TopDrawer.Elements.topDrawer;
import static com.kshitijkc.components.TopDrawer.Elements.time;
import static com.kshitijkc.components.TopDrawer.Elements.timeLine;
import static com.kshitijkc.components.TopDrawer.clock;
import static com.kshitijkc.components.TopDrawer.fadeIn;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class Dashboard {
    public static class Elements {
        public static GridPane indicator = null;
        public static JFXDrawersStack drawersStack = null;
    }

    public static boolean isMouseExited = false;
    private static final String LEFT = "LEFT";
    private static final String TOP = "TOP";

    public static void configDrawerStack(DashboardController dashboardController) {
        adjustResolution();
        buildLeftDrawer(dashboardController);
        buildTopDrawer(dashboardController);
        buildMainContent();
    }

    private static void adjustResolution() {
        Elements.indicator.setPadding(new Insets(0, 0, (AppConfig.currentHeight * (4.0)) / AppConfig.defaultHeight, 0));
    }

    private static void buildLeftDrawer(DashboardController dashboardController) {
        leftDrawer = new JFXDrawer();
        VBox leftDrawerPane = null;
        try {
            leftDrawerPane = FXMLLoader.load(dashboardController.getClass().getResource("/fxml/LeftDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        leftDrawer.setSidePane(leftDrawerPane);
        leftDrawer.setDefaultDrawerSize((AppConfig.currentWidth * (100.0)) / AppConfig.defaultWidth);
        leftDrawer.setResizeContent(true); // cool trick to move the dashboard's main content to sideways when opening
        leftDrawer.setOverLayVisible(true); // enables drawer to close when clicked in dashboard's main area when opened
        leftDrawer.setId(LEFT);
    }

    private static void buildTopDrawer(DashboardController dashboardController) {
        topDrawer = new JFXDrawer();
        StackPane topDrawerPane = null;
        try {
            topDrawerPane = FXMLLoader.load(dashboardController.getClass().getResource("/fxml/TopDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTopDrawerEvent(topDrawer);
        topDrawer.setPadding(new Insets(0, 3, 0, 3));
        topDrawer.setSidePane(topDrawerPane);
        topDrawer.setDirection(JFXDrawer.DrawerDirection.TOP); // sets the drawer to open from top
        topDrawer.setDefaultDrawerSize(AppConfig.currentHeight);
        topDrawer.setResizeContent(false); // cool trick to move the dashboard's main content to sideways when opening
        topDrawer.setOverLayVisible(true); // enables drawer to close when clicked in dashboard's main area when opened
        topDrawer.setId(TOP);
    }

    public static void setTopDrawerEvent(JFXDrawer topDrawer) {
        topDrawer.setOnDrawerOpening(event -> {
            if(!TopDrawer.isOpened) {
                time.setOpacity(0.0);
                timeLine.setOpacity(0.0);
            }
        });
        topDrawer.setOnDrawerOpened(event -> {
            System.out.println("topDrawer Opened");
            if(!TopDrawer.isOpened) {
                time.setOpacity(0.0);
                timeLine.setOpacity(1.0);
                time.setVisible(true);
                TopDrawer.resetTimer();
                TopDrawer.setAnimation();
                clock.play();
                fadeIn.playFromStart();
            }
            TopDrawer.isOpened = true;
        });
        topDrawer.setOnDrawerClosed(event -> {
            System.out.println("topDrawer Closed");
            TopDrawer.reset();
            time.setVisible(false);
            timeLine.setOpacity(0.0);
            clock.stop();
            if(Dashboard.isMouseExited)
                TopDrawer.setTimer();
            TopDrawer.isOpened = false;
        });
    }

    private static void buildMainContent() {
        FlowPane content = new FlowPane();
        JFXButton leftButton = new JFXButton(LEFT);
        content.getChildren().addAll(leftButton);

        Elements.drawersStack.setContent(content);

        leftButton.addEventHandler(MOUSE_PRESSED, e -> Elements.drawersStack.toggle(leftDrawer));
    }
}
