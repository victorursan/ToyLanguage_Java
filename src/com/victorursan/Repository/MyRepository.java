package com.victorursan.Repository;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.List.Exception.IndexOutOfBoundsException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Created by victor on 10/24/15.
 */
public class MyRepository implements Repository {
    private IList<PrgState> prgStates;


    public MyRepository(IList<PrgState> prgStates) {
        this.prgStates = prgStates;
        try {
            Files.deleteIfExists(FileSystems.getDefault().getPath("prgState.txt"));
        } catch (IOException e) {
            System.out.println("No old prgState.txt file");
        }
    }
    public MyRepository() {
        this.prgStates = null;
    }

    public IList<PrgState> getPrgStates() {
        return prgStates;
    }

    @Override
    public PrgState getCrtProgram() throws EmptyRepositoryException {
        try {
            if (prgStates.size() > 0)
                return this.prgStates.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyRepositoryException();
        }
        throw new EmptyRepositoryException();
    }

    @Override
    public void logPrgState() {
        try {
            FileChannel fc = new RandomAccessFile("prgState.txt", "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(this.getCrtProgram().printState().getBytes()));
        } catch (IOException| EmptyRepositoryException  e) {
           System.out.println("no such file");
        }
    }


    @Override
    public void serializePrgStatet() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("prgState.ser"));
            out.writeObject(prgStates);
        } catch (IOException e) {
            System.err.println("Error serialization: " + e.getMessage());
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch(IOException e)  {
                    System.err.println("Error " + e.getMessage());
                }
        }
    }


    @Override
    public void deserializePrgStatet() throws IOException {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("prgState.ser"));
           this.prgStates = (IList<PrgState>) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Error deserialization, class not found " + e.getMessage());
        } finally{
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Error " + e.getMessage());
                }
        }
    }
}


