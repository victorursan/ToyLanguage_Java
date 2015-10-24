package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

/**
 * Created by victor on 10/12/15.
 */
public class IfStmt implements IStmt{
    public Exp exp;
    public IStmt thenS;
    public IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toStr() {
        return "IF(" +  exp.toStr() + ")THEN(" + thenS.toStr()  + ")ELSE(" + elseS.toStr() + ")";
    }
}
