package dataStructures;

import ADT.StackADT;

public class MyStack<T> implements StackADT<T> {
    private Object[] stack;
    private int top;

    public MyStack() {
        stack = new Object[100];
        top = -1;
    }

    @Override
    public void push(T item) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full.");
            return;
        }
        stack[++top] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) return null;
        return (T) stack[top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) return null;
        return (T) stack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }
}
