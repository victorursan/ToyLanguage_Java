package com.victorursan.Repository;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;

import java.io.IOException;
import java.util.List;

/**
 * Created by victor on 10/24/15.
 */
public interface Repository {
    void setPrgList(List<PrgState> prgs);
    List<PrgState> getPrgList() throws EmptyRepositoryException;
    void serializePrgStatet();
    void logPrgState();
    void deserializePrgStatet() throws IOException;
}