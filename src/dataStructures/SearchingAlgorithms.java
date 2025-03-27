package dataStructures;

import ADT.SearchingAlgorithmsADT;
import models.Order;

public class SearchingAlgorithms implements SearchingAlgorithmsADT {

    @Override
    public Order findOrderByNumber(Order[] orders, int orderNumber) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getOrderNumber() == orderNumber) {
                return orders[i];
            }
        }
        return null;
    }

    @Override
    public Order[] findOrdersByCustomerName(Order[] orders, String customerName) {
        // Tạo mảng tạm để chứa kết quả
        Order[] tempResults = new Order[orders.length];
        int count = 0;

        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getCustomerName().equalsIgnoreCase(customerName)) {
                tempResults[count++] = orders[i];
            }
        }

        // Tạo mảng kết quả với kích thước đúng
        Order[] result = new Order[count];
        for (int i = 0; i < count; i++) {
            result[i] = tempResults[i];
        }

        return result;
    }
}
