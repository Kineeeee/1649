package ADT;

import models.Order;

public interface SortingAlgorithmsADT {
    void quickSort(Order[] arr, int low, int high);
    int partition(Order[] arr, int low, int high);
}
