import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/*
 * In the event that there is an error found in these tests or an
 * untested edge case is found, this test file may be updated. Check
 * back on the same gist later for updates.
 */

public class Hamby {

    private static final int TIMEOUT = 200;
    private BST<Integer> bst;

    private Integer[] tree1 = {20, 30, 40, 50, 60};
    private Integer[] tree2 = {20, 15, 10, 5, 8};
    private Integer[] tree3 = {2};
    private Integer[] tree4 = {50, 30, 1, 85, 100, 40, 55, 80, 39, 86, 53, 38, 99};
    private Integer[] tree5 = {15, 20, 6, 16, 23};

    @Before
    public void setUp() throws Exception {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertNull(bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        bst = new BST<>(Arrays.asList(tree1));

        Integer[] tree1Preorder = {20, 30, 40, 50, 60};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree1Preorder.length; i++) {
            assertEquals(pre.get(i), tree1Preorder[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }

        assertEquals(bst.size(), 5);

        Integer[] tree1Preorder = {20, 30, 40, 50, 60};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree1Preorder.length; i++) {
            assertEquals(pre.get(i), tree1Preorder[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }

        assertEquals(bst.size(), 5);

        Integer[] tree2Preorder = {20, 15, 10, 5, 8};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree2Preorder.length; i++) {
            assertEquals(pre.get(i), tree2Preorder[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }

        assertEquals(bst.size(), 1);

        Integer[] tree3Preorder = {2};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree3Preorder.length; i++) {
            assertEquals(pre.get(i), tree3Preorder[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }

        assertEquals(bst.size(), 13);

        Integer[] tree4Preorder = {50, 30, 1, 40, 39, 38, 85, 55, 53, 80, 100, 86, 99};

        List<Integer> pre = bst.preorder();

        for (int i = 0; i < tree4Preorder.length; i++) {
            assertEquals(pre.get(i), tree4Preorder[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }

        assertEquals(bst.size(), 5);

        assertEquals(bst.getRoot().getData(), Integer.valueOf(15));
        assertEquals(bst.getRoot().getLeft().getData(), Integer.valueOf(6));
        assertEquals(bst.getRoot().getRight().getData(), Integer.valueOf(20));
        assertEquals(bst.getRoot().getRight().getLeft().getData(),
                Integer.valueOf(16));
        assertEquals(bst.getRoot().getRight().getRight().getData(),
                Integer.valueOf(23));
        assertNull(bst.getRoot().getLeft().getLeft());
        assertNull(bst.getRoot().getLeft().getRight());
        assertNull(bst.getRoot().getRight().getLeft().getLeft());
        assertNull(bst.getRoot().getRight().getLeft().getRight());
        assertNull(bst.getRoot().getRight().getRight().getLeft());
        assertNull(bst.getRoot().getRight().getRight().getRight());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        bst.add(1);
        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(bst.size(), 13);

        assertEquals(bst.remove(85), Integer.valueOf(85));
        Integer[] desired1 = {50, 30, 1, 40, 39, 38, 86, 55, 53, 80, 100, 99};
        assertArrayEquals(bst.preorder().toArray(), desired1);
        assertEquals(bst.size(), 12);

        bst.remove(99);

        assertEquals(bst.remove(30), Integer.valueOf(30));
        Integer[] desired2 = {50, 38, 1, 40, 39, 86, 55, 53, 80, 100};
        assertArrayEquals(bst.preorder().toArray(), desired2);
        assertEquals(bst.size(), 10);

        assertEquals(bst.remove(86), Integer.valueOf(86));
        Integer[] desired3 = {50, 38, 1, 40, 39, 100, 55, 53, 80};
        assertArrayEquals(bst.preorder().toArray(), desired3);
        assertEquals(bst.size(), 9);

        assertEquals(bst.remove(100), Integer.valueOf(100));
        Integer[] desired4 = {50, 38, 1, 40, 39, 55, 53, 80};
        assertArrayEquals(bst.preorder().toArray(), desired4);
        assertEquals(bst.size(), 8);

        assertEquals(bst.remove(1), Integer.valueOf(1));
        Integer[] desired5 = {50, 38, 40, 39, 55, 53, 80};
        assertArrayEquals(bst.preorder().toArray(), desired5);
        assertEquals(bst.size(), 7);

        assertEquals(bst.remove(38), Integer.valueOf(38));
        Integer[] desired6 = {50, 40, 39, 55, 53, 80};
        assertArrayEquals(bst.preorder().toArray(), desired6);
        assertEquals(bst.size(), 6);

        assertEquals(bst.remove(50), Integer.valueOf(50));
        Integer[] desired7 = {53, 40, 39, 55, 80};
        assertArrayEquals(bst.preorder().toArray(), desired7);
        assertEquals(bst.size(), 5);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEquality() {
        BST<String> stringBST = new BST<>();
        stringBST.add(new String("a"));
        String toRemove = new String("a");
        String removed = stringBST.remove(toRemove);
        assertFalse(removed == toRemove);

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        bst.add(5);
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotPresent() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.remove(49);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(bst.get(50), Integer.valueOf(50));
        assertEquals(bst.get(100), Integer.valueOf(100));
        assertEquals(bst.get(30), Integer.valueOf(30));
        assertFalse(bst.get(1) == new Integer(1));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotPresent() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.get(98);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertTrue(bst.contains(50));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(80));
        assertFalse(bst.contains(0));
        assertFalse(bst.contains(1000));
        assertFalse(bst.contains(56));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        bst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }

        Integer[] desired = {50, 30, 1, 40, 39, 38, 85, 55, 53, 80, 100, 86, 99};

        assertArrayEquals(bst.preorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {15, 6, 20, 16, 23};
        assertArrayEquals(bst.preorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {20, 30, 40, 50, 60};
        assertArrayEquals(bst.inorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {5, 8, 10, 15, 20};
        assertArrayEquals(bst.inorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(bst.inorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {1, 30, 38, 39, 40, 50, 53, 55, 80, 85, 86, 99, 100};
        assertArrayEquals(bst.inorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testInorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {6, 15, 16, 20, 23};
        assertArrayEquals(bst.inorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {60, 50, 40, 30, 20};
        assertArrayEquals(bst.postorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {8, 5, 10, 15, 20};
        assertArrayEquals(bst.postorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(bst.postorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {1, 38, 39, 40, 30, 53, 80, 55, 99, 86, 100, 85, 50};
        assertArrayEquals(bst.postorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {6, 16, 23, 20, 15};
        assertArrayEquals(bst.postorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree1() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        Integer[] desired = {20, 30, 40, 50, 60};
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree2() {
        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        Integer[] desired = {20, 15, 10, 5, 8};
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree3() {
        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        Integer[] desired = {2};
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree4() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        Integer[] desired = {50, 30, 85, 1, 40, 55, 100, 39, 53, 80, 86, 38, 99};
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderTree5() {
        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        Integer[] desired = {15, 6, 20, 16, 23};
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testOrderEmpty() {
        Integer[] desired = {};
        assertArrayEquals(bst.preorder().toArray(), desired);
        assertArrayEquals(bst.inorder().toArray(), desired);
        assertArrayEquals(bst.postorder().toArray(), desired);
        assertArrayEquals(bst.levelorder().toArray(), desired);
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(bst.size(), 13);
        bst.clear();
        assertEquals(bst.size(), 0);
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        for (Integer i : Arrays.asList(tree1)) {
            bst.add(i);
        }
        assertEquals(bst.height(), 4);
        bst.clear();

        for (Integer i : Arrays.asList(tree2)) {
            bst.add(i);
        }
        assertEquals(bst.height(), 4);
        bst.clear();

        for (Integer i : Arrays.asList(tree3)) {
            bst.add(i);
        }
        assertEquals(bst.height(), 0);
        bst.clear();

        for (Integer i : Arrays.asList(tree4)) {
            bst.add(i);
        }
        assertEquals(bst.height(), 4);
        bst.clear();

        for (Integer i : Arrays.asList(tree5)) {
            bst.add(i);
        }
        assertEquals(bst.height(), 2);
    }

}