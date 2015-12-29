package com.victorursan.Models.Heap;

import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;

import java.util.HashMap;

/**
 * Created by victor on 11/22/15.
 */
public class MyLibraryHeap<T> implements IHeap<T> {
    private HashMap<Integer, T> elements;
    private int nextFree;

    public MyLibraryHeap() {
        elements = new HashMap<>();
        nextFree = 1;
    }

    @Override
    public int add(T e) {
        elements.put(nextFree, e);
        return nextFree++;
    }

    @Override
    public T get(int address) throws HashIndexOutOfBoundsException {
        if (elements.get(address) == null) {
            throw new HashIndexOutOfBoundsException();
        }
        return elements.get(address);
    }

    @Override
    public void update(int address, T value) throws HashIndexOutOfBoundsException {
        if (elements.get(address) == null) {
            throw new HashIndexOutOfBoundsException();
        }
        elements.put(address, value);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (Integer elem : elements.keySet()) {
            toPrint = elem.toString() + " -> " + elements.get(elem)  + "\n" + toPrint;
        }
        return toPrint;
    }
}
