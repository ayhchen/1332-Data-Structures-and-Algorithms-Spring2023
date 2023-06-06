import junit.framework.TestCase;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/** Custom testing class for the Circular Singly Linked List class, of probably dubious quality :)
 * @author Kaitlyn
 * @version 1.0
 */
public class JUnit7 extends TestCase {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<Integer> csll;

    @Before
    public void setUp() {
        csll = new CircularSinglyLinkedList<>();
    }
    @Test(timeout = TIMEOUT)
    public void testAddToEmpty1() {
        csll.addAtIndex(0, 1);
        assertEquals(1, (int) csll.getHead().getData());
    }
    @Test(timeout = TIMEOUT)
    public void testAddToEmpty2() {
        csll.addToFront(1);
        assertEquals(1, (int) csll.getHead().getData());
    }
    @Test(timeout = TIMEOUT)
    public void testAddToEmpty3() {
        csll.addToBack(1);
        assertEquals(1, (int) csll.getHead().getData());
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront1() {
        csll.addToFront(1);
        csll.addToFront(2);
        assertEquals(2, (int) (csll.getHead().getData()));
        assertEquals(1, (int) (csll.getHead().getNext().getData()));
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront2() {
        csll.addToFront(1);
        csll.addToFront(2);
        csll.addToFront(3);
        csll.addToFront(4);
        assertEquals(csll.size(), 4);
        int[] array = {4, 3, 2, 1};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront3() {
        csll.addToFront(1);
        csll.addToFront(2);
        csll.addToFront(3);
        csll.addToBack(3);
        int[] array = {3, 2, 1, 3};
        testHelperPrintCsll();
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront4() {
        csll.addToFront(1);
        assertEquals((int) csll.getHead().getData(), 1);
        csll.addToFront(2);
        assertEquals((int) csll.getHead().getData(), 2);
        csll.addToFront(3);
        assertEquals((int) csll.getHead().getData(), 3);
        csll.addToBack(3);
        assertEquals((int) csll.getHead().getData(), 3);
        int[] array = {3, 2, 1, 3};
        testHelperPrintCsll();
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex1() {
        csll.addAtIndex(0, 1);
        csll.addAtIndex(0, 2);
        int[] array = {2, 1};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2() {
        csll.addAtIndex(0, 1);
        csll.addAtIndex(1, 2);
        int[] array = {1, 2};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex3() {
        csll.addAtIndex(0, 1);
        assertEquals((int) csll.getHead().getData(), 1);
        csll.addAtIndex(0, 2);
        assertEquals((int) csll.getHead().getData(), 2);
        csll.addAtIndex(1, 3);
        assertEquals((int) csll.getHead().getData(), 2);
        csll.addAtIndex(1, 4);
        testHelperPrintCsll();
        int[] array = {2, 4, 3, 1};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex4() {
        csll.addAtIndex(0, 1);
        csll.addAtIndex(0, 2);
        csll.addAtIndex(1, 3);
        csll.addAtIndex(1, 4);
        csll.addAtIndex(4, 10);
        csll.addAtIndex(3, 2);
        testHelperPrintCsll();
        int[] array = {2, 4, 3, 2, 1, 10};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex5() {
        csll.addAtIndex(0, 1);
        csll.addAtIndex(1, 2);
        csll.addAtIndex(1, 3);
        csll.addAtIndex(1, 4);
        csll.addAtIndex(4, 10);
        csll.addAtIndex(3, 45);
        csll.addAtIndex(6, 22);
        csll.addAtIndex(5, 7);
        csll.addAtIndex(3, 0);
        int[] array = {1, 4, 3, 0, 45, 2, 7, 10, 22};
        testHelperCompareValues(array);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack1() {
        csll.addToBack(1);
        csll.addToBack(2);
        csll.addToFront(1);
        int[] array = {1, 1, 2};
        testHelperPrintCsll();
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddToBack2() {
        csll.addToBack(1);
        csll.addToBack(2);
        csll.addToBack(1);
        int[] array = {1, 2, 1};
        testHelperPrintCsll();
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testAddToBack3() {
        csll.addToBack(1);
        csll.addToBack(2);
        csll.removeFromFront();
        csll.addToBack(3);
        csll.removeFromFront();
        csll.addToBack(3);
        int[] array = {3, 3};
        testHelperPrintCsll();
        testHelperCompareValues(array);
    }


    @Test(timeout = TIMEOUT)
    public void testHelperCompareValues(int[] expectedArray) {
        assertEquals(csll.size(), expectedArray.length);
        CircularSinglyLinkedListNode current = csll.getHead();
        for (int i = 0; i < csll.size(); i++) {
            assertEquals(current.getData(), expectedArray[i]);
            current = current.getNext();
        }
    }
    @Test(timeout = TIMEOUT)
    public void testHelperPrintCsll() {
        for (int i = 0; i < csll.size(); i++) {
            System.err.print(csll.get(i) + " ");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCombination() {
        for (int i = 0; i < 10; i++) {
            csll.addAtIndex(i, i);
        }
        csll.removeFromFront();
        csll.removeFromBack();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        testHelperCompareValues(array);
    }
    @Test(timeout = TIMEOUT)
    public void testCombination1() {
        for (int i = 0; i < 10; i++) {
            csll.addAtIndex(i, i);
        }
        csll.removeFromFront();
        csll.removeFromBack();
        csll.removeAtIndex(1);
        csll.addAtIndex(3, 5);
        int[] array = {1, 3, 4, 5, 5, 6, 7, 8};
        testHelperCompareValues(array);
        assertEquals((int)csll.get(7), 8);
        assertEquals((int)csll.get(0), 1);
        assertEquals((int)csll.get(3), 5);

    }
    public void testGet() {
        csll.addToFront(0);
        assertEquals((int)csll.get(0), 0);
    }

    public void testIsEmpty() {
        for(int i = 0; i < 5; i++) {
            csll.addToBack(i);
        }
        csll.removeFromBack();
        csll.addAtIndex(0, 1);
        int[] array = {1, 0, 1, 2, 3};
        testHelperCompareValues(array);
        assertEquals(csll.isEmpty(), false);
    }

    public void testClear() {
        for (int i = 0; i < 3; i++) {
            csll.addToFront(i);
            csll.addAtIndex(i, i);
        }
        int[] array = {2, 1, 2, 1, 0, 0,};
        testHelperCompareValues(array);
        csll.clear();
        assertEquals(csll.getHead(), null);
        assertEquals(csll.size(), 0);
    }

    public void testRemoveLastOccurrence() {
        for (int i = 0; i < 3; i++) {
            csll.addToFront(i);
        }
        int[] array = {2, 1, 0};
        testHelperCompareValues(array);
        int last = csll.removeLastOccurrence(2);
        int[] array2 = {1, 0};
        testHelperCompareValues(array2);
    }
    public void testRemoveLastOccurrence1() {
        for (int i = 0; i < 4; i++) {
            csll.addToFront(i);
        }
        int[] array = {3, 2, 1, 0};
        testHelperCompareValues(array);
        testHelperPrintCsll();
        int last = csll.removeLastOccurrence(2);
        testHelperPrintCsll();
        int[] array2 = {3, 1, 0};
        testHelperCompareValues(array2);
    }
    public void testRemoveLastOccurrence2() {
        for (int i = 0; i < 4; i++) {
            csll.addToFront(i);
        }
        csll.addToBack(3);
        int[] array = {3, 2, 1, 0, 3};

        testHelperCompareValues(array);
        testHelperPrintCsll();
        int last = csll.removeLastOccurrence(3);
        testHelperPrintCsll();
        int[] array2 = {3, 2, 1, 0};
        testHelperCompareValues(array2);
    }
    public void testRemoveLastOccurrence3() {
        for (int i = 0; i < 4; i++) {
            csll.addToFront(i);
        }
        csll.addToBack(3);
        csll.addToBack(3);
        int[] array = {3, 2, 1, 0, 3, 3};
        testHelperCompareValues(array);
        int last = csll.removeLastOccurrence(3);
        int[] array2 = {3, 2, 1, 0, 3};
        testHelperCompareValues(array2);
    }
    public void testRemoveLastOccurrence4() {
        for (int i = 0; i < 4; i++) {
            csll.addToFront(i);
        }
        csll.addAtIndex(0, 4);
        csll.addAtIndex(2, 4);
        csll.addAtIndex(4, 4);
        int[] array = {4, 3, 4, 2, 4, 1, 0};
        testHelperCompareValues(array);
        csll.removeLastOccurrence(4);
        int[] expectedArray = {4, 3, 4, 2, 1, 0};
    }
}