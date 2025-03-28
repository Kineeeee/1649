package dataStructures;

import ADT.QueueADT;
import models.Order;

public class MyQueue<T> implements QueueADT<T> {
    private Object[] queue;
    private int front;
    private int rear;
    private int size;
    private static final int INITIAL_CAPACITY = 10;


    // Constructor
    public MyQueue() {
        queue = new Object[INITIAL_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public void enqueue(T item) {
        if (size == queue.length) {
            resize();
        }
        queue[rear] = item;
        rear = (rear + 1) % queue.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = (T) queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return item;
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
        for (int i = 0; i < size; i++) {
            queue[(front + i) % queue.length] = null;
        }
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) queue[front];
    }

    private void resize() {
        int newCapacity = queue.length * 2;
        Object[] newQueue = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % queue.length];
        }
        queue = newQueue;
        front = 0;
        rear = size;
    }

    @Override
    public Order[] toArray() {
        Order[] result = new Order[size];
        for (int i = 0; i < size; i++) {
            result[i] = (Order) queue[(front + i) % queue.length]; // ép từng phần tử
        }
        return result;
    }

}
