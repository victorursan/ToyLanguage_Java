package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public class ArithExp extends Exp{
    public Exp e1;
    public Exp e2;
    public Character op;

    @Override
    public int eval(Map tbl) {
        if (op == '+') {
            return (e1.eval(tbl) + e2.eval(tbl));
        }
        if (op == '-') {
            return (e1.eval(tbl) - e2.eval(tbl));
        }
        if (op == '*') {
            return (e1.eval(tbl) * e2.eval(tbl));
        }
        if (op == '/') {
            return (e1.eval(tbl) / e2.eval(tbl));
        }
        return 0;
    }

    @Override
    public String toStr() {
        return "(" + e1.toStr() + " " + op + " " + e2.toStr() + ")" ;
    }
}
