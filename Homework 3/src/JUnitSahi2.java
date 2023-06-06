import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class JUnitSahi2 {
    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
                array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalData() {
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueSizeZero() {
        linked.enqueue("a");
        assertSame("a", linked.peek());
        assertEquals(1, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        assertSame(cur, linked.getTail());
        assertNull(cur.getNext());
    }


    @Test(timeout = TIMEOUT)
    public void testEnqueueSizeOne() {
        linked.enqueue("a");
        linked.enqueue("b");
        assertSame("a", linked.peek());
        assertEquals(2, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("b", cur.getData());
        assertSame(cur, linked.getTail());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueSizeTwo() {
        linked.enqueue("a");
        linked.enqueue("b");
        linked.enqueue("c");
        assertSame("a", linked.peek());
        assertEquals(3, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("b", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("c", cur.getData());
        assertSame(cur, linked.getTail());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueLots() {
        linked.enqueue("a");
        linked.enqueue("b");
        linked.enqueue("c");
        linked.enqueue("d");
        linked.enqueue("e");
        linked.enqueue("f");
        linked.enqueue("g");
        linked.enqueue("h");
        assertSame("a", linked.peek());
        linked.enqueue("i");
        linked.enqueue("j");
        linked.enqueue("k");
        linked.enqueue("l");
        linked.enqueue("m");
        linked.enqueue("n");
        assertSame("a", linked.peek());
        linked.enqueue("o");
        assertEquals(15, linked.size());
        LinkedNode<String> cur = linked.getHead();
        String[] elements = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
        for (int i = 0; i < elements.length - 1; i++) {
            assertNotNull(cur);
            assertEquals(elements[i], cur.getData());
            cur = cur.getNext();
        }

        assertSame(cur, linked.getTail());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testDequeueOneElement() {
        linked.enqueue("a");
        assertEquals(1, linked.size());
        assertSame("a", linked.dequeue());
        assertEquals(0, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNull(cur);
        assertNull(linked.getHead());
        assertNull(linked.getTail());
        assertSame(cur, linked.getHead());
        assertSame(cur, linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testDequeueTwoRemoveOneElements() {
        linked.enqueue("a");
        linked.enqueue("b");
        assertEquals(2, linked.size());
        assertSame("a", linked.dequeue());
        assertEquals(1, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("b", cur.getData());
        assertSame(cur, linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testDequeueTwoRemoveAllElements() {
        linked.enqueue("a");
        linked.enqueue("b");
        assertEquals(2, linked.size());
        assertSame("a", linked.dequeue());
        assertSame("b", linked.peek());
        assertSame("b", linked.dequeue());
        assertEquals(0, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNull(cur);
        assertNull(linked.getHead());
        assertNull(linked.getTail());
        assertSame(cur, linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testEnqueueDequeueLots() {
        linked.enqueue("a");  // a 
        linked.enqueue("b"); // a b
        assertEquals(2, linked.size());
        assertSame("a", linked.peek());
        assertSame("a", linked.dequeue()); // b
        assertEquals(1, linked.size());
        linked.enqueue("c"); // b c
        linked.enqueue("d"); // b c d 
        assertSame("b", linked.dequeue()); // c d
        assertSame("c", linked.peek());
        linked.enqueue("e"); // c d e
        linked.enqueue("f"); // c d e f
        linked.enqueue("g"); // c d e f g
        linked.enqueue("h"); // c d e f g h
        linked.enqueue("i"); // c d e f g h i
        assertEquals(7, linked.size());
        assertSame("c", linked.dequeue()); // d e f g h i
        assertSame("d", linked.peek());
        assertEquals(6, linked.size());
        linked.enqueue("j"); // d e f g h i j
        linked.enqueue("k"); // d e f g h i j k
        assertSame("d", linked.peek());
        linked.enqueue("l"); // d e f g h i j k l 
        linked.enqueue("m"); // d e f g h i j k l m
        linked.enqueue("n"); // d e f g h i j k l m n
        assertEquals(11, linked.size());
        assertSame("d", linked.dequeue()); // e f g h i j k l m n
        assertSame("e", linked.peek());
        assertEquals(10, linked.size());
        linked.enqueue("o");  // e f g h i j k l m n o
        assertEquals(11, linked.size());
        LinkedNode<String> cur = linked.getHead();
        String[] elements = {"e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
        for (int i = 0; i < elements.length - 1; i++) {
            assertNotNull(cur);
            assertEquals(elements[i], cur.getData());
            cur = cur.getNext();
        }

        assertSame(cur, linked.getTail());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPeekEmptyList() {
        linked.peek();
    }
}