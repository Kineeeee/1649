package ADT;

import models.Book;

public interface InventoryADT {
    void addBook(String title, String author, double price, int quantity);
    void updateStock(String title, int quantity);
    boolean isBookAvailable(String title, int quantity);
    boolean reduceStock(String title, int quantity);
    void displayStock();
}
