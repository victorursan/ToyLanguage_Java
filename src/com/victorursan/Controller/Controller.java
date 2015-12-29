package com.victorursan.Controller;

import com.victorursan.Controller.Exception.MyStmtExecException;
import com.victorursan.Models.Expressions.Exception.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exception.UninitializedVariableException;
import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;
import com.victorursan.Models.Map.Exception.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.Exception.EmptyStackException;
import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import com.victorursan.Repository.Repository;


/**
 * Created by victor on 10/24/15.
 */
public class Controller {
    private Repository repo;
//    private PrgState crtPrgState;
    private boolean printFlag;
    private boolean logFlag;

    public Controller(Repository thisRepo) throws EmptyRepositoryException {
        printFlag = true;
        logFlag = true;
        repo = thisRepo;
//        crtPrgState = repo.getCrtProgram();
    }

    public boolean isLogFlag() {
        return logFlag;
    }

    public void setLogFlag(boolean logFlag) {
        this.logFlag = logFlag;
    }

    public boolean isPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(boolean printFlag) {
        this.printFlag = printFlag;
    }

    public PrgState getCrtPrgState() throws EmptyRepositoryException {
        return repo.getCrtProgram();
    }

    public void serializeProgramState () {
        repo.serializePrgStatet ();
    }

    public PrgState oneStep(PrgState state) throws MyStmtExecException, UninitializedVariableException, EmptyRepositoryException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
        IStack<IStmt> stk = state.getExeStack();
        if (printFlag) {
            System.out.println(state.printState());
        }
        if (logFlag) {
            repo.logPrgState();
        }
        try {
            IStmt crtStmt = stk.pop();
            return crtStmt.execute(state);
        } catch (EmptyStackException e) {
            throw  new MyStmtExecException();
        }
    }

    public void allStep(PrgState state) throws MyStmtExecException, UninitializedVariableException, NoSuchKeyException,
                                 EmptyRepositoryException, DivisionByZeroException, HashIndexOutOfBoundsException{
        while (true) { // (!crtPrgState.getExeStack().isEmpty()) {
            oneStep(state);
        }
    }

}
