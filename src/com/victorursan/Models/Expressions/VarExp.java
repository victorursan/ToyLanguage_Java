package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public class VarExp extends Exp {
    public String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public int eval(Map tbl) {
        if (tbl.get(id) instanceof Integer) {
            return (Integer) tbl.get(id);
        }
        return 0;
    }

    @Override
    public String toStr() {
        return " " + id + " ";
    }
}
