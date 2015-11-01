package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */
public class ConstExp implements Exp {
    public Integer number;

    public ConstExp(Integer number) {
        this.number = number;
    }

    @Override
    public Integer eval(Map<String, Integer> tbl) {
        return number;
    }

    @Override
    public String toStr() {
        return " " + String.valueOf(number) + " ";
    }
}
