package ADT;

import models.Order;

public interface QueueADT<T> {
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
    int size();
    void clear();
    T peek();
    Order[] toArray();
}
