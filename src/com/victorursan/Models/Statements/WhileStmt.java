package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 11/3/15.
 */
public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt stmt) {
        this.exp = e;
        this.stmt = stmt;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void settExp(Exp exp) {
        this.exp = exp;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "While( " + exp.toString() + ") { " + stmt.toString() + " }";
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        if (getExp().eval(symTbl, heap) != 0) {
            state.getExeStack().push(this);
            state.getExeStack().push(this.getStmt());
        }
        return state;
    }
}