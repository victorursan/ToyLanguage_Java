package com.victorursan.Models.Map;

/**
 * Created by victor on 10/24/15.
 */
public class ArrayDictionary implements Map {
    private Object keys[];
    private Object values[];
    private int nrElements;

    public ArrayDictionary() {
        keys = new Object[10];
        values = new Object[10];
        nrElements = 0;
    }

    @Override
    public void clear() {
        keys = new Object[10];
        values = new Object[10];
        nrElements = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < nrElements; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    private void resize() {
        Object[] tmpKeys = new Object[keys.length * 2];
        Object[] tmpValues = new Object[values.length * 2];
        System.arraycopy(keys, 0, tmpKeys, 0, keys.length);
        System.arraycopy(values, 0, tmpValues, 0, values.length);
        keys = tmpKeys;
        values = tmpValues;
    }

    @Override
    public Object put(Object key, Object value) {
        if (!this.containsKey(key)) {
            if(keys.length >= nrElements) {
                resize();
            }
            keys[nrElements] = key;
            values[nrElements] = value;
            nrElements++;
        }
        return null;
    }

    @Override
    public boolean remove(Object key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                System.arraycopy(keys, i + 1, keys, i, keys.length - i - 1);
                System.arraycopy(values, i + 1, values, i, values.length - i - 1);
                nrElements--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return nrElements;
    }

}
