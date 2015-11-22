package com.victorursan.Models.Heap;

import java.util.Vector;

/**
 * Created by victor on 11/22/15.
 */
public class MyLibraryHeap<T> implements IHeap<T> {
    private Vector<T> elements;
    private int nextFree;

    public MyLibraryHeap() {
        this.elements = new Vector<>(10, 10);
        nextFree = 0;
    }

    @Override
    public int add(T e) {
        elements.add(nextFree, e);
        return nextFree++;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException{
        return elements.get(index);
    }

    @Override
    public void update(int index, T value) throws ArrayIndexOutOfBoundsException {
        elements.set(index, value);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (T elem : elements) {
            toPrint = elements.indexOf(elem) + "->" + elem + "\n" + toPrint;
        }
        return toPrint;
    }
}
