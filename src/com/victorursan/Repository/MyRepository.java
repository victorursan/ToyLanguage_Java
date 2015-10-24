package com.victorursan.Repository;

import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/24/15.
 */
public class MyRepository implements Repository {
    private PrgState prgStates[];
    private Integer prgNumber = 0;

    public MyRepository(PrgState[] prgStates) {
        this.prgStates = prgStates;
        this.prgNumber = prgStates.length;
    }

    @Override
    public PrgState getCrtProgram() {
        if (prgNumber > 0)
                return this.prgStates[0];
        return null;
    }
}


