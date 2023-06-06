import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Yu-Hsin Chen
 * @version 11.0.16.1
 * @userid ychen3558
 * @GTID 903789607
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     * 
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        size = 0;

        for (int i = 0; i < data.size(); i++) {
            T arrayListData = data.get(i);
            if (arrayListData == null) {
                throw new IllegalArgumentException("Data is null");
            }
            backingArray[i + 1] = arrayListData;
        }
        size = data.size();
        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Method to heap up; compares the current data with its parent data, swaps
     * the data if the current data is larger than its parent data.
     * @param index index in the backingArray to start heaping
     */
    private void upHeap(int index) {
        if (index == 1) {
            return;
        }
        if (backingArray[index].compareTo(backingArray[index / 2]) > 0) {
            T temp = backingArray[index / 2];
            backingArray[index / 2] = backingArray[index];
            backingArray[index] = temp;
            upHeap(index / 2);
        }
    }

    /**
     * Method to heap down;  USED TO REMOVE
     * @param index index in the backingArray to start heaping
     */
    private void downHeap(int index) {
        if (index > size / 2) {
            return;
        }
        T largerData = backingArray[index];

        if (backingArray[(index * 2) + 1] == null) {
            if (backingArray[index * 2].compareTo(backingArray[index]) > 0) {
                largerData = backingArray[index * 2];
            }
        } else {
            largerData = getLargerData(backingArray[(index * 2) + 1], backingArray[index * 2], backingArray[index]);
        }

        if (largerData.equals(backingArray[index * 2])) {
            backingArray[index * 2] = backingArray[index];
            backingArray[index] = largerData;
            downHeap(index * 2);
        } else if (largerData.equals(backingArray[(index * 2) + 1])) {
            backingArray[(index * 2) + 1] = backingArray[index];
            backingArray[index] = largerData;
            downHeap((index * 2) + 1);
        }

    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == backingArray.length - 1) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            newArray[backingArray.length] = data;
            backingArray = newArray;
        } else {
            backingArray[size + 1] = data;
        }
        size = size + 1;
        upHeap(size);
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T toRemove = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size = size - 1;
        downHeap(1);

        return toRemove;
    }

    /**
     * Helper method used to get the larger data to help with downHeap.
     * @param data1 first data being compared
     * @param data2 second data being compared
     * @param data3 third data being compared
     * @return the larger data out of the three data
     */
    private T getLargerData(T data1, T data2, T data3) {
        if (data1.compareTo(data2) > 0 && data1.compareTo(data3) > 0) {
            return data1;
        } else if (data2.compareTo(data1) > 0 && data2.compareTo(data3) > 0) {
            return data2;
        } else {
            return data3;
        }
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
