package ru.nsu.nikita;

import java.util.EmptyStackException;
import java.util.Iterator;

public class StackIterator<T> implements Iterator<T> {
    private int cnt;        //counter for elements returning method .next()
    private final int amount;     //size of array
    private final T[] stackArr;

    public StackIterator(MyStack<T> someStack) {       //stack iterator constructor
        this.cnt = 0;              //is -1 for the [0] element showing
        this.amount = someStack.count();
        this.stackArr = someStack.getStack();
    }

    public T next() {
        if (hasNext()) {
            return stackArr[++cnt - 1];     //if next element exists, return it
        } else {
            throw new EmptyStackException();    //else throw exception
        }
    }

    public boolean hasNext() {      //checks if any elements left in array
        return cnt < amount;
    }
}
