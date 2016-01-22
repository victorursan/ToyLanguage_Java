package com.victorursan.Models.Latch;

import com.victorursan.Models.Heap.Exception.HashIndexOutOfBoundsException;

import java.io.Serializable;

/**
 * Created by victor on 1/22/16.
 */
public interface Latch<K, V>  extends Serializable {
    K add(V key);
    V get(K key) throws HashIndexOutOfBoundsException;
    boolean containsKey(K key);
    void update(K key, V value) throws HashIndexOutOfBoundsException;
}
