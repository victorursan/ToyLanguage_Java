package com.victorursan.Views;

import com.victorursan.Controller.Controller;
import com.victorursan.Controller.MyStmtExecException;
import com.victorursan.Models.ProgramState.PrgState;

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
        System.out.println(message + "\n");
    }

    private String readString(String message) {
        print(message);
        return  scanner.nextLine();
    }

    private Integer readInteger(String message) {
        print(message);
        return scanner.nextInt();

    }

    private void oneStep() {
        try {
            ctrl.oneStep(currentProgram);
        } catch (MyStmtExecException e) {}
    }

    private void allStep() {
        ctrl.allStep(currentProgram);
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
                setPrintFlag();
                break;
        }

    }

    private void inputProgram() {

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
            return;
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
                    break;
                case 4:
                    setPrintFlag();
                    firstMenu();
                    break;
                default:
                    firstMenu();
                    break;
            }
        }
    }

    public void run() {
        firstMenu();
    }
}
