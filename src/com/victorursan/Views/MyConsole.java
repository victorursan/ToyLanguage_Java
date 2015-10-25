package com.victorursan.Views;

import com.victorursan.Controller.Controller;
import com.victorursan.Controller.MyStmtExecException;
import com.victorursan.Models.Expressions.*;
import com.victorursan.Models.List.ArrayList;
import com.victorursan.Models.Map.ArrayDictionary;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.ArrayStack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.MyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by victor on 10/24/15.
 */
public class MyConsole {
    private Controller ctrl;
    private PrgState currentProgram;
    private Scanner scanner;

    public MyConsole() {
        scanner = new Scanner(System.in);
    }

    private void print(String message) {
        System.out.println(message);
    }

    private String readString(String message) {
        try {
            print(message);
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (IOException e) {
            print(e.getMessage());
        }
        return "";
    }

    private Integer readInteger(String message) {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            print("That is not a number");
        }
        return 0;
    }

    private void oneStep() {
        try {
            ctrl.oneStep(currentProgram);
        } catch (MyStmtExecException e) {
            print("Finished");
            currentProgram = null;
        } catch (UninitializedVariableException e) {
            print("A variable is not initialized");
        }
    }

    private void allStep() {
        try {
            ctrl.allStep(currentProgram);
        } catch (MyStmtExecException e) {
            print("Finished");
            currentProgram = null;
        } catch (UninitializedVariableException e) {
            print("A variable is not initialized");
        }
    }

    private void setPrintFlag() {
        print("Currently it is:");
        if (ctrl.printFlag) {
            print("On");
            print("1. Set Off");
        } else {
            print("Off");
            print("1. Set On");
        }
        print("2. Back");
        Integer opt = readInteger("Option: ");
        switch (opt) {
            case 1:
                ctrl.printFlag = !ctrl.printFlag;
                break;
            case 2:
                break;
            default:
                print("Invalid option, please try again");
                setPrintFlag();
                break;
        }

    }

    private ArithExp arithmeticalExpression() {
        print("Available operands: +, -, *, /");
        String opperand = readString("Operand: ");
        if (Arrays.asList(new String[]{"+", "-", "*", "/"}).contains(opperand)) {
            print("Left:");
            Exp left = inputExpression();
            print("Right:");
            Exp right = inputExpression();
            return new ArithExp(left, opperand, right);
        }
        print("invalid operand");
        return arithmeticalExpression();

    }

    private ConstExp constantExpression() {
        Integer number = readInteger("Number: ");
        return new ConstExp(number);
    }

    private VarExp variableExpression() {
        String id = readString("Variable id: ");
        return new VarExp(id);
    }


    private Exp inputExpression() {
        print("1. Arithmetical expression");
        print("2. Constant expression");
        print("3. Var expression");
        Exp expr;
        Integer opt = readInteger("Option: ");
        switch (opt) {
            case 1:
                expr = arithmeticalExpression();
                break;
            case 2:
                expr = constantExpression();
                break;
            case 3:
                expr = variableExpression();
                break;
            default:
                print("Invalid option, please try again");
                expr = inputExpression();
                break;

        }
        return expr;
    }

    private CompStmt compoundStatement() {
        print("Left side:");
        IStmt left = inputStatement();
        print("Right side:");
        IStmt right = inputStatement();
        return new CompStmt(left, right);
    }

    private AssignStmt assignmentStatement() {
        String name = readString("Var name:");
        print("Right side:");
        Exp exp = inputExpression();
        return new AssignStmt(name, exp);
    }

    private IfStmt ifStatement() {
        print("Expression:");
        Exp expression = inputExpression();
        print("Then Statement:");
        IStmt thenS = inputStatement();
        print("Else Statement:");
        IStmt elseS = inputStatement();
        return new IfStmt(expression, thenS, elseS);
    }

    private PrintStmt printStatement() {
        print("Expression:");
        Exp expression = inputExpression();
        return new PrintStmt(expression);
    }

    private IStmt inputStatement() {
        print("1. Compound statement");
        print("2. Assignment statement");
        print("3. If statement");
        print("4. Print statement");
        Integer opt = readInteger("Option: ");
        IStmt prg;
        switch (opt) {
            case 1:
                prg = compoundStatement();
                break;
            case 2:
                prg = assignmentStatement();
                break;
            case 3:
                prg = ifStatement();
                break;
            case 4:
                prg = printStatement();
                break;
            default:
                print("Invalid option, please try again");
                prg = inputStatement();
        }
        return prg;
    }

    private void inputProgram() {
        IStmt prgStatement = inputStatement();
        currentProgram = new PrgState(new ArrayStack(), new ArrayDictionary(), new ArrayList(), prgStatement);
        PrgState[] programs = {currentProgram};
        currentProgram.printState();
        ctrl = new Controller(new MyRepository(programs));
    }

    private void firstMenu() {
        print("1. Input program");
        print("2. One Step");
        print("3. All Step");
        print("4. Set printFlag");
        Integer opt = readInteger("Option: ");

        if (opt != 1 && currentProgram == null) {
            print("There is no program, please insert a program");
            firstMenu();
        } else {
            switch (opt) {
                case 1:
                    inputProgram();
                    firstMenu();
                    break;
                case 2:
                    oneStep();
                    firstMenu();
                    break;
                case 3:
                    allStep();
                    firstMenu();
                    break;
                case 4:
                    setPrintFlag();
                    firstMenu();
                    break;
                default:
                    print("Invalid option, please try again");
                    firstMenu();
                    break;
            }
        }
    }

    public void run() {
        firstMenu();
    }
}
