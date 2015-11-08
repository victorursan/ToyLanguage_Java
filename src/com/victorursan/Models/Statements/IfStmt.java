package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

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
}
