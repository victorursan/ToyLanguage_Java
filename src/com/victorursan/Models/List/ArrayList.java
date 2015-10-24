package com.victorursan.Models.List;

/**
 * Created by victor on 10/24/15.
 */
public class ArrayList implements  List {
    private Object[] elements;
    private int nrElements;

    public ArrayList() {
        elements = new Object[10];
        nrElements = 0;
    }

    private void resize() {
        Object[] tmpKeys = new Object[elements.length * 2];
        System.arraycopy(elements, 0, tmpKeys, 0, elements.length);
        elements = tmpKeys;
    }

    @Override
    public boolean add(Object e) {
        if (nrElements == elements.length) {
            resize();
            return true;
        }
        elements[nrElements++] = e;
        return false;
    }

    @Override
    public boolean contains(Object element) {
        for (int i = 0; i < nrElements; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        if (index <= nrElements) {
            return elements[index];
        }
        return null;
    }

    @Override
    public int size() {
        return nrElements;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

}
