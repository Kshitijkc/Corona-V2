package com.kshitijkc.controllers;

import com.jfoenix.controls.JFXDrawersStack;
import com.kshitijkc.components.LeftDrawer;
import com.kshitijkc.components.Dashboard;
import com.kshitijkc.components.TopDrawer;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public GridPane indicator;
    public JFXDrawersStack drawersStack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dashboard.Elements.indicator = indicator;
        Dashboard.Elements.drawersStack = drawersStack;

        Dashboard.configDrawerStack(this);
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
