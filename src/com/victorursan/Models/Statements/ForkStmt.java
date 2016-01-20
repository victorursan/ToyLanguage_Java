package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.MyLibraryDictionary;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Stack.MyLibraryStack;

/**
 * Created by victor on 1/2/16.
 */
public class ForkStmt implements IStmt {
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }
    public IStmt getStmt() {
        return stmt;
    }
    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        IStack<IStmt> newStack = new MyLibraryStack<>();
        IStack<IMap<String, Integer>> cloneSymTbl = new MyLibraryStack<>((MyLibraryStack<IMap<String, Integer>>)state.getFullSymTable());
        return new PrgState(newStack, cloneSymTbl, state.getHeapTable(), state.getOut(), state.getProcTable(), stmt);
    }

    @Override
    public String toString() {
        return "fork (" + stmt.toString() + ")";
    }
}
