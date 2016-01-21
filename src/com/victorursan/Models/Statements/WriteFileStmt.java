package com.victorursan.Models.Statements;

import com.victorursan.Models.Buffer;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by victor on 1/21/16.
 */
public class WriteFileStmt implements IStmt {
    private String fileName;
    private Exp var;

    public Exp getVar() {
        return var;
    }

    public void setVar(Exp var) {
        this.var = var;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public WriteFileStmt(String fileName, Exp var) {
        this.fileName = fileName;
        this.var = var;
    }



    @Override
    public PrgState execute(PrgState state) throws HashIndexOutOfBoundsException, NoSuchKeyException, UninitializedVariableException, DivisionByZeroException {
        if(state.getFileTable().containsKey(fileName)) {
            Buffer b = state.getFileTable().get(fileName);
            if (b.getThreadID() == state.getId()) {
                Integer[] buf = b.getBuffer();
                if(buf[0] == null) {
                    buf[0] = var.eval(state.getSymTable(), state.getHeapTable());
                } else if (buf[1] == null) {
                    buf[1] = var.eval(state.getSymTable(), state.getHeapTable());
                } else {
                    writeToDisk(buf);
                    buf[0] = var.eval(state.getSymTable(), state.getHeapTable());
                    buf[1] = null;
                }
            } else {
                state.getExeStack().push(this);
            }
        } else {
            throw new NoSuchKeyException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "write_file( " + fileName + ", " + var.toString() + ")";
    }

    private void writeToDisk(Integer[] buffer) {
        try {
            FileChannel fc = new RandomAccessFile(fileName, "a").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(buffer[0].toString().getBytes()));
            fc.write(ByteBuffer.wrap(" ".getBytes()));
            fc.write(ByteBuffer.wrap(buffer[1].toString().getBytes()));
            fc.write(ByteBuffer.wrap(" ".getBytes()));
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }
}
