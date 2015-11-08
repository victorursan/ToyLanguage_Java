package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

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
    public String toStr() {
        return "While( " + exp.toStr() + ") { " + stmt.toStr() + " }";
    }
}