package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public class ArithExp extends Exp{
    public Exp e1;
    public Exp e2;
    public String op;

    public ArithExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(Map tbl) {
        if (op.equals("+")) {
            return (e1.eval(tbl) + e2.eval(tbl));
        }
        if (op.equals("-")) {
            return (e1.eval(tbl) - e2.eval(tbl));
        }
        if (op.equals("*")) {
            return (e1.eval(tbl) * e2.eval(tbl));
        }
        if (op.equals("/")) {
            return (e1.eval(tbl) / e2.eval(tbl));
        }
        return 0;
    }

    @Override
    public String toStr() {
        return "(" + e1.toStr() + " " + op + " " + e2.toStr() + ")" ;
    }
}
