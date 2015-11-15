package com.victorursan.Repository;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.ProgramState.PrgState;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by victor on 10/24/15.
 */
public interface Repository {
    PrgState getCrtProgram() throws EmptyRepositoryException;
    void setPrgStates(IList<PrgState> prgStates);
    void serializePrgStatet();
    void logPrgState();
    IList<PrgState> deserializePrgStatet() throws IOException;
}
