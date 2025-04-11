package models;

import dataStructures.MyArrayList;

public class Order {
    private int orderNumber;
    private String customerName;
    private MyArrayList<Book> books;
    private MyArrayList<Integer> quantities;

    public Order(int orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.books = new MyArrayList<>();
        this.quantities = new MyArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addBook(Book book, int quantity) {
        books.add(book);
        quantities.add(quantity);
    }

    public MyArrayList<Book> getBooks() {
        return books;
    }

    public MyArrayList<Integer> getQuantities() {
        return quantities;
    }

    public void sortBooksByTitle() {
        int n = books.size();
        for (int i = 1; i < n; i++) {
            Book keyBook = books.get(i);
            int keyQty = quantities.get(i);
            int j = i - 1;

            while (j >= 0 && books.get(j).getTitle().compareTo(keyBook.getTitle()) > 0) {
                books.add(j + 1, books.get(j));
                books.remove(j + 2);
                quantities.add(j + 1, quantities.get(j));
                quantities.remove(j + 2);
                j--;
            }

            books.add(j + 1, keyBook);
            books.remove(j + 2);
            quantities.add(j + 1, keyQty);
            quantities.remove(j + 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order #" + orderNumber + " | Customer: " + customerName + "\n");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            int quantity = quantities.get(i);
            sb.append("- ").append(book.getTitle()).append(" by ").append(book.getAuthor())
                    .append(" x").append(quantity).append("\n");
        }
        return sb.toString();
    }
}
