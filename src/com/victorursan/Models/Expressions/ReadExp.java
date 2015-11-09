package com.victorursan.Models.Expressions;

import com.victorursan.Models.Map.IMap;
import com.victorursan.Models.Map.NoSuchKeyException;
import com.victorursan.Views.UnexpectedTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by victor on 11/9/15.
 */
public class ReadExp implements Exp {

    private void print(String message) {
        System.out.println(message);
    }

    private String readString(String message) {
        try {
            print(message);
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (IOException e) {
            print(e.getMessage());
        }
        return "";
    }

    private Integer readInteger(String message) throws UnexpectedTypeException {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            throw new UnexpectedTypeException();
        }
    }

    public ReadExp() {
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        try {
            return readInteger("");
        } catch (UnexpectedTypeException e) {
            return eval(tbl);
        }
    }

    @Override
    public String toString() {
        return "read()";
    }
}
