package com.victorursan.Models.ProgramState;

import com.victorursan.Models.List.List;
import com.victorursan.Models.Map.Map;
import com.victorursan.Models.Stack.Stack;
import com.victorursan.Models.Statements.IStmt;

/**
 * Created by victor on 10/12/15.
 */
public class PrgState {
    public Stack exeStack;
    public Map symTable;
    public List out;
    public IStmt originalProgram; //optional field, but good to have

    public PrgState(Stack stack, Map dictionary, List list, IStmt prg){
        exeStack = stack;
        symTable = dictionary;
        out = list;
        originalProgram = prg;
        exeStack.push(originalProgram);
    }

    public Stack getExeStack() {
        return exeStack;
    }

    public Map getSymTable() {
        return symTable;
    }

}
