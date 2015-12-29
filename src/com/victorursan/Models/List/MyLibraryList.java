package com.victorursan.Models.List;

import com.victorursan.Models.List.Exception.IndexOutOfBoundsException;

import java.util.ArrayList;

/**
 * Created by victor on 10/24/15.
 */
public class MyLibraryList<T> implements IList<T> {
    private ArrayList<T> elements;

    public MyLibraryList() {
        elements = new ArrayList<T>();
    }

    @Override
    public boolean add(T e) {
        return elements.add(e);
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < elements.size())
        return elements.get(index);
            throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (T elem : elements) {
            toPrint = elem.toString() + "\n" + toPrint;
        }
        return toPrint;
    }
}
