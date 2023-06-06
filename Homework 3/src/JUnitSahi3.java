import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class JUnitSahi3 {
    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalData() {
        linked.push(null);
    }


    @Test(timeout = TIMEOUT)
    public void testLinkedPushSizeZeroOneElement() {
        linked.push("a");
        assertEquals(1, linked.size());
        assertSame("a", linked.peek());

        LinkedNode<String> cur = linked.getHead();

        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushSizeOneOneElement() {
        linked.push("a");
        linked.push("b");
        assertEquals(2, linked.size());

        LinkedNode<String> cur = linked.getHead();

        assertNotNull(cur);
        assertEquals("b", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushSizeOneTwoElements() {
        linked.push("a");
        linked.push("b");
        assertSame("b", linked.peek());
        linked.push("c");
        assertEquals(3, linked.size());

        LinkedNode<String> cur = linked.getHead();

        assertNotNull(cur);
        assertEquals("c", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("b", cur.getData());
        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushLotsElements() {
        linked.push("a");
        linked.push("b");
        linked.push("c");
        assertSame("c", linked.peek());
        linked.push("d");
        linked.push("e");
        linked.push("f");
        linked.push("g");
        linked.push("h");
        assertSame("h", linked.peek());
        linked.push("i");
        linked.push("j");
        assertSame("j", linked.peek());
        linked.push("k");
        linked.push("l");
        linked.push("m");
        linked.push("n");
        linked.push("o");
        assertSame("o", linked.peek());
        linked.push("p");
        linked.push("q");
        linked.push("r");
        assertEquals(18, linked.size());

        LinkedNode<String> cur = linked.getHead();
        String[] elements = {"r","q","p","o","n","m", "l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a"};
        for (int i = 0; i < elements.length; i++) {
            assertNotNull(cur);
            assertEquals(elements[i], cur.getData());
            cur = cur.getNext();
        }
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPopSizeZeroOneElement() {
        linked.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopSizeOneOneElement() {
        linked.push("a");
        assertEquals(1, linked.size());

        assertSame("a", linked.pop());
        assertEquals(0, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopSizeTwoOneElement() {
        linked.push("a");
        linked.push("b");
        assertEquals(2, linked.size());
        assertSame("b", linked.pop());
        assertEquals(1, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("a", cur.getData());
        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopSizeTwoTwoElements() {
        linked.push("a");
        linked.push("b");
        assertEquals(2, linked.size());
        assertSame("b", linked.pop());
        assertSame("a", linked.pop());
        assertEquals(0, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushPopMixLotsElements() {
        linked.push("a"); // a
        linked.push("b"); // b a
        assertSame("b", linked.peek());
        linked.push("c"); // c b a
        linked.push("d"); // d c b a
        assertSame("d", linked.peek());
        assertEquals(4, linked.size());
        assertSame("d", linked.pop()); // c b a 
        assertSame("c", linked.peek());
        assertEquals(3, linked.size());
        linked.push("f"); // f c b a 
        linked.push("g"); // g f c b a
        assertEquals(5, linked.size());
        assertSame("g", linked.pop()); // f c b a 
        assertSame("f", linked.pop()); //c b a
        assertSame("c", linked.peek());
        assertEquals(3, linked.size());
        linked.push("j"); // j c b a
        linked.push("k"); // k j c b a
        assertSame("k", linked.peek());
        linked.push("l"); // l k d c b a
        assertEquals(6, linked.size());
        assertSame("l", linked.pop()); // k j c b a 
        assertSame("k", linked.peek());
        assertSame("k", linked.pop()); // j c b a
        assertSame("j", linked.pop()); // c b a
        assertEquals(3, linked.size());
        linked.push("p"); // p c b a
        linked.push("q"); // q p c b a
        linked.push("r"); // r q p c b a
        assertSame("r", linked.peek());
        assertEquals(6, linked.size());

        LinkedNode<String> cur = linked.getHead();
        String[] elements = {"r","q","p","c","b", "a"};
        for (int i = 0; i < elements.length; i++) {
            assertNotNull(cur);
            assertEquals(elements[i], cur.getData());
            cur = cur.getNext();
        }
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedPushPopPeekException() {
        linked.push("a");
        linked.push("b");
        assertEquals(2, linked.size());
        assertSame("b", linked.pop());
        assertSame("a", linked.pop());
        linked.peek();
        assertEquals(0, linked.size());
        LinkedNode<String> cur = linked.getHead();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPeekEmptyList() {
        linked.peek();
    }

}