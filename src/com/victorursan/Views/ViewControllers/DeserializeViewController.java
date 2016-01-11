package com.victorursan.Views.ViewControllers;

import com.victorursan.Controller.Controller;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by victor on 1/11/16.
 */
public class DeserializeViewController extends AnchorPane {
    private Controller ctrl;
    public TextArea txtArea;

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
        try {
            this.ctrl.deserializePrgStatet();
            txtArea.setText(ctrl.getPrgList().toString());
        } catch (IOException e) {
            System.out.print("something went wrong");
        } catch (EmptyRepositoryException e) {
            System.out.print("something else went wrong");
        }

    }

    public void backButtonTouched(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
