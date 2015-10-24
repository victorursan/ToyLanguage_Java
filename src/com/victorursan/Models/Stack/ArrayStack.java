package com.victorursan.Models.Stack;

/**
 * Created by victor on 10/6/15.
 */
public class ArrayStack implements Stack {
    private Object elements[];
    private int nrElements;

    public ArrayStack() {
        nrElements = 0;
        elements = new Object[10];
    }

    @Override
    public void push(Object e) {
        if (nrElements == elements.length) {
            resize();
        }
        elements[nrElements++] = e;
    }

    private void resize() {
        Object[] tmp = new Object[elements.length * 2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }

    @Override
    public Object pop() {
        if (nrElements > 0){
            return  elements[--nrElements];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    @Override
    public Object peek() {
        if (nrElements > 0) {
            return elements[nrElements - 1];
        }
        return null;
    }

    @Override
    public int search(Object e) {
        for(int i = 1; i <= nrElements; i++ ) {
            if (elements[nrElements - i].equals(e)) return i;
        }
        return -1;
    }
}
