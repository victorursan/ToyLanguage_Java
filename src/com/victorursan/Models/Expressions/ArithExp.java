package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public class ArithExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public Exp getE1() {
        return e1;
    }

    public void setE1(Exp e1) {
        this.e1 = e1;
    }

    public Exp getE2() {
        return e2;
    }

    public void setE2(Exp e2) {
        this.e2 = e2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public ArithExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(Map<String, Integer> tbl) throws UninitializedVariableException{
        if (op.equals("+")) return (e1.eval(tbl) + e2.eval(tbl));
        if (op.equals("-")) return (e1.eval(tbl) - e2.eval(tbl));
        if (op.equals("*")) return (e1.eval(tbl) * e2.eval(tbl));
        if (op.equals("/")) return (e1.eval(tbl) / e2.eval(tbl));
        return 0;
    }

    @Override
    public String toStr() {
        return "(" + e1.toStr() + " " + op + " " + e2.toStr() + ")" ;
    }
}
