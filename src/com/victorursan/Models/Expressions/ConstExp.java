package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */
public class ConstExp extends Exp {
    public int number;

    public ConstExp(int number) {
        this.number = number;
    }

    @Override
    public int eval(Map tbl) {
        return number;
    }

    @Override
    public String toStr() {
        return " " + String.valueOf(number) + " ";
    }
}
