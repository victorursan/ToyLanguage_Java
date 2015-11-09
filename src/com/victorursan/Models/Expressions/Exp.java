package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Views.UnexpectedTypeException;

/**
 * Created by victor on 10/12/15.
 */

public interface Exp {
    Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException;
}

