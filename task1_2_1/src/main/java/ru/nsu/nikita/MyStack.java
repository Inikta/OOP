package ru.nsu.nikita;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> implements Iterable<T> {
    private T[] stack;     //array of stack
    private int top;            //index of current element on the top of the heap
    private int size;           //current size of the array

    @SuppressWarnings("unchecked")
    public MyStack() {     //MyStack constructor
        this.size = 1;          //is 0 for correct element pushing and array resize
        this.top = 0;
        this.stack = (T[]) new Object[size];
    }

    public void push(T newElem) {
        stack[top++] = newElem;     //add new element
        increaseSize();             //increase array size by 1
    }

    public void pushStack(MyStack<T> inStack) throws EmptyStackException {
        for (T element : inStack) {            //push from inStack to this stack while inStack has unchecked elements
            this.push(element);
        }
    }

    public T pop() throws EmptyStackException {
        if (top > 0) {             //pop element if stack array is not empty yet
            top--;
            decreaseSize();
            return stack[top];
        } else {
            throw new EmptyStackException();
        }
    }

    public MyStack<T> popStack(int amount) throws EmptyStackException {
        if (amount > this.top) {
            throw new EmptyStackException();
        } //throw exception, if user pops more elements than left in stack
        MyStack<T> res = new MyStack<>();      //create result stack, to which this stack elements will be added
        for (int i = 0; i < amount; i++) {          //and return resulting stack
            res.push(this.pop());
        }
        return res;
    }

    public int count() {
        return top;
    }

    private void increaseSize() {       //increase array size in two times
        if (top == size) {
            size *= 2;
            stack = Arrays.copyOf(stack, size);
        }
    }

    private void decreaseSize() {      //decrease size in two times, if second half of array is empty. For better memory usage.
        if (top < size / 2) {
            size = top + 1;
            stack = Arrays.copyOf(stack, size);
        }
    }

    public StackIterator<T> iterator() {      //create iterator for this stack
        return new StackIterator<>(this);
    }

    @SuppressWarnings("unchecked")
    public T[] getStack() {             //return stack array
        Object[] res = new Object[top];
        System.arraycopy(stack, 0, res, 0, top);
        return (T[]) res;
    }
}
