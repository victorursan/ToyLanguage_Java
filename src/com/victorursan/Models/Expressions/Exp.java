package com.victorursan.Models.Expressions;

import com.victorursan.Models.Heap.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Views.UnexpectedTypeException;

import java.io.Serializable;

/**
 * Created by victor on 10/12/15.
 */

public interface Exp extends Serializable {
    Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException;
}

