package com.victorursan.Controller;

import com.victorursan.Models.List.Exception.IndexOutOfBoundsException;
import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import com.victorursan.Repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


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

    public PrgState getCrtPrgState() throws EmptyRepositoryException, IndexOutOfBoundsException {
        return repo.getPrgList().get(0);
    }

    public void serializeProgramState() {
        repo.serializePrgStatet();
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        prgList.forEach(p -> System.out.println(p.printState()));
        List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> (Callable<PrgState>) p::oneStep)
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList)
                .stream()
                .map(future -> { try {return future.get();} catch(Exception e) { return null; }})
                .filter(p -> p != null)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(p -> System.out.println(p.printState()));
        repo.setPrgList(prgList);
    }

    public void allStep() throws EmptyRepositoryException, InterruptedException 1{
        while(true) {
            List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() != 0) {
                oneStepForAllPrg(prgList);
            }
            return;

        }
    }

}
