package com.victorursan.Views.ViewControllers;

import com.victorursan.Controller.Controller;
import com.victorursan.Models.Expressions.*;
import com.victorursan.Models.Heap.MyLibraryHeap;
import com.victorursan.Models.List.MyLibraryList;
import com.victorursan.Models.Map.MyLibraryDictionary;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.MyLibraryStack;
import com.victorursan.Models.Statements.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by victor on 1/10/16.
 */
public class InputProgramViewController extends AnchorPane {
    public TextArea txtProgram;
    private Controller ctrl;

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void newTouched(ActionEvent actionEvent) throws IOException {
        IStmt stmt = newStatement("First");
        List<PrgState> programs = new ArrayList<>();
        programs.add(new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryHeap<>(), new MyLibraryList<>(), new MyLibraryDictionary<>(), stmt));
        ctrl.setPrgList(programs);
        txtProgram.setText(stmt.toString());
    }

    private String newString(String content) throws IOException {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText(content);
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        throw new IOException();
    }

    private Exp newExpression(String content) throws IOException {
        String[] expressionsStirngs = {"Arithmetic Expression", "Constant Expression",
                "Variable Expression", "Binary Logical Expression", "Not Expression", "Relational Expression",
                "Read value", "Read Heap"};

        ChoiceDialog<String> expresionDialog = new ChoiceDialog<>(expressionsStirngs[0], expressionsStirngs);
        expresionDialog.setContentText(content);
        Optional<String> result = expresionDialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(expressionsStirngs[0])) {
                String operator = newString("Enter operator (+, -, *): ");
                Exp left = newExpression("Left Expression: ");
                Exp right = newExpression("Right Expression: ");
                return new ArithExp(left, operator, right);
            }
            if (choice.equals(expressionsStirngs[1])) {
                Integer value = Integer.valueOf(newString("Enter constant value: "));
                return new ConstExp(value);
            }
            if (choice.equals(expressionsStirngs[2])) {
                String name = newString("Name: ");
                return new VarExp(name);
            }
            if (choice.equals(expressionsStirngs[3])) {
                String operator = newString("Enter operator (&&, ||): ");
                Exp left = newExpression("Left Expression: ");
                Exp right = newExpression("Right Expression: ");
                return new LogicalExp(left, operator,right);
            }
            if (choice.equals(expressionsStirngs[4])) {
                Exp right = newExpression("Expression: ");
                return new NotExp(right);
            }
            if (choice.equals(expressionsStirngs[5])) {
                String operator = newString("Enter operator (<, <=, !=, ==, >=, >): ");
                Exp left = newExpression("Left Expression: ");
                Exp right = newExpression("Right Expression: ");
                return new RelationalExp(left, operator, right);
            }
            if (choice.equals(expressionsStirngs[6])) {
                return new ReadExp();
            }
            if (choice.equals(expressionsStirngs[7])) {
                String name = newString("Name: ");
                return new ReadHeapExp(name);
            }
        }
        throw new IOException();
    }

    private IStmt newStatement(String content) throws IOException {
        String[] statementsStrings = {"Compound Statement", "Assign Statement", "Print Statement",
                "If Statement", "While Statement", "IfThen Statement", "Switch Statement", "Skip Statement",
                "New Statement", "Write Heap", "Fork"};
        ChoiceDialog<String> stmtDialog = new ChoiceDialog<>(statementsStrings[0], statementsStrings);
        stmtDialog.setContentText(content);
        Optional<String> result = stmtDialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(statementsStrings[0])) {
                IStmt first = newStatement("First Statement:");
                IStmt second = newStatement("Second Statement:");
                return new CompStmt(first, second);
            }
            if (choice.equals(statementsStrings[1])) {
                String name = newString("Name: ");
                Exp value = newExpression("Assigned value: ");
                return new AssignStmt(name, value);
            }
            if (choice.equals(statementsStrings[2])) {
                Exp expression = newExpression("Expression: ");
                return new PrintStmt(expression);
            }
            if (choice.equals(statementsStrings[3])) {
                Exp condition = newExpression("Condition: ");
                IStmt thenStatement = newStatement("Then branch: ");
                IStmt elseStatement = newStatement("Else branch: ");
                return new IfStmt(condition, thenStatement, elseStatement);
            }
            if (choice.equals(statementsStrings[4])) {
                Exp condition = newExpression("Condition: ");
                IStmt body = newStatement("Body: ");
                return new WhileStmt(condition, body);
            }
            if (choice.equals(statementsStrings[5])) {
                Exp condition = newExpression("Condition: ");
                IStmt thenStatement = newStatement("Then branch: ");
                return new IfThenStmt(condition, thenStatement);
            }
            if (choice.equals(statementsStrings[6])) {
                Exp condition = newExpression("Condition: ");
                Exp case1Expression = newExpression("Case 1 Expression:");
                IStmt case1Statement = newStatement("Case 1 Statement:");
                Exp case2Expression = newExpression("Case 2 Expression:");
                IStmt case2Statement = newStatement("Case 2 Statement:");
                IStmt defaultStatement = newStatement("Default Statement:");
                return new SwitchStmt(condition, case1Expression, case1Statement, case2Expression, case2Statement, defaultStatement);
            }
            if (choice.equals(statementsStrings[7])) {
                return new SkipStmt();
            }
            if (choice.equals(statementsStrings[8])) {
                String name = newString("Name: ");
                Exp expression = newExpression("Expression: ");
                return new NewStmt(name, expression);
            }
            if (choice.equals(statementsStrings[9])) {
                String name = newString("Name: ");
                Exp expression = newExpression("Expression: ");
                return new WriteHeapStmt(name, expression);
            }
            if (choice.equals(statementsStrings[10])) {
                return new ForkStmt(newStatement("Statement: "));
            }
        }
        throw new IOException();
    }


    public void backTouched(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
