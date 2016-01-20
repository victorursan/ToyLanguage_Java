package com.victorursan.Models.Statements;

import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 11/9/15.
 */
public class SkipStmt implements IStmt {
    @Override
    public String toString() {
        return "SKIP";
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

}
