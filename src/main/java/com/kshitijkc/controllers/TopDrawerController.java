package com.kshitijkc.controllers;

import com.kshitijkc.resources.TopDrawer;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.kshitijkc.resources.Main.Elements.drawersStack;
import static com.kshitijkc.resources.TopDrawer.*;

public class TopDrawerController implements Initializable {
    public StackPane topDrawerPane;
    public AnchorPane topDrawerSticker;
    public Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TopDrawer.Elements.topDrawerPane = topDrawerPane;
        TopDrawer.Elements.topDrawerSticker = topDrawerSticker;
        TopDrawer.Elements.time = time;

        setAnimation();
    }

    private void setAnimation() {
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);

        fadeIn = new FadeTransition(Duration.millis(5000));
        fadeIn.setNode(time);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        drawersStack.toggle(topDrawer);
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        time.setTextFill(Color.rgb(255, 255, 255));
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        time.setTextFill(Color.rgb(200, 200, 200));
        System.out.println("Mouse Exited Drawer");
    }
}
