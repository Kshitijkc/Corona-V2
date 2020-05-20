package com.kshitijkc.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.kshitijkc.components.AppConfig;
import com.kshitijkc.components.LeftDrawer;
import com.kshitijkc.components.Dashboard;
import com.kshitijkc.components.TopDrawer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kshitijkc.components.LeftDrawer.Elements.leftDrawer;
import static com.kshitijkc.components.TopDrawer.Elements.*;
import static com.kshitijkc.components.TopDrawer.clock;
import static com.kshitijkc.components.TopDrawer.fadeIn;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class DashboardController implements Initializable {
    public GridPane indicator;
    public JFXDrawersStack drawersStack;

    private static final String LEFT = "LEFT";
    private static final String TOP = "TOP";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dashboard.Elements.indicator = indicator;
        Dashboard.Elements.drawersStack = drawersStack;

        FlowPane content = new FlowPane();
        JFXButton leftButton = new JFXButton(LEFT);
        content.getChildren().addAll(leftButton);

        buildLeftDrawer();
        buildTopDrawer();

        drawersStack.setContent(content);

        leftButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(leftDrawer));
    }

    private void buildLeftDrawer() {
        leftDrawer = new JFXDrawer();
        VBox leftDrawerPane = null;
        try {
            leftDrawerPane = FXMLLoader.load(getClass().getResource("/fxml/LeftDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        leftDrawer.setSidePane(leftDrawerPane);
        if(AppConfig.screenIndependent)
            leftDrawer.setDefaultDrawerSize((AppConfig.currentWidth * (100.0)) / AppConfig.defaultWidth);
        leftDrawer.setResizeContent(true); // cool trick to move the dashboard's main content to sideways when opening
        leftDrawer.setOverLayVisible(true); // enables drawer to close when clicked in dashboard's main area when opened
        leftDrawer.setId(LEFT);
    }

    private void buildTopDrawer() {
        topDrawer = new JFXDrawer();
        StackPane topDrawerPane = null;
        try {
            topDrawerPane = FXMLLoader.load(getClass().getResource("/fxml/TopDrawer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTopDrawerEvent(topDrawer);
        topDrawer.setPadding(new Insets(0, 3, 0, 3));
        topDrawer.setSidePane(topDrawerPane);
        topDrawer.setDirection(JFXDrawer.DrawerDirection.TOP); // sets the drawer to open from top
        topDrawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getHeight() * (425.0 / 768.0));
        topDrawer.setResizeContent(false); // cool trick to move the dashboard's main content to sideways when opening
        topDrawer.setOverLayVisible(true); // enables drawer to close when clicked in dashboard's main area when opened
        topDrawer.setId(TOP);
    }

    public void setTopDrawerEvent(JFXDrawer topDrawer) {
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

    public void OnMouseEntered(MouseEvent mouseEvent) {
        System.out.println("Mouse Entered");
        TopDrawer.resetTimer();
        Dashboard.isMouseExited = false;
    }

    public void OnMouseExited(MouseEvent mouseEvent) {
        System.out.println("Mouse Exited");
        LeftDrawer.closeDrawer();
        TopDrawer.setTimer();
        Dashboard.isMouseExited = true;
    }
}
