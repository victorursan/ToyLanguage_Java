package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 1/22/16.
 */
public class AWaitStmt implements IStmt {
    private  String var;

    public AWaitStmt(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        if (state.getSymTable().containsKey(var)) {
            Integer foundIndex = state.getSymTable().get(var);
            if (state.getLatchTable().containsKey(foundIndex)) {
                if (state.getLatchTable().get(foundIndex) == 0) {
                    return null;
                } else {
                    state.getExeStack().push(this);
                }
            } else {
                throw new NoSuchKeyException();
            }
        } else {
            throw new NoSuchKeyException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "await(" + var +")";
    }
}
