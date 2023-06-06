import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {
    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0 && comparator.compare(arr[index], arr[index - 1]) < 0) {
                T temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index = index - 1;
            }
        }
    }
    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }

        for (int i = arr.length - 1; i > 0; i--) {
            int largestDataIndex = i;
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr[j], arr[largestDataIndex]) > 0) {
                    largestDataIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[largestDataIndex];
            arr[largestDataIndex] = temp;
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }

        boolean swapsMade = true;
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int lastSwapped = endIndex;

        while (swapsMade) {
            swapsMade = false;
            for (int i = startIndex; i < endIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapsMade = true;
                    lastSwapped = i;
                }
            }
            endIndex = lastSwapped;

            if (swapsMade) {
                swapsMade = false;
                for (int j = endIndex; j > startIndex; j--) {
                    if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                        T temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                        swapsMade = true;
                        lastSwapped = j;
                    }
                }
            }
            startIndex = lastSwapped;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }

        mergeHelper(arr, comparator);
    }

    /**
     * Method that helps carry out the mergesort operation
     * @param arr array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @return the sorted array
     * @param <T> data type to sort
     */
    private static <T> T[] mergeHelper(T[] arr, Comparator<T> comparator) {
        if (arr.length == 1) {
            return arr;
        }
        int midIndex = arr.length / 2;
        T[] left = (T[]) new Object[midIndex];
        T[] right = (T[]) new Object[arr.length - midIndex];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
            right[i] = arr[midIndex + i];
        }

        if (arr.length % 2 == 1) {
            right[right.length - 1] = arr[arr.length - 1];
        }

        left = mergeHelper(left, comparator);
        right = mergeHelper(right, comparator);

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                arr[leftIndex + rightIndex] = left[leftIndex];
                leftIndex = leftIndex + 1;
            } else {
                arr[leftIndex + rightIndex] = right[rightIndex];
                rightIndex = rightIndex + 1;
            }
        }

        while (leftIndex < left.length) {
            arr[leftIndex + rightIndex] = left[leftIndex];
            leftIndex = leftIndex + 1;
        }
        while (rightIndex < right.length) {
            arr[leftIndex + rightIndex] = right[rightIndex];
            rightIndex = rightIndex + 1;
        }

        return arr;
    }
    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Random is null");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is not in the range of 1 to arr.length");
        }

        kthSelectHelper(k, arr, comparator, rand, 0, arr.length - 1);
        return arr[k - 1];
    }

    /**
     * Helper method to carry out the kthSelect (QuickSelect) operation
     * @param k             the index to retrieve data from + 1 (due to
     *                      0-indexing) if the array was sorted; the 'k' in "kth
     *                      select"; e.g. if k == 1, return the smallest element
     *                      in the array
     * @param arr           the array that should be modified after the method
     *                      is finished executing as needed
     * @param comparator    the Comparator used to compare the data in arr
     * @param rand          the Random object used to select pivots
     * @param start         the start index to compare until the number is greater than the pivot
     * @param end           the end index to compare until the number is smaller than the pivot
     * @param <T>           data type to sort
     */
    private static <T> void kthSelectHelper(int k, T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        if (end - start <= 0) {
            return;
        }

        int pivotIndex = rand.nextInt(end - start + 1) + start;
        T pivotValue = arr[pivotIndex];

        T temp = arr[start];
        arr[start] = arr[pivotIndex];
        arr[pivotIndex] = temp;

        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotValue) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotValue) >= 0) {
                j--;
            }

            if (i <= j) {
                T temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
                j--;
            }
        }

        T temp2 = arr[start];
        arr[start] = arr[j];
        arr[j] = temp2;

        if (j == k - 1) {
            return;
        } else if (j > k - 1) {
            kthSelectHelper(k, arr, comparator, rand, start, j - 1);
        } else {
            kthSelectHelper(k, arr, comparator, rand, j + 1, end);
        }
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }

        if (arr.length == 1) {
            return;
        }

        LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19];

        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }

        int divisor = 1;

        for (int iteration = 0; iteration < 10; iteration++) {
            for (int i = 0; i < arr.length; i++) {
                int currentDigit = arr[i] / divisor;
                buckets[currentDigit % 10 + 9].add(arr[i]);
            }
            int index = 0;
            for (int bucket = 0; bucket < buckets.length; bucket++) {
                while (buckets[bucket].size() != 0) {
                    arr[index++] = buckets[bucket].remove();
                }
                if (index == arr.length) {
                    break;
                }
            }
            divisor = divisor * 10;
        }

    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(data);
        int[] toReturn = new int[data.size()];

        for (int i = 0; i < data.size(); i++) {
            toReturn[i] = heap.poll();
        }

        return toReturn;
    }
}