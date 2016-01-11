package com.victorursan.Views.ViewControllers;

import com.victorursan.Controller.Controller;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * Created by victor on 1/9/16.
 */
public class OneStepViewController  extends AnchorPane {
    private Controller ctrl;

    public TextArea txtView;

    public void setCtrl(Controller ctrl) throws EmptyRepositoryException, InterruptedException {
        this.ctrl = ctrl;
        this.ctrl.oneStep();
        txtView.setText(ctrl.getPrgList().toString());
    }

    public void oneStepTouched(ActionEvent actionEvent) throws EmptyRepositoryException, InterruptedException {
        ctrl.oneStep();
        txtView.setText(ctrl.getPrgList().toString());
    }

    public void backTouched(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

}
