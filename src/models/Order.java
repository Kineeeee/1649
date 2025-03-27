package models;

import ADT.OrderADT;

public class Order implements OrderADT {
    private int orderNumber;
    private String customerName;
    private String bookTitle;
    private int quantity;

    public Order(int orderNumber, String customerName, String bookTitle, int quantity) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
    }

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order #" + orderNumber + " | Customer: " + customerName + " | Book: " + bookTitle + " | Quantity: " + quantity;
    }
}
