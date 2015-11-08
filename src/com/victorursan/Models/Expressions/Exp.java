package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;

/**
 * Created by victor on 10/12/15.
 */

public interface Exp {
    Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException;
}

