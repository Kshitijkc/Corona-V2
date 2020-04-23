package com.kshitijkc.resources;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;

import static com.kshitijkc.resources.Main.Elements.drawersStack;

public class TopDrawer {
    public static class Elements {
        public static StackPane topDrawerPane = null;
        public static AnchorPane topDrawerSticker = null;
        public static Label time = null;
        public static JFXDrawer topDrawer = null;
    }

    public static Thread timer = null;
    public static long timeOut = 4000;
    public static Timeline clock = null;
    public static FadeTransition fadeIn = null;
    public static boolean isOpened = false;
    public static ScaleTransition scaleUpTransition = null;
    public static ScaleTransition scaleDownTransition = null;
    public static TranslateTransition translateUpTransition = null;
    public static TranslateTransition translateDownTransition = null;

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
        if(scaleUpTransition == null) {
            System.out.println("Setting scaleUpTransition");
            scaleUpTransition = new ScaleTransition(Duration.millis(250), Elements.time);
            scaleUpTransition.setByX(0.05);
            scaleUpTransition.setByY(0.05);
            scaleUpTransition.setCycleCount(1);
            scaleUpTransition.setAutoReverse(false);
        }
        if(scaleDownTransition == null) {
            System.out.println("Setting scaleDownTransition");
            scaleDownTransition = new ScaleTransition(Duration.millis(250), Elements.time);
            scaleDownTransition.setByX(-0.05);
            scaleDownTransition.setByY(-0.05);
            scaleDownTransition.setCycleCount(1);
            scaleDownTransition.setAutoReverse(false);
        }
        if(translateUpTransition == null) {
            System.out.println("Setting translateUpTransition");
            translateUpTransition = new TranslateTransition(Duration.millis(500), Elements.time);
            translateUpTransition.setByY(-10.0);
            translateUpTransition.setCycleCount(1);
            translateUpTransition.setAutoReverse(false);
        }
        if(translateDownTransition == null) {
            System.out.println("Setting translateDownTransition");
            translateDownTransition = new TranslateTransition(Duration.millis(500), Elements.time);
            translateDownTransition.setByY(10.0);
            translateDownTransition.setCycleCount(1);
            translateDownTransition.setAutoReverse(false);
        }
    }

    public static void resetAnimation() {
        if(scaleUpTransition != null) {
            System.out.println("Stopping scaleUpTransition");
            scaleUpTransition.stop();
            scaleUpTransition = null;
        }
        if(scaleDownTransition != null) {
            System.out.println("Stopping scaleDownTransition");
            scaleDownTransition.stop();
            scaleDownTransition = null;
        }
        if(translateUpTransition != null) {
            System.out.println("Stopping translateUpTransition");
            translateUpTransition.stop();
            translateUpTransition = null;
        }
        if(translateDownTransition != null) {
            System.out.println("Stopping translateDownTransition");
            translateDownTransition.stop();
            translateDownTransition = null;
        }
    }

    public static void scaleTranslateUp() {
        if(TopDrawer.isOpened) {
            System.out.println("Playing scaleTranslateUp");
            scaleUpTransition.play();
            translateUpTransition.play();
        }
    }

    public static void scaleTranslateDown() {
        if(TopDrawer.isOpened) {
            System.out.println("Playing scaleTranslateDown");
            translateDownTransition.play();
            scaleDownTransition.play();
        }
    }
}
