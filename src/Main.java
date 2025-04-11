import dataStructures.*;
import models.*;
import Test.PerformanceTester;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MyInventory inventory = new MyInventory();
    private static final MyQueue<Order> orderQueue = new MyQueue<>();
    private static int orderNumber = 1;

    private static final MyArrayList<Order> processedOrders = new MyArrayList<>();
    private static final MyStack<Order> failedOrdersStack = new MyStack<>();
    private static final SearchingAlgorithms searchAlgo = new SearchingAlgorithms();
    private static final SortingAlgorithms sortingAlgo = new SortingAlgorithms();

    public static void main(String[] args) {
        PerformanceTester.runAll();

        addInitialBooksToStock();
        while (true) {
            System.out.println("\n=== Online Bookstore System ===");
            System.out.println("1. Create new order");
            System.out.println("2. Process order");
            System.out.println("3. Search order by number");
            System.out.println("4. Search orders by customer name");
            System.out.println("5. Add book to inventory");
            System.out.println("6. Display inventory");
            System.out.println("7. Update book stock");
            System.out.println("8. Display sorted orders by customer name");
            System.out.println("9. Retry failed orders");
            System.out.println("10. View processed order history");

            System.out.println("11. Exit");

            int choice = safeInputInt("Choose an option: ");

            switch (choice) {
                case 1 -> createNewOrder();
                case 2 -> processOrder();
                case 3 -> searchOrder();
                case 4 -> searchOrderByName();
                case 5 -> addBookToStock();
                case 6 -> inventory.displayStock();
                case 7 -> updateBookStock();
                case 8 -> displaySortedOrdersByCustomerName();
                case 9 -> retryFailedOrders();
                case 10 -> viewProcessedOrderHistory();
                case 11 -> {
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addInitialBooksToStock() {
        inventory.addBook("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 20);
        inventory.addBook("1984", "George Orwell", 12.99, 15);
        inventory.addBook("To Kill a Mockingbird", "Harper Lee", 14.99, 30);
        inventory.addBook("The Catcher in the Rye", "J.D. Salinger", 9.99, 25);
        inventory.addBook("Lord of the Flies", "William Golding", 11.99, 18);
        inventory.addBook("Pride and Prejudice", "Jane Austen", 13.99, 22);
        inventory.addBook("Animal Farm", "George Orwell", 10.99, 17);
        System.out.println("7 books have been added to the inventory.");
    }

    private static void createNewOrder() {
        String customerName = safeInputString("Enter customer name: ");
        System.out.println("Available books:");
        inventory.displayStock();

        int bookChoice = safeInputInt("Choose a book (enter the book number): ");
        Book selectedBook = inventory.getBookByIndex(bookChoice - 1);

        if (selectedBook == null) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        int quantity = safeInputInt("Enter quantity: ");

        if (inventory.isBookAvailable(selectedBook.getTitle(), quantity)) {
            Order order = new Order(orderNumber++, customerName, selectedBook.getTitle(), quantity);
            orderQueue.enqueue(order);
            System.out.println("Order placed successfully. It will be processed soon.");
        } else {
            System.out.println("Not enough stock for the selected book.");
        }
    }

    private static void processOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }

        Order order = orderQueue.dequeue();
        System.out.println("Processing order: " + order);

        boolean success = inventory.reduceStock(order.getBookTitle(), order.getQuantity());
        if (success) {
            System.out.println("Order processed successfully.");
            processedOrders.add(order);
        } else {
            System.out.println("Failed to process order due to stock error (book not found or insufficient quantity).");
            failedOrdersStack.push(order);
        }

        System.out.println("\nUnprocessed orders:");
        Order[] remainingOrders = orderQueue.toArray();
        if (remainingOrders.length == 0) {
            System.out.println("No remaining orders in the queue.");
        } else {
            for (Order o : remainingOrders) {
                System.out.println(o);
            }
        }
    }

    private static void searchOrder() {
        int orderNum = safeInputInt("Enter order number to search: ");
        Order foundOrder = searchAlgo.findOrderByNumber(orderQueue.toArray(), orderNum);
        if (foundOrder != null) {
            System.out.println("Order found: " + foundOrder);
        } else {
            System.out.println("Order not found with the entered number.");
        }
    }

    private static void searchOrderByName() {
        String customerName = safeInputString("Enter customer name to search: ");
        Order[] foundOrdersByName = searchAlgo.findOrdersByCustomerName(orderQueue.toArray(), customerName);
        if (foundOrdersByName.length > 0) {
            System.out.println("Orders found:");
            for (Order order : foundOrdersByName) {
                System.out.println(order);
            }
        } else {
            System.out.println("No orders found for the entered customer name.");
        }
    }

    private static void addBookToStock() {
        String title = safeInputString("Enter book title: ");
        String author = safeInputString("Enter author: ");
        double price = safeInputDouble("Enter price: ");
        int quantity = safeInputInt("Enter quantity: ");

        inventory.addBook(title, author, price, quantity);
        System.out.println("Book has been added to inventory.");
        System.out.println("Updated inventory:");
        inventory.displayStock();
    }

    private static void updateBookStock() {
        System.out.println("Available books:");
        inventory.displayStock();

        int bookChoice = safeInputInt("Choose a book (enter the book number): ");
        Book selectedBook = inventory.getBookByIndex(bookChoice - 1);

        if (selectedBook == null) {
            System.out.println("Invalid selection.");
            return;
        }

        int quantity = safeInputInt("Enter new stock quantity: ");
        inventory.updateStock(selectedBook.getTitle(), quantity);
        System.out.println("Book stock updated:");
        inventory.displayStock();
    }

    private static void displaySortedOrdersByCustomerName() {
        MyQueue<Order> tempOrderQueue = new MyQueue<>();
        Order[] ordersArray = new Order[orderQueue.size()];
        int index = 0;

        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.dequeue();
            ordersArray[index++] = order;
            tempOrderQueue.enqueue(order);
        }

        sortingAlgo.quickSort(ordersArray, 0, ordersArray.length - 1);

        if (ordersArray.length == 0) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("Orders sorted by customer name:");
        for (Order order : ordersArray) {
            System.out.println(order);
        }

        while (!tempOrderQueue.isEmpty()) {
            orderQueue.enqueue(tempOrderQueue.dequeue());
        }
    }



    private static void retryFailedOrders() {
        if (failedOrdersStack.isEmpty()) {
            System.out.println("No failed orders to retry.");
            return;
        }

        MyStack<Order> tempStack = new MyStack<>();
        System.out.println("Retrying failed orders...");

        while (!failedOrdersStack.isEmpty()) {
            Order order = failedOrdersStack.pop();
            System.out.println("Retrying order: " + order);

            boolean success = inventory.reduceStock(order.getBookTitle(), order.getQuantity());
            if (success) {
                System.out.println("Order processed successfully on retry.");
            } else {
                System.out.println("Still failed. Keeping in retry stack.");
                tempStack.push(order);
            }
        }

        // Push remaining failed orders back
        while (!tempStack.isEmpty()) {
            failedOrdersStack.push(tempStack.pop());
        }
    }

    private static void viewProcessedOrderHistory() {
        if (processedOrders.size() == 0) {
            System.out.println("No processed orders yet.");
            return;
        }

        System.out.println("=== Processed Order History ===");
        for (int i = 0; i < processedOrders.size(); i++) {
            System.out.println((i + 1) + ". " + processedOrders.get(i));
        }
    }




    // ==============================
    //        INPUT SAFEGUARDS
    // ==============================

    private static int safeInputInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double safeInputDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String safeInputString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }
}
