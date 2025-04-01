package dataStructures;

import ADT.ArrayListADT;

public class MyArrayList<T> implements ArrayListADT<T> {
    private Node head;
    private int size;

    private class Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    public MyArrayList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            head = head.next;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) curr = curr.next;
            curr.next = curr.next.next;
        }
        size--;
    }

    @Override
    public boolean contains(T item) {
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(item)) return true;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
