package com.victorursan.Models.List;


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
    public T get(int index) {
        return elements.get(index);
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
        return " " + elements + " ";
    }
}
