package com.victorursan.Views.ViewControllers;/**
 * Created by victor on 1/9/16.
 */

import com.victorursan.Controller.Controller;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import com.victorursan.Repository.MyRepository;
import com.victorursan.Repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, EmptyRepositoryException {
        Repository repo = new MyRepository();
        Controller ctrl = new Controller(repo);

        primaryStage.setTitle("Toy Language");

        URL location = FirstMenuViewController.class.getResource("FirstMenuView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        FirstMenuViewController ctrl1 = fxmlLoader.getController();
        ctrl1.setCtrl(ctrl);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
