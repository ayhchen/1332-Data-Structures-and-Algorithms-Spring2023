import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class JUnitSahi {
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
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalData() {
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void enqueueZeroElements() {
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        for (int i = 0; i < expected.length; i++) {
            assertNull(expected[i]);
        }
        assertEquals(0, array.size());
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void enqueueOneElement() {
        array.enqueue("a");
        assertSame("a", array.peek());
        assertEquals(1, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "a";
        for (int i = 1; i < expected.length; i++) {
            assertNull(expected[i]);
        }
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void enqueueTwoElements() {
        array.enqueue("a");
        array.enqueue("b");
        assertSame("a", array.peek());
        assertEquals(2, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        for (int i = 2; i < expected.length; i++) {
            assertNull(expected[i]);
        }
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void enqueueCapacityMinusOneElements() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        assertSame("a", array.peek());
        assertEquals(8, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        for (int i = 8; i < expected.length; i++) {
            assertNull(expected[i]);
        }
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void enqueueCapacityElements() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertSame("a", array.peek());
        assertEquals(9, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";

        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }


    @Test(timeout = TIMEOUT)
    public void enqueueCapacityElementsOneResize() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());

        for (int i = 9; i < 12; i++) {
            array.enqueue(i + "z");
        }

        assertEquals(12, array.size());
        assertSame("a", array.peek());
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";

        for (int i = 9; i < 12; i++) {
            expected[i] = i + "z";
        }

        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoUnwrappingDequeueSizeZero() {
        array.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueAllSizeOne() {
        array.enqueue("a");
        assertEquals(1, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        assertEquals(0, array.size());
        assertEquals(1, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testNoUnwrappingDequeueAllSizeOnePeek() {
        array.enqueue("a");
        assertEquals(1, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("a", array.peek());
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        assertEquals(0, array.size());
        assertEquals(1, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueAllSizeTwo() {
        array.enqueue("a");
        array.enqueue("b");
        assertSame("a", array.peek());
        assertEquals(2, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.peek());
        assertEquals(1, array.size());
        assertEquals(1, array.getFront());
        assertSame("b", array.dequeue());
        assertEquals(2, array.getFront());
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        assertEquals(0, array.size());
        assertEquals(2, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueOneElement() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        assertEquals(8, array.size());
        assertEquals(1, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueTwoElements() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.dequeue());
        assertSame("c", array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        assertEquals(7, array.size());
        assertEquals(2, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueUntilSizeOne() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.dequeue());
        assertSame("c", array.dequeue());
        assertSame("d", array.dequeue());
        assertSame("e", array.peek());
        assertSame("e", array.dequeue());
        assertSame("f", array.dequeue());
        assertSame("g", array.dequeue());
        assertSame("h", array.peek());
        assertSame("h", array.dequeue());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = "i";
        assertEquals(1, array.size());
        assertEquals(8, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueUntilSizeTwp() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.dequeue());
        assertSame("c", array.dequeue());
        assertSame("d", array.peek());
        assertSame("d", array.dequeue());
        assertSame("e", array.dequeue());
        assertSame("f", array.dequeue());
        assertSame("g", array.dequeue());
        assertSame("h", array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = "h";
        expected[8] = "i";
        assertEquals(2, array.size());
        assertEquals(7, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingDequeueAllElements() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertSame("b", array.dequeue());
        assertSame("c", array.dequeue());
        assertSame("d", array.dequeue());
        assertSame("e", array.dequeue());
        assertSame("f", array.dequeue());
        assertSame("g", array.dequeue());
        assertSame("h", array.dequeue());
        assertSame("i", array.dequeue());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertEquals(0, array.size());
        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testNoUnwrappingEnqueueThenDequeueThenEnqueueMaintainCapacityMultiple() {
        array.enqueue("a"); // a 
        array.enqueue("b"); // a b 
        array.enqueue("c"); // a b c  
        array.enqueue("d"); // a b c d 
        array.enqueue("e"); // a b c d e 
        array.enqueue("f"); // a b c d e f 
        array.enqueue("g"); // a b c d e f g 
        array.enqueue("h"); // a b c d e f g h  
        array.enqueue("i"); // a b c d e f g h i 
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue()); // null b c d e f g h i
        assertSame("b", array.dequeue()); // null null c d e f g h i
        assertSame("c", array.dequeue()); // null null null d e f g h i
        assertSame("d", array.dequeue()); // null null null null e f g h i
        assertEquals(5, array.size());
        assertEquals(4, array.getFront());
        assertSame("e", array.peek());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        assertArrayEquals(expected, array.getBackingArray());

        array.enqueue("j"); // j null null null e f g h i
        array.enqueue("k"); // j k null null e f g h i
        array.enqueue("l"); // j k l null e f g h i
        assertSame("e", array.peek());
        array.enqueue("m"); // j k l m e f g h i
        assertEquals(9, array.size());
        assertEquals(4, array.getFront());

        assertSame("e", array.dequeue()); // j k l m null f g h i
        assertEquals(8, array.size());
        assertEquals(5, array.getFront());
        assertSame("f", array.peek());

        expected[0] = "j";
        expected[1] = "k";
        expected[2] = "l";
        expected[3] = "m";
        expected[4] = null;
        assertArrayEquals(expected, array.getBackingArray());

        assertSame("f", array.dequeue()); // j k l m null null g h i
        assertEquals(7, array.size());
        assertEquals(6, array.getFront());

        assertSame("g", array.dequeue()); // j k l m null null null h i
        assertEquals(6, array.size());
        assertEquals(7, array.getFront());

        assertSame("h", array.dequeue()); // j k l m null null null null i
        assertEquals(5, array.size());
        assertEquals(8, array.getFront());
        assertSame("i", array.peek());

        assertSame("i", array.dequeue()); // j k l m null null null null null
        assertEquals(4, array.size());
        assertEquals(0, array.getFront());
        assertSame("j", array.peek());

        assertSame("j", array.dequeue()); // null k l m null null null null null
        assertEquals(3, array.size());
        assertEquals(1, array.getFront());
        assertSame("k", array.peek());

        array.enqueue("n");; // null k l m n null null null null
        assertEquals(4, array.size());
        assertEquals(1, array.getFront());

        expected[0] = null;
        expected[4] = "n";
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        assertSame("k", array.dequeue()); // null null l m n null null null null

        expected[0] = null;
        expected[1] = null;
        expected[2] = "l";
        expected[3] = "m";
        expected[4] = "n";
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testUnwrapOnlyEnqueue() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        array.enqueue("j");
        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        expected[9] = "j";

        assertEquals(0, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testUnwrapEnqueueOneDequeue() {
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.enqueue("f");
        array.enqueue("g");
        array.enqueue("h");
        array.enqueue("i");
        array.enqueue("j");
        assertEquals(10, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue());
        assertEquals(9, array.size());


        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = null;
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        expected[9] = "j";

        assertEquals(1, array.getFront());
        assertArrayEquals(expected, array.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void testUnwrappingEnqueueThenDequeueThenEnqueueMultiple() {
        array.enqueue("a"); // a 
        array.enqueue("b"); // a b 
        array.enqueue("c"); // a b c  
        array.enqueue("d"); // a b c d 
        array.enqueue("e"); // a b c d e 
        array.enqueue("f"); // a b c d e f 
        array.enqueue("g"); // a b c d e f g 
        assertSame("a", array.peek());
        array.enqueue("h"); // a b c d e f g h  
        array.enqueue("i"); // a b c d e f g h i 
        assertEquals(9, array.size());
        assertEquals(0, array.getFront());
        assertSame("a", array.dequeue()); // null b c d e f g h i  
        assertSame("b", array.dequeue()); // null null c d e f g h i 
        assertSame("c", array.dequeue()); // null null null d e f g h i  
        assertSame("d", array.peek());
        assertSame("d", array.dequeue()); // null null null null e f g h i 
        assertEquals(5, array.size());
        assertEquals(4, array.getFront());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        assertArrayEquals(expected, array.getBackingArray());

        array.enqueue("l"); // l null null null e f g h i  
        array.enqueue("m"); // l m null null e f g h i  
        array.enqueue("n"); // l m n null e f g h i 
        assertSame("e", array.peek());
        array.enqueue("o"); // l m n o e f g h i 
        assertEquals(9, array.size());
        assertEquals(4, array.getFront());

        array.enqueue("p");
        //unwrapped : e f g h i l m n o p

        Object[] expectedNew = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expectedNew[0] = "e";
        expectedNew[1] = "f";
        expectedNew[2] = "g";
        expectedNew[3] = "h";
        expectedNew[4] = "i";
        expectedNew[5] = "l";
        expectedNew[6] = "m";
        expectedNew[7] = "n";
        expectedNew[8] = "o";
        expectedNew[9] = "p";
        assertArrayEquals(expectedNew, array.getBackingArray());

        assertEquals(10, array.size());
        assertEquals(0, array.getFront());
        array.enqueue("q"); // e f g h i l m n o p q
        assertEquals(11, array.size());
        assertEquals(0, array.getFront());
        assertSame("e", array.peek());

        assertSame("e", array.dequeue()); // null f g h i l m n o p q
        assertEquals(10, array.size());
        assertEquals(1, array.getFront());

        assertSame("f", array.dequeue()); // null null g h i l m n o p q
        assertSame("g", array.peek());
        assertEquals(9, array.size());
        assertEquals(2, array.getFront());

        expectedNew[0] = null;
        expectedNew[1] = null;
        expectedNew[10] = "q";

        assertArrayEquals(expectedNew, array.getBackingArray());

        array.enqueue("r"); // null null g h i l m n o p q r
        assertEquals(10, array.size());
        assertEquals(2, array.getFront());

        array.enqueue("s"); // null null g h i l m n o p q r s
        assertEquals(11, array.size());
        assertEquals(2, array.getFront());

        assertSame("g", array.dequeue()); // null null null h i l m n o p q r s
        assertEquals(10, array.size());
        assertEquals(3, array.getFront());
        assertSame("h", array.peek());
        assertSame("h", array.dequeue()); // null null null null i l m n o p q r s
        assertEquals(9, array.size());
        assertEquals(4, array.getFront());
        assertSame("i", array.dequeue()); // null null null null null l m n o p q r s
        assertSame("l", array.peek());
        assertEquals(8, array.size());
        assertEquals(5, array.getFront());

        expectedNew[2] = null;
        expectedNew[3] = null;
        expectedNew[4] = null;
        expectedNew[10] = "q";
        expectedNew[11] = "r";
        expectedNew[12] = "s";
        assertArrayEquals(expectedNew, array.getBackingArray());


        array.enqueue("t"); // null null null null null l m n o p q r s t
        assertEquals(9, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("u"); // null null null null null l m n o p q r s t u
        assertEquals(10, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("v"); // null null null null null l m n o p q r s t u v
        assertEquals(11, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("a"); // null null null null null l m n o p q r s t u v a
        assertEquals(12, array.size());
        assertEquals(5, array.getFront());
        assertSame("l", array.peek());
        array.enqueue("b"); // null null null null null l m n o p q r s t u v a b
        assertEquals(13, array.size());
        assertEquals(5, array.getFront());

        expectedNew[13] = "t";
        expectedNew[14] = "u";
        expectedNew[15] = "v";
        expectedNew[16] = "a";
        expectedNew[17] = "b";
        assertArrayEquals(expectedNew, array.getBackingArray());

        array.enqueue("w"); // w null null null null l m n o p q r s t u v a b
        assertSame("l", array.peek());
        assertEquals(14, array.size());
        assertEquals(5, array.getFront());

        expectedNew[0] = "w";
        assertArrayEquals(expectedNew, array.getBackingArray());

        array.enqueue("x"); // w x null null null l m n o p q r s t u v a b
        assertEquals(15, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("y"); // w x y null null l m n o p q r s t u v a b
        assertEquals(16, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("z"); // w x y z null l m n o p q r s t u v a b
        assertEquals(17, array.size());
        assertEquals(5, array.getFront());
        array.enqueue("j"); // w x y z j l m n o p q r s t u v a b
        assertSame("l", array.peek());
        assertEquals(18, array.size());
        assertEquals(5, array.getFront());

        array.enqueue("k");
        //unwrapped : l m n o p q r s t u v a b w x y z j k
        assertSame("l", array.peek());
        assertEquals(19, array.size());
        assertEquals(0, array.getFront());

        Object[] expectedNew2 = new Object[(ArrayQueue.INITIAL_CAPACITY * 2) * 2];
        expectedNew2[0] = "l";
        expectedNew2[1] = "m";
        expectedNew2[2] = "n";
        expectedNew2[3] = "o";
        expectedNew2[4] = "p";
        expectedNew2[5] = "q";
        expectedNew2[6] = "r";
        expectedNew2[7] = "s";
        expectedNew2[8] = "t";
        expectedNew2[9] = "u";
        expectedNew2[10] = "v";
        expectedNew2[11] = "a";
        expectedNew2[12] = "b";
        expectedNew2[13] = "w";
        expectedNew2[14] = "x";
        expectedNew2[15] = "y";
        expectedNew2[16] = "z";
        expectedNew2[17] = "j";
        expectedNew2[18] = "k";
        assertNull(expectedNew2[19]);
        assertNull(expectedNew2[20]);
        assertNull(expectedNew2[21]);
        assertArrayEquals(expectedNew2, array.getBackingArray());

    }

}