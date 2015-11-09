package com.victorursan.Models.List;

/**
 * Created by victor on 10/24/15.
 */
public interface IList<T> {
    //Return if or not the list is empty
    boolean isEmpty();

    //Appends the specified element to the end of this list (optional operation).
    boolean add(T e);

    //Returns true if this list contains the specified element.
    boolean contains(T element);

    //Returns the element at the specified position in this list.
    T get(int index) throws IndexOutOfBoundsException;

    //Returns the number of elements in this list.
    int size();

}
