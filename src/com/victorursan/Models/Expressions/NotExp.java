package com.victorursan.Models.Expressions;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;

/**
 * Created by victor on 11/9/15.
 */
public class NotExp implements Exp {
    private Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
        return exp.eval(tbl, heap) == 0 ? 1: 0;
    }

    @Override
    public String toString() {
        return "!(" + exp + ")";
    }
}
