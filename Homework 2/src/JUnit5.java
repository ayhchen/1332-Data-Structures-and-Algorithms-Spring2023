import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnit5 {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    //whether it is circular singly linkedlist.
    @Test(timeout = TIMEOUT)
    public void testCircular() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a

        CircularSinglyLinkedListNode<String> curr = list.getHead();

        String[] expected = {"0a", "1a", "0a"};
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], curr.getData());
            curr = curr.getNext();
        }
    }

    //have to use .equals() when comparing objects.
    @Test(timeout = TIMEOUT)
    public void testForEquality() {
        list.addAtIndex(0, new String("0a"));   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a

        String temp = new String("1a");
        assertEquals(new String("1a"), list.removeLastOccurrence(temp));
    }

}