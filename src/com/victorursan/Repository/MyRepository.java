package com.victorursan.Repository;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.List.IndexOutOfBoundsException;
import com.victorursan.Models.ProgramState.PrgState;

/**
 * Created by victor on 10/24/15.
 */
public class MyRepository implements Repository {
    private IList<PrgState> prgStates;
    private Integer prgNumber = 0;

    public MyRepository(IList<PrgState> prgStates) {
        this.prgStates = prgStates;
        this.prgNumber = prgStates.size();
    }

    @Override
    public PrgState getCrtProgram() throws EmptyRepositoryException {
        try {
            if (prgNumber > 0)
                return this.prgStates.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyRepositoryException();
        }
        throw new EmptyRepositoryException();
    }
}


