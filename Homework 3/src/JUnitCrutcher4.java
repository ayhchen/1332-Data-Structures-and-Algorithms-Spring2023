import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/** Custom testing case for the Array-backed Queue :)
 *  Should cover variations of adding, removing, and peek
 * @author Kaitlyn
 * @version 1.0
 */
public class JUnitCrutcher4 {
    private static final int TIMEOUT = 200;
    ArrayQueue<Integer> queue;

    @Before
    public void setup() {
        queue = new ArrayQueue<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueEmpty() {
        queue.enqueue(1);
        assertEquals(queue.size(), 1);
        assertEquals(1, (int) queue.peek());
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueEmpty() {
        queue.enqueue(1);
        queue.dequeue();
        assertEquals(0, queue.size());
        assertEquals(1, queue.getFront());
    }
    @Test(timeout = TIMEOUT)
    public void testDequeue1() {
        populateQueue(4);
        int[] expectedValues = {0, 1, 2, 3};
        compareQueueArray(expectedValues);
        assertEquals(0, queue.getFront());
        queue.dequeue();
        assertEquals(1, queue.getFront());
        queue.dequeue();
        assertEquals(2, queue.getFront());
        queue.dequeue();
        assertEquals(3, queue.getFront());
        queue.dequeue();
        assertEquals(4, queue.getFront());
    }
    @Test(timeout = TIMEOUT)
    public void testDequeue2() {
        populateQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
            assertEquals(i + 1, queue.getFront());
        }
    }
    @Test(timeout = TIMEOUT)
    public void testDequeue3() {
        populateQueue(3);
        queue.dequeue();
        int[] expectedArray = {1, 2};
        compareQueueArray(expectedArray);
        assertEquals(1, queue.getFront());
        queue.enqueue(4);
        int[] expectedArray2 = {1, 2, 4};
        compareQueueArray(expectedArray);
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueComprehensive() {
        /* make new queue */
        populateQueue(6);
        /* dequeue twice */
        queue.dequeue();
        queue.dequeue();
        int[] expectedArray = {2, 3, 4, 5};
        compareQueueArray(expectedArray);
        assertEquals(2, queue.getFront());
        /* add a bunch of threes */
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        int[] expectedArray2 = {2, 3, 4, 5, 3, 3, 3, 3};
        assertEquals(8, queue.size());
        compareQueueArray(expectedArray2);
        for(int i = 0; i < 8; i++) {
            queue.dequeue();
        }
        assertEquals(1, queue.getFront());
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueWrapping() {
        populateQueue(9);
        for (int i = 0; i < 9; i++) {
            queue.dequeue();
        }
        assertEquals(0, queue.getFront());
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueWrapping2() {
        populateQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
        }
        assertEquals(10, queue.getFront());
    }
    @Test(timeout = TIMEOUT)
    public void testPeekEmpty() {
        populateQueue(1);
        assertEquals(0, (int) queue.peek());
    }
    @Test(timeout = TIMEOUT)
    public void testPeek2() {
        populateQueue(7);
        for (int i = 0; i < 7; i++) {
            assertEquals(i, (int) queue.peek());
            queue.dequeue();
        }
    }
    @Test(timeout = TIMEOUT)
    public void testPeekResize() {
        populateQueue(11);
        for (int i = 0; i < 9; i++) {
            assertEquals(i, (int) queue.peek());
            queue.dequeue();
        }
    }
    @Test(timeout = TIMEOUT)
    public void testPopulateResize() {
        populateQueue(10);
        assertEquals(10, queue.size());
        int[] expectedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        compareQueueArray(expectedArray);

    }
    /**
     * Private helper method for populating a queue with 0-i values.
     *
     * @param size the number of values in the queue
     */
    private void populateQueue(int size) {
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);
        }
    }
    /**
     * Private helper method for comparing the values of the queue with the expected values.
     * @param expectedValues the array of expected values
     */
    private void compareQueueArray(int[] expectedValues) {
        for (int i = 0; i < expectedValues.length; i++) {
            Object[] backing = queue.getBackingArray();
            int front = (queue.getFront() + i) %  backing.length;
            Integer queueValue = (Integer) backing[front];
            assertEquals(expectedValues[i], (int) queueValue);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCompareQueueArray() {
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        int[] expectedValues = {1, 1, 1, 1};
        compareQueueArray(expectedValues);
        queue.dequeue();
        int[] expectedValues2 = {1, 1, 1};
        compareQueueArray(expectedValues2);
    }
    /**
     * Private helper method for printing the queue values.
     */
    private void printQueueArray() {
        ArrayQueue<Integer> copyArray = new ArrayQueue();
        System.out.print("\n The ArrayQueue: ");
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Integer data = queue.dequeue();
            copyArray.enqueue(data);
            System.out.print(data + " ");
        }
        queue = copyArray;
    }
}