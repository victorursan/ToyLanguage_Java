package com.victorursan.Views.ViewControllers;

import com.victorursan.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by victor on 1/9/16.
 */
public class FirstMenuViewController extends AnchorPane implements Initializable {
    private Controller ctrl;

    public void setCtrl(Controller ctrl) {
        System.out.println("controller is set");
        this.ctrl = ctrl;
    }

    @FXML
    public CheckBox checkLog;

    @FXML
    public CheckBox checkSerialize;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void inputProgramTouched(ActionEvent actionEvent) {

    }

    public void oneStepTouched(ActionEvent actionEvent) {
    }

    public void allStepTouched(ActionEvent actionEvent) {
    }

    public void deserializeTouched(ActionEvent actionEvent) {
    }

    public void checkSerializeTouched(ActionEvent actionEvent) {
    }

    public void checkLogTouched(ActionEvent actionEvent) {

    }


}