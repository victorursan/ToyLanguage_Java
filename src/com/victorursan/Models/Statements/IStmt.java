package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.DivisionByZeroException;
import com.victorursan.Models.Expressions.UninitializedVariableException;
import com.victorursan.Models.Heap.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

import java.io.Serializable;

/**
 * Created by victor on 10/24/15.
 */
public interface IStmt extends Serializable {
    String toString();
    PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException;
}