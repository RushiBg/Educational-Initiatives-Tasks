package com.designpatterns.behavioural.strategy;

/**
 * Context: Uses a SortingStrategy to perform the sort.
 */
public class Sorter {
    private SortingStrategy strategy;

    // The strategy can be changed at runtime
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(int[] numbers) {
        if (strategy == null) {
            System.out.println("Error: Sorting strategy not set.");
            return;
        }
        strategy.sort(numbers);
    }
}
