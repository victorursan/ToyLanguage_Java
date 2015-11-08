package com.victorursan.Controller;

import com.victorursan.Models.Expressions.*;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.Map;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.Stack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.Repository;
import com.victorursan.Controller.MyStmtExecException;

/**
 * Created by victor on 10/24/15.
 */
public class Controller {
    private Repository repo;
    private PrgState crtPrgState;
    private boolean printFlag;

    public boolean isPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(boolean printFlag) {
        this.printFlag = printFlag;
    }

    public PrgState getCrtPrgState() {
        return crtPrgState;
    }

    public void setCrtPrgState(PrgState crtPrgState) {
        this.crtPrgState = crtPrgState;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public Controller(Repository thisRepo) {
        printFlag = true;
        repo = thisRepo;
        crtPrgState = repo.getCrtProgram();
    }

    public void oneStep() throws MyStmtExecException, UninitializedVariableException {
        Stack<IStmt> stk = crtPrgState.getExeStack();
        if (stk.isEmpty()) {
            throw new MyStmtExecException();
        }
        IStmt crtStmt = stk.pop();
        if (crtStmt instanceof CompStmt) {
            CompStmt crtStmt1 = (CompStmt) crtStmt;
            stk.push(crtStmt1.getSecond());
            stk.push(crtStmt1.getFirst());
        } else if (crtStmt instanceof AssignStmt) {
            AssignStmt crtStmt1 = (AssignStmt) crtStmt;
            Exp exp = crtStmt1.getExp();
            String id = crtStmt1.getId();
            Map<String, Integer> symTbl = repo.getCrtProgram().getSymTable();
            int val = exp.eval(symTbl);
            if (symTbl.containsKey(id)) {
                symTbl.update(id, val);
            } else {
                symTbl.put(id, val);
            }
        } else if (crtStmt instanceof IfStmt) {
            IfStmt crtStmt1 = (IfStmt) crtStmt;
            Map<String, Integer> symTbl = crtPrgState.getSymTable();
            if (crtStmt1.getExp().eval(symTbl) != 0) {
                stk.push(crtStmt1.getThenS());
            } else {
                stk.push(crtStmt1.getElseS());
            }
        } else if (crtStmt instanceof PrintStmt) {
            PrintStmt crtStmt1 = (PrintStmt) crtStmt;
            IList<Integer> output = crtPrgState.getOut();
            output.add(crtStmt1.getExp().eval(crtPrgState.getSymTable()));

        } else if (crtStmt instanceof WhileStmt) {
            WhileStmt crtStmt1 = (WhileStmt) crtStmt;
            Map<String, Integer> symTbl = crtPrgState.getSymTable();
            if (crtStmt1.getExp().eval(symTbl) != 0) {
                stk.push(crtStmt1);
                stk.push(crtStmt1.getStmt());
            }
        }

        if (printFlag) {
            crtPrgState.printState();
        }
    }

    public void allStep() throws MyStmtExecException, UninitializedVariableException{
        while(!crtPrgState.getExeStack().isEmpty()){
            oneStep();
        }
    }

}
