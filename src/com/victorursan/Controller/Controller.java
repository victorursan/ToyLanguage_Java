package com.victorursan.Controller;

import com.victorursan.Models.Expressions.ArithExp;
import com.victorursan.Models.Expressions.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Expressions.UninitializedVariableException;
import com.victorursan.Models.List.IList;
import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.EmptyStackException;
import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Statements.*;
import com.victorursan.Repository.EmptyRepositoryException;
import com.victorursan.Repository.Repository;

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

    public Controller(Repository thisRepo) throws EmptyRepositoryException {
        printFlag = true;
        repo = thisRepo;
        crtPrgState = repo.getCrtProgram();
    }

    public void oneStep() throws MyStmtExecException, UninitializedVariableException, EmptyRepositoryException, DivisionByZeroException, NoSuchKeyException {
        IStack<IStmt> stk = crtPrgState.getExeStack();
        try {
            IStmt crtStmt = stk.pop();
        if (crtStmt instanceof CompStmt) {
            CompStmt crtStmt1 = (CompStmt) crtStmt;
            stk.push(crtStmt1.getSecond());
            stk.push(crtStmt1.getFirst());
        } else if (crtStmt instanceof AssignStmt) {
            AssignStmt crtStmt1 = (AssignStmt) crtStmt;
            Exp exp = crtStmt1.getExp();
            String id = crtStmt1.getId();
            IMap<String, Integer> symTbl = repo.getCrtProgram().getSymTable();
            int val = exp.eval(symTbl);
            symTbl.put(id, val);
        } else if (crtStmt instanceof IfStmt) {
            IfStmt crtStmt1 = (IfStmt) crtStmt;
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
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
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            if (crtStmt1.getExp().eval(symTbl) != 0) {
                stk.push(crtStmt1);
                stk.push(crtStmt1.getStmt());
            }
        } else if (crtStmt instanceof SkipStmt) {
            //nothing
        } else if (crtStmt instanceof SwitchStmt) {
            SwitchStmt crtStmt1 = (SwitchStmt) crtStmt;
            Exp difSwitch = new ArithExp(crtStmt1.getExp(), "-", crtStmt1.getExpCase2());
            Exp difSwitch2 = new ArithExp(crtStmt1.getExp(), "-", crtStmt1.getExpCase1());
            IStmt ifSwitch = new IfStmt(difSwitch2, crtStmt1.getDefaultCase(), crtStmt1.getCase1());
            IStmt switchStmt = new IfStmt(difSwitch, ifSwitch, crtStmt1.getCase2());
            stk.push(switchStmt);
        }
        } catch (EmptyStackException e) {
            throw new MyStmtExecException();
        }
        if (printFlag) {
            System.out.println(crtPrgState.printState());
        }
    }

    public void allStep() throws MyStmtExecException, UninitializedVariableException, NoSuchKeyException,
                                 EmptyRepositoryException, DivisionByZeroException {
        while (!crtPrgState.getExeStack().isEmpty()) {
            oneStep();
        }
    }

}
