package com.victorursan.Views;

import com.victorursan.Controller.Controller;
import com.victorursan.Controller.MyStmtExecException;
import com.victorursan.Models.Expressions.*;
import com.victorursan.Models.List.MyLibraryList;
import com.victorursan.Models.Map.MyLibraryDictionary;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.MyLibraryStack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.EmptyRepositoryException;
import com.victorursan.Repository.MyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by victor on 10/24/15.
 */
public class MyConsole {
    private Controller ctrl;
    private PrgState currentProgram;

    public MyConsole() {

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

    private Integer readInteger(String message) throws UnexpectedTypeException {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            throw new UnexpectedTypeException();
        }
    }

    private void oneStep() {
        try {
            ctrl.oneStep();
        } catch (MyStmtExecException e) {
            print("Finished");
            currentProgram = null;
        } catch (UninitializedVariableException e) {
            print("A variable is not initialized");
        } catch (NoSuchKeyException e) {
            print("No such Variable");
        } catch (DivisionByZeroException e) {
            print("Division by zero");
        } catch (EmptyRepositoryException e) {
            print("No program state ");
        }
    }

    private void allStep() {
        try {
            ctrl.allStep();
        } catch (MyStmtExecException e) {
            print("Finished");
            currentProgram = null;
        } catch (UninitializedVariableException e) {
            print("A variable is not initialized");
        } catch (NoSuchKeyException e) {
            print("No such Variable");
        } catch (DivisionByZeroException e) {
            print("Division by zero");
        } catch (EmptyRepositoryException e) {
            print("No program state ");
        }
    }


    private void setPrintFlag() throws UnexpectedTypeException {
        print("Currently it is:");
        if (ctrl.isPrintFlag()) {
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
                ctrl.setPrintFlag(!ctrl.isPrintFlag());
                break;
            case 2:
                break;
            default:
                print("Invalid option, please try again");
                setPrintFlag();
                break;
        }

    }

    private ArithExp arithmeticalExpression() throws UnexpectedTypeException {
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

    private ConstExp constantExpression() throws UnexpectedTypeException {
        Integer number = readInteger("Number: ");
        return new ConstExp(number);
    }

    private VarExp variableExpression() {
        String id = readString("Variable id: ");
        return new VarExp(id);
    }

    private RelationalExp relationalExpression() throws UnexpectedTypeException {
        print("Available operands: <, <=, ==, !=, >, >=");
        String opperand = readString("Operand: ");
        if (Arrays.asList(new String[]{"<", "<=", "==", "!=", ">", ">="}).contains(opperand)) {
            print("Left:");
            Exp left = inputExpression();
            print("Right:");
            Exp right = inputExpression();
            return new RelationalExp(left, opperand, right);
        }
        print("invalid operand");
        return relationalExpression();
    }

    private Exp inputExpression() throws UnexpectedTypeException {
        print("1. Arithmetical expression");
        print("2. Constant expression");
        print("3. Var expression");
        print("4. Relational expression");

        try {
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
                case 4:
                    expr = relationalExpression();
                    break;
                default:
                    print("Invalid option, please try again");
                    expr = inputExpression();
                    break;

            }
            return expr;
        } catch (UnexpectedTypeException e) {
            print("Invalid option, please try again");
            return inputExpression();
        }
    }

    private CompStmt compoundStatement() throws UnexpectedTypeException {
        print("Left side:");
        IStmt left = inputStatement();
        print("Right side:");
        IStmt right = inputStatement();
        return new CompStmt(left, right);
    }

    private AssignStmt assignmentStatement() throws UnexpectedTypeException {
        String name = readString("Var name:");
        print("Right side:");
        Exp exp = inputExpression();
        return new AssignStmt(name, exp);
    }

    private IfStmt ifStatement() throws UnexpectedTypeException {
        print("Expression:");
        Exp expression = inputExpression();
        print("Then Statement:");
        IStmt thenS = inputStatement();
        print("Else Statement:");
        IStmt elseS = inputStatement();
        return new IfStmt(expression, thenS, elseS);
    }

    private WhileStmt whileStatement() throws UnexpectedTypeException {
        print("Expression:");
        Exp expression = inputExpression();
        print("Statement:");
        IStmt statement = inputStatement();
        return new WhileStmt(expression, statement);
    }

    private PrintStmt printStatement() throws UnexpectedTypeException {
        print("Expression:");
        Exp expression = inputExpression();
        return new PrintStmt(expression);
    }


    private IStmt inputStatement() {
        print("1. Compound statement");
        print("2. Assignment statement");
        print("3. If statement");
        print("4. Print statement");
        print("5. While statement");
        try {
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
                case 5:
                    prg = whileStatement();
                    break;
                default:
                    print("Invalid option, please try again");
                    prg = inputStatement();
            }

            return prg;
        } catch (UnexpectedTypeException e) {
            print("Invalid option, please try again");
            return inputStatement();
        }
    }

    private void inputProgram() throws EmptyRepositoryException {
        IStmt prgStatement = inputStatement();
                //new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2), "-", new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        currentProgram = new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryList<>(), prgStatement);
        MyLibraryList<PrgState> programs = new MyLibraryList<>();
        programs.add(currentProgram);
        print(currentProgram.printState());
        ctrl = new Controller(new MyRepository(programs));
    }

    private void firstMenu() {
        print("1. Input program");
        print("2. One Step");
        print("3. All Step");
        print("4. Set printFlag");
        try {
            Integer opt = readInteger("Option: ");
            if (opt != 1 && currentProgram == null) {
                print("There is no program, please insert a program");
            } else {
                switch (opt) {
                    case 1:
                        inputProgram();
                        break;
                    case 2:
                        oneStep();
                        break;
                    case 3:
                        allStep();
                        break;
                    case 4:
                        setPrintFlag();
                        break;
                    default:
                        print("Invalid option, please try again");
                        break;
                }
            }
            firstMenu();
        } catch (UnexpectedTypeException e) {
            print("Invalid option, please insert a number");
            firstMenu();
        } catch (EmptyRepositoryException e) {
            print("No program added");
        }
    }

    public void run() {
        firstMenu();
    }
}
