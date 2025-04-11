package ADT;

import dataStructures.MyArrayList;
import models.Book;

public interface OrderADT {
    int getOrderNumber();
    String getCustomerName();
    void addBook(Book book, int quantity);
    MyArrayList<Book> getBooks();
    MyArrayList<Integer> getQuantities();
    void sortBooksByTitle();
    String toString();
}
