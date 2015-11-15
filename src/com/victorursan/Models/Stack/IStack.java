package com.victorursan.Models.Stack;

import java.io.Serializable;

/**
 * Created by victor on 10/6/15.
 */
public interface IStack<T> extends Serializable {
    void push(T e);

    T pop() throws EmptyStackException;

    boolean isEmpty();

    T peek() throws EmptyStackException;

    int search(T e);
}
