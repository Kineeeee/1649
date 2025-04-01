package dataStructures;

import ADT.StackADT;

public class MyStack<T> implements StackADT<T> {
    private Node top;
    private int size;

    // Constructor
    public MyStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(T item) {
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;
        T item = top.element;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) return null;
        return top.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        T element;
        Node next;

        public Node(T element) {
            this.element = element;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = top;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
