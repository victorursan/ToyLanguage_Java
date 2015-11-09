package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;

/**
 * Created by victor on 11/9/15.
 */
public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        if (op.equals("<")) return (e1.eval(tbl) < e2.eval(tbl))? 1 : 0;
        if (op.equals("<=")) return (e1.eval(tbl) <= e2.eval(tbl))? 1 : 0;
        if (op.equals("==")) return (e1.eval(tbl).equals(e2.eval(tbl)))? 1 : 0;
        if (op.equals("!=")) return (!e1.eval(tbl).equals(e2.eval(tbl)))? 1 : 0;
        if (op.equals(">")) return (e1.eval(tbl) > e2.eval(tbl))? 1 : 0;
        if (op.equals(">=")) return (e1.eval(tbl) >= e2.eval(tbl))? 1 : 0;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + e1 + " " + op + " " + e2 + ")";
    }

}