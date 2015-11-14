package com.victorursan.Models.List;

/**
 * Created by victor on 11/10/15.
 */
public class MyArrayList implements IList<Integer> {
    private Integer[] elements;
    private int nrElements;

    public MyArrayList() {
        elements = new Integer[10];
        nrElements = 0;
    }

    private void resize() {
        Integer[] tmpKeys = new Integer[elements.length * 2];
        System.arraycopy(elements, 0, tmpKeys, 0, elements.length);
        elements = tmpKeys;
    }

    @Override
    public boolean add(Integer e) {
        if (nrElements == elements.length) {
            resize();
            return true;
        }
        elements[nrElements++] = e;
        return false;
    }

    @Override
    public boolean contains(Integer element) {
        for (int i = 0; i < nrElements; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(int index) {
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
