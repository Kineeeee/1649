package dataStructures;

import ADT.SortingAlgorithmsADT;
import models.Order;

public class SortingAlgorithms implements SortingAlgorithmsADT {
    @Override
    public void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    @Override
    public int partition(Order[] arr, int low, int high) {
        Order pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].getCustomerName().compareTo(pivot.getCustomerName()) < 0) {
                i++;
                Order temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Order temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
