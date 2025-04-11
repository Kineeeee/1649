package ADT;

public interface ArrayListADT<T> {
    void add(T item);
    T get(int index);
    void remove(int index);
    boolean contains(T item);
    int size();
    void clear();
    void add(int index, T item);
    boolean isEmpty();
}
