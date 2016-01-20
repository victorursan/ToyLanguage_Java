package com.victorursan.Models.Statements;

import com.victorursan.Models.Buffer;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
                writeToDisk(state.getFileTable().get(fileName).getBuffer());
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

    private void writeToDisk(Integer[] buffer) {
        try {
            FileChannel fc = new RandomAccessFile(fileName, "rw").getChannel();
            fc.position(fc.size());
            if(buffer[0] != null) {
                fc.write(ByteBuffer.wrap(buffer[0].toString().getBytes()));
            }
            if(buffer[1] != null) {
                fc.write(ByteBuffer.wrap(buffer[1].toString().getBytes()));
            }
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }
}
