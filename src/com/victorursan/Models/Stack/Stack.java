package com.victorursan.Models.Stack;

/**
 * Created by victor on 10/6/15.
 */
public interface Stack {
    void push(Object e);
    Object pop();
    boolean isEmpty();
    Object peek();
    int search(Object e);
}
