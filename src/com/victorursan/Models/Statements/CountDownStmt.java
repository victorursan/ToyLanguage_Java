package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 1/22/16.
 */
public class CountDownStmt implements IStmt {
    private  String var;

    public CountDownStmt(String var) {
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
                } else if(state.getLatchTable().get(foundIndex) > 0) {
                    state.getLatchTable().update(foundIndex, state.getLatchTable().get(foundIndex) - 1 );
                } else {
                    state.getExeStack().push(this);
                }
            } else {
                return null;
            }
        } else {
            throw new NoSuchKeyException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "CountDown(" + var +")";
    }
}
