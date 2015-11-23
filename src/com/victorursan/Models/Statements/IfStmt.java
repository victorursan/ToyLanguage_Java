package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/12/15.
 */
public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenS() {
        return thenS;
    }

    public IStmt getElseS() {
        return elseS;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    public void setElseS(IStmt elseS) {
        this.elseS = elseS;
    }

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
//        IfStmt crtStmt1 = (IfStmt) crtStmt;
//        IMap<String, Integer> symTbl = crtPrgState.getSymTable();
//        IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
//        if (crtStmt1.getExp().eval(symTbl, heap) != 0) {
//            stk.push(crtStmt1.getThenS());
//        } else {
//            stk.push(crtStmt1.getElseS());
//        }
        return state;
    }
}
