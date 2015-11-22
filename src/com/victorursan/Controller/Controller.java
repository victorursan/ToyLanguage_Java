package com.victorursan.Controller;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import com.victorursan.Models.Expressions.ArithExp;
import com.victorursan.Models.Expressions.DivisionByZeroException;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Expressions.UninitializedVariableException;
import com.victorursan.Models.Heap.HashIndexOutOfBoundsException;
import com.victorursan.Models.Heap.IHeap;
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
    private boolean logFlag;

    public Controller(Repository thisRepo) throws EmptyRepositoryException {
        printFlag = true;
        logFlag = true;
        repo = thisRepo;
        crtPrgState = repo.getCrtProgram();
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

    public void oneStep() throws MyStmtExecException, UninitializedVariableException, EmptyRepositoryException, DivisionByZeroException, NoSuchKeyException, HashIndexOutOfBoundsException {
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
            IMap<String, Integer> symTbl = getCrtPrgState().getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            int val = exp.eval(symTbl, heap);
            symTbl.put(id, val);
        } else if (crtStmt instanceof IfStmt) {
            IfStmt crtStmt1 = (IfStmt) crtStmt;
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            if (crtStmt1.getExp().eval(symTbl, heap) != 0) {
                stk.push(crtStmt1.getThenS());
            } else {
                stk.push(crtStmt1.getElseS());
            }
        } else if (crtStmt instanceof PrintStmt) {
            PrintStmt crtStmt1 = (PrintStmt) crtStmt;
            IList<Integer> output = crtPrgState.getOut();
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            output.add(crtStmt1.getExp().eval(symTbl, heap));

        } else if (crtStmt instanceof WhileStmt) {
            WhileStmt crtStmt1 = (WhileStmt) crtStmt;
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            if (crtStmt1.getExp().eval(symTbl, heap) != 0) {
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
        } else if (crtStmt instanceof IfThenStmt) {
            IfThenStmt crtStmt1 = (IfThenStmt) crtStmt;
            stk.push(new IfStmt(crtStmt1.getExp(), crtStmt1.getThenS(), new SkipStmt()));
        } else if (crtStmt instanceof NewStmt) {
            NewStmt crtStmt1 = (NewStmt) crtStmt;
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            symTbl.put(crtStmt1.getId(), heap.add(crtStmt1.getExp().eval(symTbl, heap)));
        } else if (crtStmt instanceof WriteHeapStmt) {
            WriteHeapStmt crtStmt1 = (WriteHeapStmt) crtStmt;
            IMap<String, Integer> symTbl = crtPrgState.getSymTable();
            IHeap<Integer> heap =  getCrtPrgState().getHeapTable();
            heap.update(symTbl.get(crtStmt1.getId()), crtStmt1.getExp().eval(symTbl, heap));
        }
        } catch (EmptyStackException e) {
            throw new MyStmtExecException();
        }
        if (printFlag) {
            System.out.println(crtPrgState.printState());
        }
        if (logFlag) {
            repo.logPrgState();
        }
    }

    public void allStep() throws MyStmtExecException, UninitializedVariableException, NoSuchKeyException,
                                 EmptyRepositoryException, DivisionByZeroException, HashIndexOutOfBoundsException{
        while (true) { // (!crtPrgState.getExeStack().isEmpty()) {
            oneStep();
        }
    }

}
