import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 *
 * @author Yu-Hsin Chen
 * @version 11.0.16.1
 * @userid ychen3558
 * @GTID 903789607
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        size = 0;
        for (T dataInCollection : data) {
            if (dataInCollection == null) {
                throw new IllegalArgumentException("Data is null");
            } else {
                add(dataInCollection);
            }
        }

    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        root = addHelper(data, root);
    }

    /**
     * Helper and recursive method that traverses to the appropriate
     * location to add data to the tree.
     * @param data the data to add
     * @param current the current node that is to be compared to the
     *                child nodes to determine whether its location
     *                based on whether it is larger or smaller than the
     *                child nodes
     * @return the node that is being compared.
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> current) {
        if (current == null) {
            size = size + 1;
            return new BSTNode<T>(data);
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(addHelper(data, current.getRight()));
        }
        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        BSTNode<T> temp = new BSTNode<T>(null);
        root = removeHelper(data, root, temp);
        return temp.getData();
    }

    /**
     * Helper method to recursively remove and return the data from the
     * tree that is matches the given parameter.
     * @param data the data to remove
     * @param current the current node that is used to traverse the tree
     * @param temp a temporary node used to store the removed element
     * @return the current node from pointer reinforcement
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> current, BSTNode<T> temp) {
        if (current == null) {
            throw new NoSuchElementException("Data is not found in the tree");
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(removeHelper(data, current.getLeft(), temp));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(removeHelper(data, current.getRight(), temp));
        } else if (data.compareTo(current.getData()) == 0) {
            temp.setData(current.getData());
            size = size - 1;

            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            } else if (current.getLeft() != null && current.getRight() == null) {
                return current.getLeft();
            } else if (current.getLeft() == null && current.getRight() != null) {
                return current.getRight();
            } else if (current.getLeft() != null && current.getRight() != null) {
                BSTNode<T> temp2 = new BSTNode<T>(null);
                current.setRight(successorRemove(current.getRight(), temp2));
                current.setData(temp2.getData());
            }
        }
        return current;
    }

    /**
     * Helper method that recursively finds the position of the node to remove
     * when the node has two children using the successor method.
     * @param current the current node that is used to traverse the tree
     * @param temp a temporary node used to store the removed element
     * @return the current node from pointer reinforcement
     */
    private BSTNode<T> successorRemove(BSTNode<T> current, BSTNode<T> temp) {
        if (current.getLeft() == null) {
            temp.setData(current.getData());
            return current.getRight();
        } else {
            current.setLeft(successorRemove(current.getLeft(), temp));
            return current;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        return getHelper(data, root);
    }

    /**
     * Helper method that returns the data from the tree matching the
     * given parameter.
     * @param data the data to search for
     * @param current the current node that is being compared to search for
     *                the given data element
     * @return the data in the tree that is equal to the data parameter
     */
    private T getHelper(T data, BSTNode<T> current) {
        if (current == null) {
            throw new NoSuchElementException("Data is not found in the tree.");
        }
        if (data.compareTo(current.getData()) == 0) {
            return current.getData();
        } else if (data.compareTo(current.getData()) < 0) {
            return getHelper(data, current.getLeft());
        } else if (data.compareTo(current.getData()) > 0) {
            return getHelper(data, current.getRight());
        }
        return null;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        return containsHelper(data, root);
    }

    /**
     * Helper method that recursively goes through the tree to see if
     * the data in the parameter in contained in the tree.
     * @param data the data to search for
     * @param current the current node that is used to search for the
     *                data parameter
     * @return true if the data parameter is contained in the tree,
     * false otherwise
     */
    private boolean containsHelper(T data, BSTNode<T> current) {
        if (current == null) {
            return false;
        }
        if (data.compareTo(current.getData()) == 0) {
            return true;
        } else if (data.compareTo(current.getData()) < 0) {
            return containsHelper(data, current.getLeft());
        } else if (data.compareTo(current.getData()) > 0) {
            return containsHelper(data, current.getRight());
        }
        return false;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> preorderList = new LinkedList<T>();
        if (root != null) {
            preorderHelper(root, preorderList);
        }
        return preorderList;
    }

    /**
     * Helper method to recursively generate a pre-order
     * traversal of the tree.
     * @param current the current node used for traversal
     * @param preorderList the list containing elements in
     *                     preorder order
     */
    private void preorderHelper(BSTNode<T> current, List<T> preorderList) {
        preorderList.add(current.getData());
        if (current.getLeft() != null) {
            preorderHelper(current.getLeft(), preorderList);
        }
        if (current.getRight() != null) {
            preorderHelper(current.getRight(), preorderList);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> inorderList = new LinkedList<T>();
        if (root != null) {
            inorderHelper(root, inorderList);
        }
        return inorderList;
    }

    /**
     * Helper method to recursively generate an in-order
     * traversal of the tree.
     * @param current the current node used for traversal
     * @param inorderList the list containing elements in
     *                    in-order order
     */
    private void inorderHelper(BSTNode<T> current, List<T> inorderList) {
        if (current.getLeft() != null) {
            inorderHelper(current.getLeft(), inorderList);
        }
        inorderList.add(current.getData());
        if (current.getRight() != null) {
            inorderHelper(current.getRight(), inorderList);
        }
    }
    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> postorderList = new LinkedList<T>();
        if (root != null) {
            postorderHelper(root, postorderList);
        }
        return postorderList;
    }

    /**
     * Helper method to recursively generate a post-order
     * traversal of the tree.
     * @param current the current node used for traversal
     * @param postorderList the list containing elements in
     *                    post-order order
     */
    private void postorderHelper(BSTNode<T> current, List<T> postorderList) {
        if (current.getLeft() != null) {
            postorderHelper(current.getLeft(), postorderList);
        }
        if (current.getRight() != null) {
            postorderHelper(current.getRight(), postorderList);
        }
        postorderList.add(current.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> levelorderList = new LinkedList<T>();
        List<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();

        if (size != 0) {
            queue.add(root);
            while (!(queue.isEmpty())) {
                BSTNode<T> current = queue.remove(0);
                levelorderList.add(current.getData());
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
        }
        return levelorderList;
    }



    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return helperHeight(root);
    }

    /**
     * Helper method to recursively determine the height of
     * the root of the tree
     * @param current the current node used to determine the node's
     *                height
     * @return the height of the root of the tree, -1 if the tree
     * is empty
     */
    private int helperHeight(BSTNode<T> current) {
        if (current == null) {
            return -1;
        }
        int leftSideHeight = helperHeight(current.getLeft());
        int rightSideHeight = helperHeight(current.getRight());

        if (leftSideHeight > rightSideHeight) {
            return leftSideHeight + 1;
        } else {
            return rightSideHeight + 1;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Cannot return less than 0 elements.");
        } else if (k > size) {
            throw new IllegalArgumentException("Cannot return more elements than size.");
        }
        List<T> kList = new LinkedList<T>();
        reverseInorder(root, kList, k);
        return kList;
    }

    /**
     * Helper method to recursively generate k elements of a reversed
     * in-order traversal of the tree
     * @param current the current node used for traversal
     * @param list the list containing k elements in reversed in-order order
     * @param k the number of largest elements to return
     */
    private void reverseInorder(BSTNode<T> current, List<T> list, int k) {
        if (list.size() < k) {
            if (current.getRight() != null) {
                reverseInorder(current.getRight(), list, k);
            }
            if (list.size() < k) {
                list.add(0, current.getData());
                if (current.getLeft() != null) {
                    reverseInorder(current.getLeft(), list, k);
                }
            }
        }
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
