package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.Exception.EmptyStackException;

/**
 * Created by victor on 1/20/16.
 */
public class ReturnStmt implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        try {
            state.getFullSymTable().pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString() {
        return "return";
    }


}
