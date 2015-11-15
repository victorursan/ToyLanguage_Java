package com.victorursan.Repository;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.List.IndexOutOfBoundsException;
import com.victorursan.Models.ProgramState.PrgState;

import java.io.*;

/**
 * Created by victor on 10/24/15.
 */
public class MyRepository implements Repository {
    private IList<PrgState> prgStates;


    public MyRepository(IList<PrgState> prgStates) {
        this.prgStates = prgStates;
    }
    public MyRepository() {
        this.prgStates = null;
    }

    public IList<PrgState> getPrgStates() {
        return prgStates;
    }

    public void setPrgStates(IList<PrgState> prgStates) {
        this.prgStates = prgStates;
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
    public void logPrgState() {

    }

    @Override
    public IList<PrgState> deserializePrgStatet() throws IOException {
        IList<PrgState> prgState = null;
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("prgState.ser"));
            prgState = (IList<PrgState>) in.readObject();
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
        return prgState;
    }
}


