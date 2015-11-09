package com.victorursan.Models.Map;

import java.util.Set;

/**
 * Created by victor on 10/24/15.
 */
public interface IMap<K, V> {
    //Removes all of the mappings from this map (optional operation).
    void clear();

    //Returns true if this map contains a mapping for the specified key.
    boolean containsKey(K key);

    //Returns true if this map maps one or more keys to the specified value.
    boolean containsValue(V value);

    //Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    V get(K key) throws NoSuchKeyException;

    //Returns true if this map contains no key-value mappings.
    boolean isEmpty();

    //Associates the specified value with the specified key in this map (optional operation).
    void put(K key, V value);

    //Removes the mapping for a key from this map if it is present (optional operation).
    V remove(K key) throws NoSuchKeyException;

    //Returns the number of key-value mappings in this map.
    int size();

    //Returns a set of all keys;
    Set<K> keySet();
}
