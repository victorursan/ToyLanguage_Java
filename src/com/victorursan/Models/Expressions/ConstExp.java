package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;

/**
 * Created by victor on 10/12/15.
 */
public class ConstExp implements Exp {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ConstExp(Integer number) {
        this.number = number;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl) {
        return number;
    }

    @Override
    public String toString() {
        return " " + String.valueOf(number) + " ";
    }
}
