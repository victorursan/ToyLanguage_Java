package com.victorursan.Models.Stack;

/**
 * Created by victor on 10/6/15.
 */
public interface IStack<T> {
    void push(T e);

    T pop();

    boolean isEmpty();

    T peek();

    int search(T e);
}
