package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Expressions.UninitializedVariableException;
import com.victorursan.Models.Heap.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 11/22/15.
 */
public class WriteHeapStmt implements IStmt {
    private String id;
    private Exp exp;

    public WriteHeapStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

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

    @Override
    public String toString() {
        return "writeHeap( " + id + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws NoSuchKeyException, DivisionByZeroException, UninitializedVariableException, HashIndexOutOfBoundsException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        heap.update(symTbl.get(getId()), getExp().eval(symTbl, heap));
        return null;
    }
}
