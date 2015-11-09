package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;

/**
 * Created by victor on 11/9/15.
 */
public class NotExp implements Exp {
    private Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        return exp.eval(tbl) == 0 ? 1: 0;
    }

    @Override
    public String toString() {
        return "!(" + exp + ")";
    }
}
