package com.victorursan.Models.ProgramState;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Statements.IStmt;

import java.io.Serializable;

/**
 * Created by victor on 10/12/15.
 */
public class PrgState implements Serializable {
    private IStack<IStmt> exeStack;
    private IMap<String, Integer> symTable;
    private IList<Integer> out;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(IStack<IStmt> stack, IMap<String, Integer> dictionary, IList<Integer> list, IStmt prg) {
        exeStack = stack;
        symTable = dictionary;
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

    public IList<Integer> getOut() {
        return out;
    }

    public String printState() {
        return "--------------------------------\n" +  "Exec Stack:\n" + exeStack.toString() +
                "\nSymbol table\n" + symTable.toString() + "\n\nOutput List\n" + out.toString() +
                "\n\n--------------------------------\n";
    }

}
