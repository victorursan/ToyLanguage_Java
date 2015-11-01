package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public class VarExp implements Exp {
    public String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(Map<String, Integer> tbl) throws UninitializedVariableException {
        if (tbl.containsKey(id)) return tbl.get(id);
        else throw new UninitializedVariableException();
    }

    @Override
    public String toStr() {
        return " " + id + " ";
    }
}
