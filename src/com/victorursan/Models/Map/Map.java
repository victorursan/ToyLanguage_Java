package com.victorursan.Models.Map;

/**
 * Created by victor on 10/24/15.
 */
public interface Map {
    //Removes all of the mappings from this map (optional operation).
    void clear();
    //Returns true if this map contains a mapping for the specified key.
    boolean	containsKey(String key);
    //Returns true if this map maps one or more keys to the specified value.
    boolean	containsValue(Integer value);
    //Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    Integer get(String key);
    //Returns true if this map contains no key-value mappings.
    boolean	isEmpty();
    //Associates the specified value with the specified key in this map (optional operation).
    void put(String key, Integer value);
    //Removes the mapping for a key from this map if it is present (optional operation).
    boolean remove(String key);
    //Returns the number of key-value mappings in this map.
    int	size();
    //Update a pair key-value
    void update(String key, Integer value);
    //Returns a list of all keys;
    String[] keys();
}
