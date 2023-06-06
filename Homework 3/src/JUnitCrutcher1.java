import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/** Custom testing class for Linked-List based stack :)
 * Short but hopefully sweet!
 * Please look elsewhere for exceptions testing for literally any JUnits I write, though
 * @author Kaitlyn
 * @version 1.0
 */
public class JUnitCrutcher1 {
    private static final int TIMEOUT = 200;
    private LinkedStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new LinkedStack();
    }

    @Test(timeout = TIMEOUT)
    public void testEmpty() {
        assertEquals(0, stack.size());
        stack.push(0);
        assertEquals(1, stack.size());
        assertEquals(0, (int) stack.pop());
        assertEquals(0, stack.size());
    }
    @Test(timeout = TIMEOUT)
    public void testPushHead() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            assertEquals(i, (int) stack.getHead().getData());
        }
    }
    @Test(timeout = TIMEOUT)
    public void testPush() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            assertEquals(i + 1, stack.size());
            assertEquals(i, (int) stack.getHead().getData());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(4 - i, (int) stack.getHead().getData());
            stack.pop();
            assertEquals(4 - i, stack.size());
        }
        assertEquals(null, stack.getHead());
        assertEquals(0, stack.size());
    }
    @Test(timeout = TIMEOUT)
    public void testPull() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            assertEquals(1, stack.size());
            int data = (int) stack.pop();
            assertEquals(0, stack.size());
            assertEquals(i, data);
        }
    }
    @Test(timeout = TIMEOUT)
    public void testPull2() {
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(6);
        assertEquals(5, stack.size());
        assertEquals(6, (int) stack.getHead().getData());
        stack.pop();
        assertEquals(2, (int) stack.getHead().getData());
    }
    @Test(timeout = TIMEOUT)
    public void testPeek() {
        for (int i = 0; i < 6; i++) {
            stack.push(i);
            assertEquals(i, (int) stack.peek());
        }
        stack.pop();
        assertEquals(4, (int) stack.peek());
    }
}