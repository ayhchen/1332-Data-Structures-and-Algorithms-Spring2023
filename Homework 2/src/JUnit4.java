import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class JUnit4 {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    //add exceptions
    @Test(timeout = TIMEOUT)
    public void testAddAtIndexExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(-1, "-1"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(4, "4"));
        assertThrows(IllegalArgumentException.class, () -> list.addAtIndex(0, null));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontExceptions() {
        assertThrows(IllegalArgumentException.class, () -> list.addToFront(null));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackExceptions() {
        assertThrows(IllegalArgumentException.class, () -> list.addToBack(null));
    }

    //test add when list is empty

    @Test(timeout = TIMEOUT)
    public void testAddFirstNode() {
        list.addAtIndex(0, "0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
        list.clear();
        list.addToBack("0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
        list.clear();
        list.addToFront("0");
        assertEquals("0", list.get(0));
        assertEquals(list.getHead(), list.getHead().getNext());
    }

    //remove exceptions
    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(0));

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontExceptions() {
        assertThrows(NoSuchElementException.class, () -> list.removeFromFront());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackExceptions() {
        assertThrows(NoSuchElementException.class, () -> list.removeFromBack());

    }

    //remove when one node
    @Test(timeout = TIMEOUT)
    public void removeOneNode() {
        list.addToFront("a");
        assertEquals("a", list.removeAtIndex(0));
        assertNull(list.getHead());
        list.addToFront("a");
        assertEquals("a", list.removeFromFront());
        assertNull(list.getHead());
        list.addToFront("a");
        assertEquals("a", list.removeFromBack());
        assertNull(list.getHead());


    }

    //removeLastOccurrence exceptions
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceExceptions() {
        assertThrows(IllegalArgumentException.class, () -> list.removeLastOccurrence(null));

    }

    //removeLastOccurrence no such element
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceNoSuchElement() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront("c");
        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence("d"));

    }

    //get exceptions
    @Test(timeout = TIMEOUT)
    public void testGetExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

    }

}