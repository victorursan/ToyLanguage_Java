package com.victorursan.Models.Statements;

import com.victorursan.Models.Buffer;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 1/20/16.
 */
public class CloseFileStmt implements IStmt {
    private String fileName;

    public CloseFileStmt(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        if(state.getFileTable().containsKey(fileName)) {
            if (state.getFileTable().get(fileName).getThreadID() == state.getId()) {
                state.getFileTable().put(fileName, null);
            } else {
                throw new NoSuchKeyException();
            }
        } else {
            throw new NoSuchKeyException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "close_file(" + fileName + ")";
    }
}
