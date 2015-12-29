package com.victorursan.Models.Heap;

import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;

import java.io.Serializable;

/**
 * Created by victor on 11/22/15.
 */
public interface IHeap<T> extends Serializable {
    int add(T e);
    T get(int address) throws HashIndexOutOfBoundsException;
    void update(int address, T value) throws HashIndexOutOfBoundsException;
}
