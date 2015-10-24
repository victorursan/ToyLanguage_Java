package com.victorursan.Repository;

import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/12/15.
 */
public class Repository {
    PrgState programStates[];

    public Repository(PrgState[] programStates) {
        this.programStates = programStates;
    }

    public PrgState getCrtProgram() {
        return programStates[0];
    }
}
