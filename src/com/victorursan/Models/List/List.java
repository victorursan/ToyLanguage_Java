package com.victorursan.Models.List;

import com.victorursan.Models.Expressions.Exp;

/**
 * Created by victor on 10/24/15.
 */
public interface List {
    //Return if or not the list is empty
    boolean isEmpty();
    //Appends the specified element to the end of this list (optional operation).
    boolean	add(Integer e);
    //Returns true if this list contains the specified element.
    boolean	contains(Integer element);
    //Returns the element at the specified position in this list.
    Integer get(int index);
    //Returns the number of elements in this list.
    int	size();

}
