package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

/**
 * Created by victor on 10/12/15.
 */
public class PrintStmt implements IStmt{
    public Exp exp;

    public PrintStmt(Exp expression) {
        exp =  expression;
    }

    @Override
    public String toStr() {
        return "print(" + exp.toStr() + ")";
    }
}
