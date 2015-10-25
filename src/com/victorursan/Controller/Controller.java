package com.victorursan.Controller;

import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.List.List;
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
    public boolean printFlag;

    public Controller(Repository repo) {
        printFlag = true;
        this.repo = repo;
    }

    public void oneStep(PrgState state) throws MyStmtExecException {
        Stack stk = state.getExeStack();
        if (stk.isEmpty()) {
            throw new MyStmtExecException();
        }
        IStmt crtStmt = stk.pop();
        if (crtStmt instanceof CompStmt) {
            CompStmt crtStmt1 = (CompStmt) crtStmt;
            stk.push(crtStmt1.second);
            stk.push(crtStmt1.first);
        } else if (crtStmt instanceof AssignStmt) {
            AssignStmt crtStmt1 = (AssignStmt) crtStmt;
            Exp exp = crtStmt1.exp;
            String id = crtStmt1.id;
            Map symTbl = state.getSymTable();
            int val = exp.eval(symTbl);
            if (symTbl.containsKey(id)) {
                symTbl.update(id, val);
            } else {
                symTbl.put(id, val);
            }
        } else if (crtStmt instanceof IfStmt) {
            IfStmt crtStmt1 = (IfStmt) crtStmt;
            Map symTbl = state.getSymTable();
            if (crtStmt1.exp.eval(symTbl) != 0) {
                stk.push(crtStmt1.thenS);
            } else {
                stk.push(crtStmt1.elseS);
            }
        } else if (crtStmt instanceof PrintStmt) {
            PrintStmt crtStmt1 = (PrintStmt) crtStmt;
            List output = state.getOut();
            output.add(crtStmt1.exp);
        }
        if (printFlag) {
            state.printState();
        }
    }

    public void allStep(PrgState state) {
//        PrgState prg = repo.getCrtProgram(); // repo is the controller field of type MyRepoInterface try{
        try {
        while(true){
            oneStep(state);
        }
        }
        catch(MyStmtExecException e) {
            System.out.println("Finished");
        }
    }

}
