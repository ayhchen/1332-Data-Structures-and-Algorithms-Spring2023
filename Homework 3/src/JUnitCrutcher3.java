import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/** Custom testing case for the Linked List backed Queue :)
 *  Should cover variations of adding, removing, and peek
 * @author Kaitlyn
 * @version 1.0
 */
public class JUnitCrutcher3 {
    private static final int TIMEOUT = 200;
    private LinkedQueue<Integer> queue;

    @Before
    public void setup() {
        queue = new LinkedQueue();
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueEmpty() {
        queue.enqueue(1);
        assertEquals((int) queue.peek(), 1);
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueue1() {
        populateQueue(4);
        int[] expectedValues = {0, 1, 2, 3};
        compareQueueArray(expectedValues);
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueManual() {
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);

        int[] expectedValues = {5, 4, 3, 2, 1};
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals((int) queue.dequeue(), expectedValues[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testDequeueEmpty() {
        queue.enqueue(1);
        queue.dequeue();
        assertEquals(null, queue.getHead());
        assertEquals(null, queue.getTail());
        assertEquals(queue.size(), 0);
    }

    @Test(timeout = TIMEOUT)
    public void testDequeue() {
        populateQueue(5);
        int[] expectedArray = {0, 1, 2, 3, 4};
        compareQueueArray(expectedArray);
    }
    @Test(timeout = TIMEOUT)
    public void testPeek() {
        populateQueue(5);
        for (int i = 0; i < 5; i++) {
            assertEquals(i, (int) queue.peek());
            queue.dequeue();
        }
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueEnqueue() {
        populateQueue(10);
        assertEquals(9, (int) queue.getTail().getData());
        assertEquals(0, (int) queue.getHead().getData());
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
            assertEquals(i + 1, (int) queue.getHead().getData());
        }
        assertEquals(9, (int) queue.getTail().getData());
        int[] expectedValues = {5, 6, 7, 8, 9};
        assertEquals(5, queue.size());
        compareQueueArray(expectedValues);
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            assertEquals(i, (int) queue.getTail().getData());
        }
        int[] expectedValues2 = {5, 6, 7, 8, 9, 0, 1, 2, 3, 4};
        compareQueueArray(expectedValues2);
    }
    @Test(timeout = TIMEOUT)
    public void testDequeueEnqueue2() {
        queue.enqueue(1);
        assertEquals(1, (int) queue.getHead().getData());
        assertEquals(1, (int) queue.getTail().getData());
        queue.enqueue(2);
        assertEquals(1, (int) queue.getHead().getData());
        assertEquals(2, (int) queue.getTail().getData());
        queue.dequeue();
        assertEquals(2, (int) queue.getHead().getData());
        assertEquals(2, (int) queue.getTail().getData());
        queue.dequeue();
        assertEquals(null, queue.getHead());
        assertEquals(null, queue.getTail());
        queue.enqueue(2);
        assertEquals(2, (int) queue.getHead().getData());
        assertEquals(2, (int) queue.getTail().getData());



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
     *
     * @param expectedValues the array of expected values
     */
    private void compareQueueArray(int[] expectedValues) {
        LinkedQueue<Integer> copiedQueue = new LinkedQueue();
        for (int i = 0; i < expectedValues.length; i++) {
            copiedQueue.enqueue(expectedValues[i]);
        }
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], (int) queue.peek());
            queue.dequeue();
        }
        queue = copiedQueue;
    }
}