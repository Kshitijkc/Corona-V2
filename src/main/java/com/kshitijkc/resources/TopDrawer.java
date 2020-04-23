package com.kshitijkc.resources;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.kshitijkc.resources.Main.Elements.drawersStack;

public class TopDrawer {
    public static class Elements {
        public static StackPane topDrawerPane = null;
        public static AnchorPane topDrawerSticker = null;
        public static Label time = null;
        public static JFXDrawer topDrawer = null;
    }

    public static Thread timer = null;
    public static long timeOut = 1000;
    public static Timeline clock = null;
    public static FadeTransition fadeIn = null;
    public static boolean isOpened = false;
    public static ScaleTransition scaleUpTransition = null;
    public static ScaleTransition scaleDownTransition = null;
    public static TranslateTransition translateUpTransition = null;
    public static TranslateTransition translateDownTransition = null;
    public static boolean isTimeScaledUp = false;
    public static boolean isTimeTranslatedUp = false;

    public static void setTimer() {
        if(TopDrawer.timer == null && Elements.topDrawer.isClosed()) {
            TopDrawer.timer = new Thread(() -> {
                try {
                    System.out.println("Sleep Started : " + LocalDateTime.now().getSecond());
                    Thread.sleep(TopDrawer.timeOut);
                    System.out.println("Sleep Stopped : " + LocalDateTime.now().getSecond());
                    System.out.println("Platform Started : " + LocalDateTime.now().getSecond());
                    Platform.runLater(() -> {
                        System.out.println("Platform Run : " + LocalDateTime.now().getSecond());
                        drawersStack.toggle(Elements.topDrawer);
                    });
                } catch (InterruptedException e) {
                    System.out.println("Timer Interrupted");
                }
            });
            TopDrawer.timer.setDaemon(true);
            System.out.println("Thread Starting : " + LocalDateTime.now().getSecond());
            TopDrawer.timer.start();
            System.out.println("Thread Started : " + LocalDateTime.now().getSecond());
        }
    }

    public static void resetTimer() {
        if(TopDrawer.timer != null) {
            if(TopDrawer.timer.isAlive()) {
                System.out.println("Stopping Thread");
                TopDrawer.timer.stop();
            }
            if(Elements.topDrawer.isOpening()) {
                System.out.println("Closing TopDrawer");
                Elements.topDrawer.close();
            }
            System.out.println("Setting Timer to null");
            TopDrawer.timer = null;
        }
    }

    public static void setAnimation() {
        if(clock == null) {
            clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                Elements.time.setText(LocalDateTime.now().format(formatter));
            }), new KeyFrame(Duration.seconds(1)));
            clock.setCycleCount(Animation.INDEFINITE);
        }
        if(fadeIn == null) {
            fadeIn = new FadeTransition(Duration.millis(5000));
            fadeIn.setNode(Elements.time);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setCycleCount(1);
            fadeIn.setAutoReverse(false);
        }
        if(scaleUpTransition == null) {
            System.out.println("Setting scaleUpTransition");
            scaleUpTransition = new ScaleTransition(Duration.millis(250), Elements.time);
            scaleUpTransition.setByX(0.05);
            scaleUpTransition.setByY(0.05);
            scaleUpTransition.setCycleCount(1);
            scaleUpTransition.setAutoReverse(false);
            scaleUpTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    isTimeScaledUp = true;
                    if(isTimeTranslatedUp && Main.isMouseExited) {
                        scaleTranslateDown();
                    }
                }
            });
        }
        if(scaleDownTransition == null) {
            System.out.println("Setting scaleDownTransition");
            scaleDownTransition = new ScaleTransition(Duration.millis(250), Elements.time);
            scaleDownTransition.setByX(-0.05);
            scaleDownTransition.setByY(-0.05);
            scaleDownTransition.setCycleCount(1);
            scaleDownTransition.setAutoReverse(false);
            scaleDownTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    isTimeScaledUp = false;
                    if(!isTimeTranslatedUp && !Main.isMouseExited) {
                        scaleTranslateUp();
                    }
                }
            });
        }
        if(translateUpTransition == null) {
            System.out.println("Setting translateUpTransition");
            translateUpTransition = new TranslateTransition(Duration.millis(500), Elements.time);
            translateUpTransition.setByY(-10.0);
            translateUpTransition.setCycleCount(1);
            translateUpTransition.setAutoReverse(false);
            translateUpTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    isTimeTranslatedUp = true;
                    if(isTimeScaledUp && Main.isMouseExited) {
                        scaleTranslateDown();
                    }
                }
            });
        }
        if(translateDownTransition == null) {
            System.out.println("Setting translateDownTransition");
            translateDownTransition = new TranslateTransition(Duration.millis(500), Elements.time);
            translateDownTransition.setByY(10.0);
            translateDownTransition.setCycleCount(1);
            translateDownTransition.setAutoReverse(false);
            translateDownTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    isTimeTranslatedUp = false;
                    if(!isTimeScaledUp && !Main.isMouseExited) {
                        scaleTranslateUp();
                    }
                }
            });
        }
    }

    public static void scaleTranslateUp() {
        if(TopDrawer.isOpened && !isTimeScaledUp && !isTimeTranslatedUp) {
            System.out.println("Playing scaleTranslateUp");
            scaleUpTransition.play();
            translateUpTransition.play();
        }
    }

    public static void scaleTranslateDown() {
        if(TopDrawer.isOpened && isTimeScaledUp && isTimeTranslatedUp) {
            System.out.println("Playing scaleTranslateDown");
            scaleDownTransition.play();
            translateDownTransition.play();
        }
    }

    public static void reset() {
        scaleDownTransition.play();
        translateDownTransition.play();
    }
}
