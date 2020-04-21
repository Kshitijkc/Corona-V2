package com.kshitijkc.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.kshitijkc.resources.DrawerStack;
import com.kshitijkc.resources.Main;
import com.kshitijkc.resources.TopDrawer;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.kshitijkc.resources.TopDrawer.Element.time;
import static com.kshitijkc.resources.LeftDrawer.leftDrawer;
import static com.kshitijkc.resources.TopDrawer.clock;
import static com.kshitijkc.resources.TopDrawer.topDrawer;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class MainController implements Initializable {
    public GridPane indicator;
    public JFXDrawersStack drawersStack;

    private static final String LEFT = "LEFT";
    private static final String TOP = "TOP";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.indicator = indicator;
        Main.drawersStack = drawersStack;

        FlowPane content = new FlowPane();
        JFXButton leftButton = new JFXButton(LEFT);
        content.getChildren().addAll(leftButton);

        buildLeftDrawer();
        buildTopDrawer();

        DrawerStack.drawersStack = drawersStack;
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
        leftDrawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getWidth() * (100.0 / 1366.0));
        leftDrawer.setResizeContent(true); // cool trick to move the main content to sideways when opening
        leftDrawer.setOverLayVisible(true); // enables drawer to close when clicked in main area when opened
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
        topDrawer.setResizeContent(false); // cool trick to move the main content to sideways when opening
        topDrawer.setOverLayVisible(true); // enables drawer to close when clicked in main area when opened
        topDrawer.setId(TOP);
    }

    public void setTopDrawerEvent(JFXDrawer topDrawer) {
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        FadeTransition fadeIn = new FadeTransition(
                Duration.millis(5000)
        );
        fadeIn.setNode(time);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        topDrawer.setOnDrawerOpened(event -> {
            System.out.println("HAHAHA Clocking : ");
            clock.play();
            time.setOpacity(0.0);
            time.setVisible(true);
            fadeIn.playFromStart();
        });
        topDrawer.setOnDrawerClosed(event -> {
            clock.stop();
            time.setVisible(false);
            System.out.println("StooooooPpPed");
            if(Main.isMouseExited && TopDrawer.timer == null)
                TopDrawer.setTimer();
        });
    }

    public void OnMouseEntered(MouseEvent mouseEvent) {
        System.out.println("Mouse Entered");
        TopDrawer.stopTimer();
        Main.isMouseExited = false;
    }

    public void OnMouseExited(MouseEvent mouseEvent) {
        System.out.println("Mouse Exited");
        if(TopDrawer.timer == null && topDrawer.isClosed()) {
            TopDrawer.setTimer();
        }

        if(leftDrawer.isOpening() || leftDrawer.isOpened())
            leftDrawer.close();

        Main.isMouseExited = true;
    }
}
