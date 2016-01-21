package com.victorursan.Models.Statements;

import com.victorursan.Models.Buffer;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by victor on 1/20/16.
 */
public class OpenFileStmt implements IStmt {
    private String fileName;

    public OpenFileStmt(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        if(state.getFileTable().containsKey(fileName)) {
            Buffer b = state.getFileTable().get(fileName);
            if (b == null) {
                state.getFileTable().put(fileName, new Buffer(state.getId()));
            } else {
                if (b.getThreadID() == state.getId()) {
                    return null;
                } else {
                    state.getExeStack().push(this);
                }
            }
        } else {
            try {
                new RandomAccessFile(fileName, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            state.getFileTable().put(fileName, new Buffer(state.getId()));
        }
        return null;
    }

    @Override
    public String toString() {
        return "open_file(" + fileName + ")";
    }

}
