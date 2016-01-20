package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.List.Exception.IndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.MyLibraryDictionary;
import com.victorursan.Models.Procedure;
import com.victorursan.Models.ProgramState.PrgState;

import java.util.List;

/**
 * Created by victor on 1/20/16.
 */
public class CallStmt implements IStmt {
    private String fName;
    private List<Exp> params;

    public CallStmt(String fName, List<Exp> params) {
        this.fName = fName;
        this.params = params;
    }

    @Override
    public String toString() {
        return "call " + fName + " " + params.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        if (state.getProcTable().containsKey(fName)) {
            Procedure p = state.getProcTable().get(fName);
            IMap<String, Integer> procSymTable = new MyLibraryDictionary<>();
            for (Exp e : params) {
                try {
                    String param = p.getParameters().get(params.indexOf(e));
                    Integer value = e.eval(state.getSymTable(),state.getHeapTable());
                    procSymTable.put(param, value);
                } catch (IndexOutOfBoundsException e1) {
                    e1.printStackTrace();
                }
            }
            state.getFullSymTable().push(procSymTable);
            state.getExeStack().push(new ReturnStmt());
            state.getExeStack().push(p.getBody());
        } else {
            throw new NoSuchKeyException();
        }
        return null;
    }
}
