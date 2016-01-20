package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/12/15.
 */
public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp expression) {
        exp = expression;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        IList<Integer> output = state.getOut();
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        output.add(getExp().eval(symTbl, heap));
        return null;
    }
}
