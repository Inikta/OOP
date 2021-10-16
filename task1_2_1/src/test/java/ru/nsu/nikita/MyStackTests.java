package ru.nsu.nikita;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.EmptyStackException;

public class MyStackTests {
    @Test
    void emptyStackTest () {        //pop from empty stack
        MyStack <Dot> stack = new MyStack<>();
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void pushPopTest () {
        Dot[] dots = {new Dot(1, 1), new Dot(2, 2), new Dot(3, 3), new Dot(4, 4),};
        MyStack <Dot> ref = new MyStack<>();
        MyStack <Dot> stack = new MyStack<>();

        ref.push(dots[0]);      //make reference stack
        stack.push(dots[0]);    //fill stack with elements
        stack.push(dots[1]);
        Dot temp = stack.pop();     //pop one and save

        Assertions.assertArrayEquals(ref.getStack(), stack.getStack());     //checks if left in stack elements are right
        Assertions.assertEquals(dots[1], temp);             //checks if popped element is right
    }

    @Test
    void pushPopStackTest () {
        Dot[] dots = {new Dot(1, 1), new Dot(2, 2), new Dot(3, 3), new Dot(4, 4),};
        MyStack <Dot> dotsStack = new MyStack<>();
        MyStack <Dot> stack = new MyStack<>();

        Dot[] ref1 = {dots[0], dots[1]};        //make reference stacks
        Dot[] ref2 = {dots[3], dots[2]};

        for (Dot dot : dots) {                  //add dots to temporary dotsStack
            dotsStack.push(dot);
        }

        stack.pushStack(dotsStack);             //push dotsStack to stack
        MyStack <Dot> tempStack = stack.popStack(2);    //pop stack of 2 elements and save

        Assertions.assertArrayEquals(ref1, stack.getStack());       //checks if left in stack elements are right
        Assertions.assertArrayEquals(ref2, tempStack.getStack());   //checks if popped stack is right
    }

    @Test
    void CounterTest () {
        Dot[] dots = {new Dot(1, 1), new Dot(2, 2), new Dot(3, 3), new Dot(4, 4),};
        MyStack <Dot> dotsStack = new MyStack<>();
        MyStack <Dot> stack = new MyStack<>();

        int ref1 = 2;
        int ref2 = 2;

        for (Dot dot : dots) {      //add dots to temporary dotsStack
            dotsStack.push(dot);
        }

        stack.pushStack(dotsStack);     //push dotsStack to stack
        MyStack <Dot> tempStack = stack.popStack(2);    //pop stack of 2 elements and save

        Assertions.assertEquals(ref1, stack.count());       //checks if left in stack elements amount is right
        Assertions.assertEquals(ref2, tempStack.count());   //checks if popped stack size is right
    }

    @Test
    void iteratorTest () {
        Dot[] dots = {new Dot(1, 1), new Dot(2, 2), new Dot(3, 3), new Dot(4, 4),};
        MyStack <Dot> dotsStack = new MyStack<>();

        Dot[] res = {null, null, null, null};       //array, which will be filled with elements from dotsStack

        for (Dot dot : dots) {
            dotsStack.push(dot);
        }

        StackIterator <Dot> dotIterator = new StackIterator<>(dotsStack);       //create new stackIterator for dotsStack
        int i = 0;
        while (dotIterator.hasNext()) {     //add elements from dotsStack to res by for-each loop
            res[i] = dotIterator.next();
            i++;
        }

        Assertions.assertArrayEquals(dots, res);        //checks if added elements are right
    }
}
