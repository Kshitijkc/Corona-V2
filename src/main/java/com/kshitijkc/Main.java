package com.kshitijkc;

import com.kshitijkc.components.AppConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    // Width 250
    // Height 425

    @Override
    public void start(Stage primaryStage) throws Exception {
        hideTaskbarIcon(primaryStage);

        Stage stage = setStage(primaryStage);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        setEvents(root, stage, scene);

        stage.setScene(scene);

        stage.show();
    }

    private void hideTaskbarIcon(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);
        primaryStage.setHeight(0);
        primaryStage.setWidth(0);
        primaryStage.show();
    }

    private Stage setStage(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //set Stage boundaries to visible bounds of the main screen
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setX(primaryScreenBounds.getWidth() * (1.0 - (250.0 / 1366.0) - (20.0 / 1366.0))); // 1 - 0.18301610541 - 0.01464128843 && 0.01464128843 = 20 / 1366
        stage.setY((primaryScreenBounds.getHeight() * (1.0 - (425.0 / 768.0)))/2.0); // (1 - 0.55338541666) / 2 && set it as the middle of the screen
        if(AppConfig.screenIndependent){
            stage.setWidth(primaryScreenBounds.getWidth() * (250.0 / 1366.0)); // 0.18301610541 = 250 / 1366
            stage.setHeight(primaryScreenBounds.getHeight() * (425.0 / 768.0)); // 0.55338541666 = 425 / 768
            AppConfig.currentHeight = stage.getHeight();
            AppConfig.currentWidth = stage.getWidth();
        }
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Corona");
        return stage;
    }

    private void setEvents(Parent root, final Stage stage, final Scene scene) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            scene.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            scene.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseReleased(event -> scene.setCursor(Cursor.HAND));
        root.setOnMouseEntered(event -> scene.setCursor(Cursor.HAND));
    }

    public static void main(String[] args) {
        launch(args);
    }

}