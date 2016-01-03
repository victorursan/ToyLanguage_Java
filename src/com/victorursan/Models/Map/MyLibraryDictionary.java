package com.victorursan.Models.Map;

import com.victorursan.Models.Map.Exception.NoSuchKeyException;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by victor on 10/24/15.
 */
public class MyLibraryDictionary<K, V> implements IMap<K, V> {
    private HashMap<K, V> hashMap;

    public MyLibraryDictionary() {
        hashMap = new HashMap<>();
    }

    public MyLibraryDictionary(MyLibraryDictionary<K, V> tmp) {
        hashMap = (HashMap<K, V>) tmp.hashMap.clone();
    }
    @Override
    public void clear() {
        hashMap = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return hashMap.containsValue(value);
    }

    @Override
    public V get(K key) throws NoSuchKeyException {
        if (!this.containsKey(key)) throw new NoSuchKeyException();
        return hashMap.get(key);
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void put(K key, V value) {
        hashMap.put(key, value);
    }

    @Override
    public V remove(K key) throws NoSuchKeyException {
        if (!this.containsKey(key)) throw new NoSuchKeyException();
        return hashMap.remove(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (K elem : hashMap.keySet()) {
            toPrint = elem.toString() + " -> " + hashMap.get(elem)  + "\n" + toPrint;
        }
        return toPrint;
    }
}
