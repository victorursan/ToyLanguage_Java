package com.victorursan.Models.Heap;

import java.io.Serializable;

/**
 * Created by victor on 11/22/15.
 */
public interface IHeap<T> extends Serializable {
    int add(T e);
    T get(int index) throws HashIndexOutOfBoundsException;
    void update(int index, T value) throws HashIndexOutOfBoundsException;
}
