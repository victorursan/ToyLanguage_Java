package com.victorursan.Controller;

import com.victorursan.Models.ProgramState.PrgState;
import com.victorursan.Repository.Exceptions.EmptyRepositoryException;
import com.victorursan.Repository.Repository;

import java.io.IOException;
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
    private boolean printFlag;
    private boolean logFlag;
    private String programsOutput;

    public Controller(Repository thisRepo) throws EmptyRepositoryException {
        printFlag = true;
        logFlag = true;
        repo = thisRepo;
        programsOutput = "";
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

    public void serializeProgramState() {
        repo.serializePrgState();
    }

    public void deserializePrgStatet() throws IOException {
        repo.deserializePrgStatet();
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    private void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<PrgState>> callList = prgList.stream()
                                                    .map(p -> (Callable<PrgState>) p::oneStep)
                                                    .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList)
                                            .stream()
                                            .map(future -> { try {return future.get();} catch(Exception e) { return null; }})
                                            .filter(p -> p != null)
                                            .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        programsOutput += prgList.toString();
        if (logFlag) {
            repo.logPrgStates();
        }
        repo.setPrgList(prgList);
        executor.shutdown();
    }

    public void oneStep() throws EmptyRepositoryException, InterruptedException {
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        oneStepForAllPrg(prgList);
    }

    public void allStep() throws EmptyRepositoryException, InterruptedException {
        programsOutput = "";
        while(true) {
            List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0) {
                return;
            } else {
                oneStepForAllPrg(prgList);
            }

        }
    }

    public String getProgramsOutput() {
        return programsOutput;
    }

    public List<PrgState> getPrgList() throws EmptyRepositoryException {
        return repo.getPrgList();
    }

    public void setPrgList(List<PrgState> prgs) {
        repo.setPrgList(prgs);
    }

}
