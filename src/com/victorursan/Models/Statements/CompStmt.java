package com.victorursan.Models.Statements;

/**
 * Created by victor on 10/12/15.
 */
public class CompStmt implements IStmt {
    public IStmt first;
    public IStmt second;

    public CompStmt(IStmt left, IStmt right) {
        first = left;
        second = right;
    }

    @Override
    public String toStr() {
        return "(" + first.toStr() + ";" + second.toStr() + ")";
    }
}
