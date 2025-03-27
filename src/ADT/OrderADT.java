package ADT;

public interface OrderADT {
    int getOrderNumber();
    String getCustomerName();
    String getBookTitle();
    int getQuantity();
    void setQuantity(int quantity);
    String toString();
}
