package com.victorursan.Repository;

import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/24/15.
 */
public interface Repository {
    PrgState getCrtProgram() throws EmptyRepositoryException;
}
