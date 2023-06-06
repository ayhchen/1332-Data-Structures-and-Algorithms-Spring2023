import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Some tests that attempt to test edge cases for removeLastOccurrence
 * It also makes sure that one element linked lists are linked properly
 * The test file also tests that all methods throw the expected exceptions
 * @author Abhinav Vemulapalli
 * @version 1.0
 */

public class JUnit6 {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() { list = new CircularSinglyLinkedList<>(); }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrenceRepeatedValueAtEnd() {
        String temp = "2a";

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "2a");
        list.addAtIndex(4, "3a");
        list.addAtIndex(5, "3a");
        list.addAtIndex(6, "4a");
        list.addAtIndex(7, "2a");
        assertEquals(8, list.size());

        assertSame(temp, list.removeLastOccurrence("2a"));
        assertEquals(7, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrenceOneElement() {
        String temp = "0a";

        list.addAtIndex(0, "0a");
        assertEquals(1, list.size());

        assertSame(temp, list.removeLastOccurrence("0a"));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLastOccurrenceIllegalArgumentException() {
        list.removeLastOccurrence(null);
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLastOccurrenceEmptyList() {
        list.removeLastOccurrence("1a");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLastOccurrenceDataNotFound() {
        list.addToFront("0a");
        list.removeLastOccurrence("1a");
    }

    @Test(timeout = TIMEOUT)
    public void testToArrayWhenEmpty() {
        String[] expectedArray = new String[0];
        assertArrayEquals(expectedArray, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddWithOneElement() {
        list.addToFront("0a");
        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        list.clear();

        list.addToBack("0a");

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        list.clear();
    }


    @Test(timeout = TIMEOUT)
    public void testRemoveWithOneElement() {
        list.addToFront("0a");

        assertEquals("0a", list.removeFromFront());
        assertEquals(0, list.size());
        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNull(cur);

        list.addToFront("0a");

        assertEquals("0a", list.removeFromBack());
        assertEquals(0, list.size());
        cur = list.getHead();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddExceptions(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(-1, "0a"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(2, "0a"));
        assertThrows(IllegalArgumentException.class, () -> list.addAtIndex(0, null));

        assertThrows(IllegalArgumentException.class, () -> list.addToFront(null));
        assertThrows(IllegalArgumentException.class, () -> list.addToBack(null));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveExceptions() {
        assertThrows(NoSuchElementException.class, () -> list.removeFromBack());
        assertThrows(NoSuchElementException.class, () -> list.removeFromFront());

        list.addToFront("0a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(2));
    }

    @Test(timeout = TIMEOUT)
    public void testGetExceptions() {
        list.addToFront("0a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }
}