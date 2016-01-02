package com.victorursan.Models.ProgramState;

import com.victorursan.Controller.Exception.MyStmtExecException;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Stack.Exception.EmptyStackException;
import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Statements.IStmt;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;

import java.io.Serializable;

/**
 * Created by victor on 10/12/15.
 */
public class PrgState implements Serializable {
    private static int generator = 0;
    private int id;
    private IStack<IStmt> exeStack;
    private IMap<String, Integer> symTable;
    private IHeap<Integer> heapTable;
    private IList<Integer> out;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(IStack<IStmt> stack, IMap<String, Integer> dictionary, IHeap<Integer> heap, IList<Integer> list, IStmt prg) {
        id = generator++;
        exeStack = stack;
        symTable = dictionary;
        heapTable = heap;
        out = list;
        originalProgram = prg;
        exeStack.push(originalProgram);
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IMap<String, Integer> getSymTable() {
        return symTable;
    }

    public IHeap<Integer> getHeapTable() {
        return heapTable;
    }

    public IList<Integer> getOut() {
        return out;
    }

    public boolean isNotCompleted() {return !exeStack.isEmpty(); }

    public PrgState oneStep() throws MyStmtExecException, UninitializedVariableException, EmptyRepositoryException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
        try {
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        } catch (EmptyStackException e) {
            throw new MyStmtExecException();
        }
    }


    public String printState() {
        return "--------------------------------\n" + "id: " + id +
        "\nExec Stack:\n" + exeStack.toString() +
                "\nSymbol table\n" + symTable.toString() + "\nHeap table\n" + heapTable.toString() +
                "\n\nOutput List\n" + out.toString() + "\n\n--------------------------------\n";
    }

}
