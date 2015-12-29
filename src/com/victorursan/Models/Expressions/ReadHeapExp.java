package com.victorursan.Models.Expressions;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;

/**
 * Created by victor on 11/22/15.
 */
public class ReadHeapExp implements Exp{
    private String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
        Integer address = tbl.get(id);
        return heap.get(address);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "readHeap( " + id + ")";
    }
}
