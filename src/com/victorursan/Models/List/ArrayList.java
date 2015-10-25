package com.victorursan.Models.List;

import com.victorursan.Models.Expressions.Exp;

/**
 * Created by victor on 10/24/15.
 */
public class ArrayList implements List {
    private Exp[] elements;
    private int nrElements;

    public ArrayList() {
        elements = new Exp[10];
        nrElements = 0;
    }

    private void resize() {
        Exp[] tmpKeys = new Exp[elements.length * 2];
        System.arraycopy(elements, 0, tmpKeys, 0, elements.length);
        elements = tmpKeys;
    }

    @Override
    public boolean add(Exp e) {
        if (nrElements == elements.length) {
            resize();
            return true;
        }
        elements[nrElements++] = e;
        return false;
    }

    @Override
    public boolean contains(Exp element) {
        for (int i = 0; i < nrElements; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Exp get(int index) {
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
