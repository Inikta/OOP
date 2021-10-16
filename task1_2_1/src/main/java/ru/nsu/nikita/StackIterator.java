package ru.nsu.nikita;

import java.util.EmptyStackException;
import java.util.Iterator;

public class StackIterator <T> implements Iterator <T> {

    private int cnt;        //counter for elements returning method .next()
    private int amount;     //size of array
    private int hasCnt;     //counter for element existing method .hasNext()
    private T[] stackArr;

    public StackIterator(MyStack <T> someStack) {       //stack iterator constructor
        this.cnt = -1;              //is -1 for the [0] element showing
        this.hasCnt = 0;
        this.amount = someStack.count();
        this.stackArr = (T[]) someStack.getStack();
    }

    public T next() {
        if (cnt < amount) {         //if next element exists, return it
            cnt++;
            return stackArr[cnt];
        }
        else {
            cnt = -1;               //else throw exception and reset counter for new for-each loop
            throw new EmptyStackException();
        }
    }

    public boolean hasNext() {
        if (hasCnt < amount) {      //if next element exists, return true
            hasCnt++;
            return true;
        }
        else {
            hasCnt = 0;             //else return false and reset counter for new for-each loop
            return false;
        }
    }
}
