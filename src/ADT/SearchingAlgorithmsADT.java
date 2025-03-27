package ADT;

import models.Order;

public interface SearchingAlgorithmsADT {
    Order findOrderByNumber(Order[] orders, int orderNumber);
    Order[] findOrdersByCustomerName(Order[] orders, String customerName);
}
