package com.victorursan.Models.Stack;

import com.victorursan.Models.Statements.IStmt;

/**
 * Created by victor on 10/6/15.
 */
public interface Stack {
    void push(IStmt e);
    IStmt pop();
    boolean isEmpty();
    IStmt peek();
    int search(IStmt e);
}
