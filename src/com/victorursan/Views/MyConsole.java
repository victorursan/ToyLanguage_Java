package com.victorursan.Views;

import com.victorursan.Controller.Controller;
import com.victorursan.Models.Expressions.*;
import com.victorursan.Models.Heap.MyLibraryHeap;
import com.victorursan.Models.List.Exception.IndexOutOfBoundsException;
import com.victorursan.Models.List.MyLibraryList;
import com.victorursan.Models.Map.MyLibraryDictionary;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.MyLibraryStack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import com.victorursan.Repository.MyRepository;
import com.victorursan.Repository.Repository;
import com.victorursan.Views.Exception.UnexpectedTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by victor on 10/24/15.
 */
public class MyConsole {
    private Controller ctrl;
//    private PrgState currentProgram;

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
            print(ctrl.getPrgList().toString());
        } catch (EmptyRepositoryException e) {
            print("No program state ");
        } catch (InterruptedException e) {
            print("Something went wrong");
        }
    }

    private void allStep() {
        try {
            ctrl.allStep();
            print(ctrl.getProgramsOutput());
        } catch (InterruptedException e) {
            print("Something went wrong");
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

    private Exp logicalExpression() throws UnexpectedTypeException {
        print("Available operands: !, ||, &&");
        String opperand = readString("Operand: ");
        if (opperand.equals("!")) {
            print("Expression:");
            Exp exp = inputExpression();
            return new NotExp(exp);
        } else if (Arrays.asList(new String[]{"||", "&&"}).contains(opperand)) {
            print("Left:");
            Exp left = inputExpression();
            print("Right:");
            Exp right = inputExpression();
            return new LogicalExp(left, opperand, right);
        }
        print("invalid operand");
        return relationalExpression();
    }

    private ReadExp readExpression() throws UnexpectedTypeException {
        return new ReadExp();
    }

    private Exp inputExpression() throws UnexpectedTypeException {
        print("1. Arithmetical expression");
        print("2. Constant expression");
        print("3. Var expression");
        print("4. Relational expression");
        print("5. Logical expression");
        print("6. Read expression");
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
                case 5:
                    expr = logicalExpression();
                    break;
                case 6:
                    expr = readExpression();
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

    private SkipStmt skipStatements() throws UnexpectedTypeException {
        return new SkipStmt();
    }

    private PrintStmt printStatement() throws UnexpectedTypeException {
        print("Expression:");
        Exp expression = inputExpression();
        return new PrintStmt(expression);
    }

    private SwitchStmt switchStatement() throws UnexpectedTypeException {
        String variableName = readString("Variable name");
        Exp expr = new VarExp(variableName);
        print("Case 1 expression:");
        Exp expCase1 = inputExpression();
        print("Case 1 Statement:");
        IStmt case1 = inputStatement();
        print("Case 2 expression:");
        Exp expCase2 = inputExpression();
        print("Case 2 Statement:");
        IStmt case2 = inputStatement();
        print("Default case Statement:");
        IStmt caseDefault = inputStatement();

        return new SwitchStmt(expr, expCase1, case1, expCase2, case2, caseDefault);
    }

    private IfThenStmt ifThenStatement() throws UnexpectedTypeException {
        print("Expression:");
        Exp expression = inputExpression();
        print("Then Statement:");
        IStmt thenS = inputStatement();
        return new IfThenStmt(expression, thenS);
    }

    private NewStmt newStatement() throws UnexpectedTypeException {
        String name = readString("Var name:");
        print("Right side:");
        Exp exp = inputExpression();
        return new NewStmt(name, exp);
    }

    private WriteHeapStmt writeHeapStatement() throws UnexpectedTypeException {
        String name = readString("Var name:");
        print("Right side:");
        Exp exp = inputExpression();
        return new WriteHeapStmt(name, exp);
    }

    private IStmt inputStatement() {
        print("1. Compound statement");
        print("2. Assignment statement");
        print("3. If statement");
        print("4. Print statement");
        print("5. While statement");
        print("6. Skip statement");
        print("7. Switch statement");
        print("8. If then statement");
        print("9. New statement");
        print("10. Write Heap statement");
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
                case 6:
                    prg = skipStatements();
                    break;
                case 7:
                    prg = switchStatement();
                    break;
                case 8:
                    prg = ifThenStatement();
                    break;
                case 9:
                    prg = newStatement();
                    break;
                case 10:
                    prg = writeHeapStatement();
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

    private void inputProgram() throws EmptyRepositoryException, IndexOutOfBoundsException {
//        IStmt prgStatement = new CompStmt(new NewStmt("a", new ConstExp(10)), new CompStmt(new WriteHeapStmt("a", new ConstExp(4)),  new CompStmt(new AssignStmt("b", new ConstExp(1)), new PrintStmt(new ReadHeapExp("b")))));
//                //new CompStmt(new AssignStmt("a", new ArithExp(new ReadExp(), "-", new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
//                //inputStatement();
        IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("a", new ConstExp(22));
        IStmt st3 = new AssignStmt("v", new ConstExp(32));
        IStmt st4 = new PrintStmt(new VarExp("v"));
        IStmt st5 = new PrintStmt(new ReadHeapExp("a"));
        IStmt st8 = new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), new CompStmt(st3, new CompStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExp("v"));
        IStmt st7 = new PrintStmt(new ReadHeapExp("a"));
        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6,new CompStmt (st7, new CompStmt(new SkipStmt(), new CompStmt(new SkipStmt(), new SkipStmt() )))))));

        List<PrgState> programs = new ArrayList<>();

        programs.add(new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryHeap<>(),new MyLibraryList<>(), prgStatement));
        print(programs.toString());
        Repository repo = new MyRepository(programs);

        ctrl = new Controller(repo);
        ctrl.serializeProgramState();
    }

    private void lastProgramState() throws EmptyRepositoryException, IOException, IndexOutOfBoundsException {
        Repository repo = new MyRepository();
                repo.deserializePrgStatet();
        ctrl = new Controller(repo);
    }

    private void setLogFlag() throws UnexpectedTypeException {
        print("Currently it is:");
        if (ctrl.isLogFlag()) {
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
                ctrl.setLogFlag(!ctrl.isLogFlag());
                break;
            case 2:
                break;
            default:
                print("Invalid option, please try again");
                setLogFlag();
                break;
        }
    }

    private void firstMenu() {
        print("1. Input program");
        print("2. One Step");
        print("3. All Step");
        print("4. Set printFlag");
        print("5. Last program state");
        print("6. Set logFlag");
        try {
            Integer opt = readInteger("Option: ");
            if (opt != 1 && opt != 5 && ctrl == null) {
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
                    case 5:
                        lastProgramState();
                        break;
                    case 6:
                        setLogFlag();
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
        } catch (EmptyRepositoryException| IndexOutOfBoundsException e) {
            print("No program added");
        } catch (IOException e) {
            print("No previous program");
            firstMenu();
        }
    }


    public void run() {
        firstMenu();
    }
}
