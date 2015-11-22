package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exp;

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
}
