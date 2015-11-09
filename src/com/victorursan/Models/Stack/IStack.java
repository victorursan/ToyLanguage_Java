package com.victorursan.Models.Stack;

/**
 * Created by victor on 10/6/15.
 */
public interface IStack<T> {
    void push(T e);

    T pop() throws EmptyStackException;

    boolean isEmpty();

    T peek() throws EmptyStackException;

    int search(T e);
}
