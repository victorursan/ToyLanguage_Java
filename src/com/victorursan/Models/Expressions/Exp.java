package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.Map;

/**
 * Created by victor on 10/12/15.
 */

public interface Exp {
    Integer eval(Map<String, Integer> tbl) throws UninitializedVariableException;
    String toStr();
}

