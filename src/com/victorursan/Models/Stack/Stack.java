package com.victorursan.Models.Stack;

import com.victorursan.Models.Statements.IStmt;

/**
 * Created by victor on 10/6/15.
 */
public interface Stack<T> {
    void push(T e);
    T pop();
    boolean isEmpty();
    T peek();
    int search(T e);
}
