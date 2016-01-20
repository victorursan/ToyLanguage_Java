package com.victorursan.Models;

import com.victorursan.Models.List.IList;
import com.victorursan.Models.Statements.IStmt;

import java.io.Serializable;

/**
 * Created by victor on 1/20/16.
 */
public class Procedure implements Serializable {
    private IList<String> parameters;
    private IStmt body;

    public IList<String> getParameters() {
        return parameters;
    }

    public IStmt getBody() {
        return body;
    }

    public Procedure(IList<String> parameters, IStmt body) {
        this.parameters = parameters;
        this.body = body;
    }
}
