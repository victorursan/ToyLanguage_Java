package com.victorursan.Views;

import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/24/15.
 */
public class Console {
    PrgState currentProgram;

    public Console() {
    }

    public void run() {
        if (currentProgram == null) {
            System.out.print("1. Input program");
        } else {
            System.out.print("1. Input program");
            System.out.print("2. One Step");
            System.out.print("3. All Step");
            System.out.print("4. Set printFlag");
        }

    }
}
