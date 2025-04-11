package Test;

import models.Book;
import models.Order;
import dataStructures.MyQueue;
import dataStructures.MyStack;
import dataStructures.SortingAlgorithms;
import dataStructures.SearchingAlgorithms;
import dataStructures.MyInventory;

public class PerformanceTester {

    public static void testQueuePerformance() {
        MyQueue<Order> queue = new MyQueue<>();
        int count = 1000;

        long startEnqueue = System.nanoTime();
        for (int i = 0; i < count; i++) {
            Order order = new Order(i + 1, "Customer_" + i);
            order.addBook(new Book("Book", "Author", 10.0, 100), 1);
            queue.enqueue(order);
        }
        long endEnqueue = System.nanoTime();

        long startDequeue = System.nanoTime();
        while (!queue.isEmpty()) {
            queue.dequeue();
        }
        long endDequeue = System.nanoTime();

        System.out.println("[Queue]");
        System.out.println("Enqueue 1000 orders: " + (endEnqueue - startEnqueue) + " ns");
        System.out.println("Dequeue 1000 orders: " + (endDequeue - startDequeue) + " ns\n");
    }

    public static void testStackPerformance() {
        MyStack<Order> stack = new MyStack<>();
        int count = 1000;

        long startPush = System.nanoTime();
        for (int i = 0; i < count; i++) {
            Order order = new Order(i + 1, "Customer_" + i);
            order.addBook(new Book("Book", "Author", 10.0, 100), 1);
            stack.push(order);
        }
        long endPush = System.nanoTime();

        long startPop = System.nanoTime();
        while (!stack.isEmpty()) {
            stack.pop();
        }
        long endPop = System.nanoTime();

        System.out.println("[Stack]");
        System.out.println("Push 1000 orders: " + (endPush - startPush) + " ns");
        System.out.println("Pop 1000 orders: " + (endPop - startPop) + " ns\n");
    }

    public static void testInventoryPerformance() {
        MyInventory inventory = new MyInventory();
        for (int i = 0; i < 100; i++) {
            inventory.addBook("Book_" + i, "Author", 10.0, 100);
        }

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            inventory.reduceStock("Book_" + (i % 100), 1);
        }
        long end = System.nanoTime();

        System.out.println("[Inventory]");
        System.out.println("1000 stock reductions: " + (end - start) + " ns\n");
    }

    public static void testSearchPerformance() {
        int count = 1000;
        Order[] orders = new Order[count];
        for (int i = 0; i < count; i++) {
            Order order = new Order(i + 1, "Customer_" + i);
            order.addBook(new Book("Book", "Author", 10.0, 100), 1);
            orders[i] = order;
        }
        SearchingAlgorithms search = new SearchingAlgorithms();

        System.out.println("[Linear Search]");

        long t1 = System.nanoTime();
        search.findOrderByNumber(orders, 1); // best case
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        search.findOrderByNumber(orders, 500); // average case
        long t4 = System.nanoTime();

        long t5 = System.nanoTime();
        search.findOrderByNumber(orders, 1000); // worst case
        long t6 = System.nanoTime();

        System.out.println("Best-case (ID 1): " + (t2 - t1) + " ns");
        System.out.println("Average-case (ID 500): " + (t4 - t3) + " ns");
        System.out.println("Worst-case (ID 1000): " + (t6 - t5) + " ns\n");
    }

    public static void testQuickSortPerformance() {
        int count = 1000;
        Order[] orders = new Order[count];
        for (int i = 0; i < count; i++) {
            Order order = new Order(i + 1, "Customer_" + (count - i));
            order.addBook(new Book("Book", "Author", 10.0, 100), 1);
            orders[i] = order;
        }

        SortingAlgorithms sorter = new SortingAlgorithms();

        long start = System.nanoTime();
        sorter.quickSort(orders, 0, orders.length - 1);
        long end = System.nanoTime();

        System.out.println("[QuickSort]");
        System.out.println("Sort 1000 orders by customer name: " + (end - start) + " ns\n");
    }

    public static void runAll() {
        System.out.println("=== PERFORMANCE TEST START (nanoTime) ===\n");
        testQueuePerformance();
        testStackPerformance();
        testInventoryPerformance();
        testSearchPerformance();
        testQuickSortPerformance();
        System.out.println("=== PERFORMANCE TEST END ===");
    }
}
