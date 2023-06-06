import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Custom testing class for ArrayStack. This class is zero aligned and an array, so there shouldn't be too many edges.
 * @author Kaitlyn
 * @version 1.0
 */
public class JUnitCrutcher2 {
    private static final int TIMEOUT = 200;
    private ArrayStack<Integer> stack;
    @Before
    public void setUp() {
        stack = new ArrayStack();
    }
    @Test(timeout = TIMEOUT)
    public void testEmpty() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, (int) stack.peek());
        assertEquals(1, stack.size());
        int data = stack.pop();
        assertEquals(1, data);
        assertEquals(0, stack.size());
    }
    @Test(timeout = TIMEOUT)
    public void testPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, (int) stack.peek());
        stack.pop();
        assertEquals(2, stack.size());
        assertEquals(2, (int) stack.peek());
        stack.pop();
        assertEquals(1, stack.size());
        assertEquals(1, (int) stack.peek());
        stack.pop();
        assertEquals(0, stack.size());
    }
    public void testPeek() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, (int) stack.peek());
        stack.pop();
        assertEquals(2, stack.size());
        stack.push(2);
        assertEquals(2, (int) stack.peek());
    }
}