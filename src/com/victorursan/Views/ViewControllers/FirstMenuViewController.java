package com.victorursan.Views.ViewControllers;

import com.victorursan.Controller.Controller;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by victor on 1/9/16.
 */
public class FirstMenuViewController extends AnchorPane {
    private Controller ctrl;

    @FXML
    public CheckBox checkLog;

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
        checkLog.setSelected(ctrl.isLogFlag());
    }


    public void inputProgramTouched(ActionEvent actionEvent) throws IOException, EmptyRepositoryException {
        Stage primaryStage = new Stage();
        URL location = InputProgramViewController.class.getResource("InputProgramView.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        InputProgramViewController ctrl1 = fxmlLoader.getController();
        ctrl1.setCtrl(ctrl);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void oneStepTouched(ActionEvent actionEvent) throws IOException, EmptyRepositoryException {
        Stage primaryStage = new Stage();
        URL location = OneStepViewController.class.getResource("OneStepView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        OneStepViewController ctrl1 = fxmlLoader.getController();
        ctrl1.setCtrl(ctrl);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void allStepTouched(ActionEvent actionEvent) throws IOException, EmptyRepositoryException, InterruptedException {
        Stage primaryStage = new Stage();
        URL location = AllStepViewController.class.getResource("AllStepView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        AllStepViewController ctrl1 = fxmlLoader.getController();
        ctrl1.setCtrl(ctrl);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void deserializeTouched(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        URL location = DeserializeViewController.class.getResource("DeserializeView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        DeserializeViewController ctrl1 = fxmlLoader.getController();
        ctrl1.setCtrl(ctrl);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void checkLogTouched(ActionEvent actionEvent) {
        ctrl.setLogFlag(checkLog.isSelected());
    }

    public void serializedTouched(ActionEvent actionEvent) {
        ctrl.serializeProgramState();
    }
}