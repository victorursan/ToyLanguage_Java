package com.victorursan.Repository;

import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;

import java.io.IOException;

/**
 * Created by victor on 10/24/15.
 */
public interface Repository {
    PrgState getCrtProgram() throws EmptyRepositoryException;
    void serializePrgStatet();
    void logPrgState();
    void deserializePrgStatet() throws IOException;
}