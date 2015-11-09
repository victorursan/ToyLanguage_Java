package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;

/**
 * Created by victor on 11/9/15.
 */
public class LogicalExp implements Exp {
    private Exp e1;
    private Exp e2;
    private String op;

    public LogicalExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        if (op.equals("&&")) return (e1.eval(tbl) != 0  && e2.eval(tbl) != 0) ? 1 : 0;
        if (op.equals("||")) return (e1.eval(tbl) != 0 || e2.eval(tbl) != 0)? 1: 0;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + e1 + " " + op + " " + e2 + ")";
    }
}
