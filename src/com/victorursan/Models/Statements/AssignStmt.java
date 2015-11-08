package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

/**
 * Created by victor on 10/12/15.
 */
public class AssignStmt implements IStmt{
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

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toStr() {
        return id + "=" + exp.toStr();
    }
}
