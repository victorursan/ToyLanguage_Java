package com.victorursan.Models.Expressions;

import com.victorursan.Models.Heap.IHeap;
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
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
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
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVariableException, DivisionByZeroException, NoSuchKeyException {
        try {
            return readInteger("input=");
        } catch (UnexpectedTypeException e) {
            return eval(tbl, heap);
        }
    }

    @Override
    public String toString() {
        return "read()";
    }
}
