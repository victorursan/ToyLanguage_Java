package com.victorursan.Controller;

import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Map.Map;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Models.Stack.Stack;
import com.victorursan.Models.Statements.AssignStmt;
import com.victorursan.Models.Statements.CompStmt;
import com.victorursan.Models.Statements.IStmt;
import com.victorursan.Repository.Repository;

/**
 * Created by victor on 10/24/15.
 */
public class Controller {
    private Repository repo;

    public Controller(Repository repo) {
        this.repo = repo;
    }

    public void oneStep(PrgState state) {
        Stack stk = state.getExeStack();
//        if(stk.isEmpty()) {
//            throws MyStmtExecException;
//        }
        IStmt crtStmt = stk.pop();
        if (crtStmt instanceof CompStmt){
            CompStmt crtStmt1= (CompStmt) crtStmt;
            stk.push(crtStmt1.second);
            stk.push(crtStmt1.first);
            return;
        }
        if (crtStmt instanceof AssignStmt){
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
            return;
        }
    }

}
