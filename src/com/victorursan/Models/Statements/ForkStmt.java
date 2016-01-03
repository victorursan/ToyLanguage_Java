package com.victorursan.Models.Statements;

import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.MyLibraryStack;

import java.io.*;

/**
 * Created by victor on 1/2/16.
 */
public class ForkStmt implements IStmt {
    private IStmt stmt;


    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }


    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        MyLibraryStack<IStmt> newStack = new MyLibraryStack<>();
        IMap<String, Integer> cloneSymTbl = deepCopy(state.getSymTable());
        return new PrgState(newStack, cloneSymTbl, state.getHeapTable(), state.getOut(), stmt);
    }

    static private IMap<String, Integer> deepCopy(IMap<String, Integer> oldObj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            // serialize and pass the object
            oos.writeObject(oldObj);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            // return the new object
            return (IMap<String, Integer>) ois.readObject();
        }  catch(Exception e) {
            System.out.println("Exception in copying: " + e);
        }  finally {
            if (oos != null && ois != null) {
                try {
                    oos.close();
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Error " + e.getMessage());
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "fork (" + stmt.toString() + ")";
    }
}
