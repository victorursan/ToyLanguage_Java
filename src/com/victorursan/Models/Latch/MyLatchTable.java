package com.victorursan.Models.Latch;

import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;

import java.util.HashMap;

/**
 * Created by victor on 1/22/16.
 */
public class MyLatchTable<V> implements Latch<Integer, V> {
    private HashMap<Integer, V> elements;
    private int nextFree;

    public MyLatchTable() {
        elements = new HashMap<>();
        nextFree = 1;
    }

    @Override
    public Integer add(V e) {
        elements.put(nextFree, e);
        return nextFree++;
    }

    @Override
    public synchronized V get(Integer key) throws HashIndexOutOfBoundsException {
        if (elements.get(key) == null) {
            throw new HashIndexOutOfBoundsException();
        }
        return elements.get(key);
    }

    @Override
    public boolean containsKey(Integer key) {
        return elements.get(key) != null;
    }

    @Override
    public synchronized void update(Integer key, V value) throws HashIndexOutOfBoundsException {
        if (elements.get(key) == null) {
            throw new HashIndexOutOfBoundsException();
        }
        elements.put(key, value);
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
