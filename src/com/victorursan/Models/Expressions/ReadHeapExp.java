package com.victorursan.Models.Expressions;

import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;

/**
 * Created by victor on 11/22/15.
 */
public class ReadHeapExp implements Exp{
    private String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        return null;
    }

    public String getId() {
        return id;
    }
}
