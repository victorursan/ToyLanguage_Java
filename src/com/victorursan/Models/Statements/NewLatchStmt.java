package com.victorursan.Models.Statements;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 1/22/16.
 */
public class NewLatchStmt implements IStmt {
    private String varExp;
    private Integer latchCount;

    public NewLatchStmt(String varExp, Integer latchCount) {
        this.varExp = varExp;
        this.latchCount = latchCount;
    }

    public String getVarExp() {
        return varExp;
    }

    public void setVarExp(String varExp) {
        this.varExp = varExp;
    }

    public Integer getLatchCount() {
        return latchCount;
    }

    public void setLatchCount(Integer latchCount) {
        this.latchCount = latchCount;
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        synchronized (state.getLatchTable()) {
            if (state.getSymTable().containsKey(varExp)) {
                state.getSymTable().put(varExp, state.getLatchTable().add(latchCount));
            } else {
                state.getSymTable().put(varExp, state.getLatchTable().add(latchCount));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "newLatch(" + varExp +", " + latchCount.toString() + ")";
    }

}
