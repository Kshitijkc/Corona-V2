package com.kshitijkc.resources;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

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
                        System.out.println(Elements.topDrawer.isOpened());
                        drawersStack.toggle(Elements.topDrawer);
                        System.out.println(Elements.topDrawer.isOpening());
                    });
                } catch (InterruptedException e) {
                    System.out.println("Timer Interrupted");
                }
            });
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
                System.out.println("Thread Stopped");
            }
            if(Elements.topDrawer.isOpening())
                Elements.topDrawer.close();
            System.out.println("Setting Timer to null");
            TopDrawer.timer = null;
        }
    }
}
