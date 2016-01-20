package com.victorursan.Models;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by victor on 1/20/16.
 */
public class Buffer implements Serializable{
    private Integer threadID;
    private Integer[] buffer;

    public Buffer(Integer threadID) {
        buffer = new Integer[2];
        this.threadID = threadID;
    }

    public int getThreadID() {
        return threadID;
    }

    public Integer[] getBuffer() {
        return buffer;
    }

    public void setBuffer(Integer[] buffer) {
        this.buffer = buffer;
    }

    @Override
    public String toString() {
        return "Opened by: " + threadID + " and the content is: " + Arrays.toString(buffer);
    }

}
