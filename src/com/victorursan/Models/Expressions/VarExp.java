package com.victorursan.Models.Expressions;

import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;

/**
 * Created by victor on 10/12/15.
 */

public class VarExp implements Exp {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, NoSuchKeyException {
        if (tbl.containsKey(id)) return tbl.get(id);
        throw new UninitializedVariableException();
    }

    @Override
    public String toString() {
        return " " + id + " ";
    }
}
