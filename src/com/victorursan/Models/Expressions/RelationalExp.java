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
public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
        if (op.equals("<"))  return (e1.eval(tbl, heap) < e2.eval(tbl, heap))? 1 : 0;
        if (op.equals("<=")) return (e1.eval(tbl, heap) <= e2.eval(tbl, heap))? 1 : 0;
        if (op.equals("==")) return (e1.eval(tbl, heap).equals(e2.eval(tbl, heap)))? 1 : 0;
        if (op.equals("!=")) return (!e1.eval(tbl, heap).equals(e2.eval(tbl, heap)))? 1 : 0;
        if (op.equals(">"))  return (e1.eval(tbl, heap) > e2.eval(tbl, heap))? 1 : 0;
        if (op.equals(">=")) return (e1.eval(tbl, heap) >= e2.eval(tbl, heap))? 1 : 0;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + e1 + " " + op + " " + e2 + ")";
    }

}