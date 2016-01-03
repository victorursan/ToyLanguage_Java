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
 * Created by victor on 11/22/15.
 */
public class NewStmt implements IStmt {
    private String id;
    private Exp exp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public NewStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return "new( " + id + ", " + exp.toString() + ") ";
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException,NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        symTbl.put(getId(), heap.add(getExp().eval(symTbl, heap)));
        return state;
    }
}
