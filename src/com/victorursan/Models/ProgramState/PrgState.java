package com.victorursan.Models.ProgramState;

import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.Map;
import com.victorursan.Models.Stack.ArrayStack;
import com.victorursan.Models.Stack.Stack;
import com.victorursan.Models.Statements.IStmt;

/**
 * Created by victor on 10/12/15.
 */
public class PrgState {
    private Stack exeStack;
    private Map symTable;
    private IList<Integer> out;
    private IStmt originalProgram; //optional field, but good to have

    public PrgState(Stack stack, Map dictionary, IList<Integer> list, IStmt prg){
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

    public IList<Integer> getOut() {
        return out;
    }

    public void printState() {
        Stack tmpStack = new ArrayStack();
        System.out.println("Exec Stack:");
        while (!exeStack.isEmpty()) {
            IStmt element = exeStack.pop();
            tmpStack.push(element);
            System.out.println(element.toStr());
        }
        while (!tmpStack.isEmpty()) {
            exeStack.push(tmpStack.pop());
        }

        System.out.println("\nSymbol table");

        for (String key: symTable.keys()) {
            System.out.println(key + " = " + symTable.get(key).toString());
        }

        System.out.println("\nOutput List");

        for (int index = 0; index < out.size(); index++) {
            System.out.println(out.get(index));
        }

        System.out.println("");
    }

}
