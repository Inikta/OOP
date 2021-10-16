package ru.nsu.nikita;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack <T> {
    private Object[] stack;     //array of stack
    private int top;            //index of current element on the top of the heap
    private int size;           //current size of the array

    @SuppressWarnings("unchecked")
    public <T> MyStack () {     //MyStack constructor
        this.size = 0;          //is 0 for correct element pushing and array resize
        this.top = 0;
        this.stack = (T[]) new Object[size];
    }
    
    public void push(T newElem) {
        increaseSize();             //increase array size by 1
        stack[top++] = newElem;     //add new element
    }

    public void pushStack(MyStack <T> inStack) throws EmptyStackException {
        StackIterator <T> iter = new StackIterator<>(inStack);      //for-each loop for inStack
        while (iter.hasNext()) {            //push from inStack to this stack while inStack has unchecked elements
            this.push(iter.next());
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() throws EmptyStackException {
        T res;
        if (size > 0) {             //pop element if stack array is not empty yet
            top--;
            res = (T) stack[top];
            decreaseSize();         //and decrease array size by 1
        }
        else {              //if it is empty, throw exception
            throw new EmptyStackException();
        }

        return res;
    }
    
    public MyStack <T> popStack(int am) {
        MyStack <T> res = new MyStack<>();      //create result stack, to which this stack elements will be added
        for (int i = 0; i < am; i++) {          //and return resulting stack
            try {
                res.push(this.pop());
            }
            catch (EmptyStackException ex) {    //if exception was caught during popping - return current stack
                return res;
            }
        }
        return res;
    }
    
    public int count() {
        return top;
    }
    
    private void increaseSize() {       //increase array size by 1
        if (top == size) {
            stack = Arrays.copyOf(stack, size + 1);
            size++;
        }
    }

    private void decreaseSize() {       //decrease array size by 1
        stack = Arrays.copyOf(stack, size-1);
        size--;
    }

    public StackIterator <T> iterator () {      //create iterator for this stack
        return new StackIterator<>(this);
    }

    @SuppressWarnings("unchecked")
    public T[] getStack() {             //return stack array
        return (T[]) stack;
    }
}
