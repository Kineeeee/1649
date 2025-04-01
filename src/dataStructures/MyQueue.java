package dataStructures;

import ADT.QueueADT;
import models.Order;

public class MyQueue<T> implements QueueADT<T> {
    private Node head;
    private Node tail;
    private int size;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T item = head.element;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public Order[] toArray() {
        Order[] result = new Order[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            result[index++] = (Order) current.element; // ép kiểu như trong MyQueue
            current = current.next;
        }
        return result;
    }

    private class Node {
        private T element;
        private Node next;

        public Node(T element) {
            this.element = element;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
