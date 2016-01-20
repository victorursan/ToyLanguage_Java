package com.victorursan.Models.Stack;

import com.victorursan.Models.Stack.Exception.EmptyStackException;

import java.util.Stack;

/**
 * Created by victor on 10/6/15.
 */
public class MyLibraryStack<T> implements IStack<T> {
    private Stack<T> elements;

    public MyLibraryStack() {
        elements = new Stack<>();
    }

    public MyLibraryStack(MyLibraryStack<T> old) {
        elements = (Stack<T>)old.elements.clone();
    }

    @Override
    public void push(T e) {
        elements.push(e);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.isEmpty()) throw new EmptyStackException();
        return elements.pop();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public T peek() throws EmptyStackException {
        if (this.isEmpty()) throw new EmptyStackException();
        return elements.peek();
    }

    @Override
    public int search(T e) {
        return elements.search(e);
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
