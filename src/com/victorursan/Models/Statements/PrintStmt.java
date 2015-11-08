package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

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
}
