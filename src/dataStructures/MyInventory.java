package dataStructures;

import ADT.InventoryADT;
import models.Book;

public class MyInventory implements InventoryADT {
    private Book[] books;
    private int size;

    public MyInventory() {
        books = new Book[100]; // Initial inventory capacity (can be increased if needed)
        size = 0;
    }

    @Override
    public void addBook(String title, String author, double price, int quantity) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title)) {
                books[i].setQuantity(books[i].getQuantity() + quantity);
                return;
            }
        }
        if (size >= books.length) {
            System.out.println("⚠ Inventory is full. Cannot add new book.");
            return;
        }
        books[size++] = new Book(title, author, price, quantity);
    }

    @Override
    public void updateStock(String title, int quantity) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title)) {
                books[i].setQuantity(quantity);
                return;
            }
        }
        System.out.println("⚠ Book not found in inventory.");
    }

    @Override
    public boolean isBookAvailable(String title, int quantity) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i].getQuantity() >= quantity;
            }
        }
        return false;
    }

    @Override
    public boolean reduceStock(String title, int quantity) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equals(title)) {
                if (books[i].getQuantity() >= quantity) {
                    books[i].setQuantity(books[i].getQuantity() - quantity);
                    return true;
                } else {
                    System.out.println("⚠ Not enough stock to reduce.");
                    return false;
                }
            }
        }
        System.out.println("⚠ Book not found in inventory.");
        return false;
    }

    @Override
    public void displayStock() {
        if (size == 0) {
            System.out.println("⚠ Inventory is empty.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
    }

    // Method to get a book by index
    public Book getBookByIndex(int index) {
        if (index >= 0 && index < size) {
            return books[index];
        }
        return null;
    }
}
