import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
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
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        for (T dataInCollection : data) {
            if (dataInCollection == null) {
                throw new IllegalArgumentException("Data is null");
            }
            add(dataInCollection);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
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
    private AVLNode<T> addHelper(T data, AVLNode<T> current) {
        if (current == null) {
            AVLNode<T> newNode = new AVLNode<T>(data);
            size = size + 1;
            newNode.setHeight(0);
            newNode.setBalanceFactor(0);
            return newNode;
        } else if (data.compareTo(current.getData()) > 0) {
            AVLNode<T> returnedNode = addHelper(data, current.getRight());
            current.setRight(returnedNode);
            updateHeight(current);
            return masterRotation(current);
        } else if (data.compareTo(current.getData()) < 0) {
            AVLNode<T> returnedNode = addHelper(data, current.getLeft());
            current.setLeft(returnedNode);
            updateHeight(current);
            return masterRotation(current);
        }
        return current;
    }

    /**
     * Helper method that updates the height and balanceFactor of the appropriate node
     * @param current the current node that is to be updated
     */
    private void updateHeight(AVLNode<T> current) {
        int leftHeight;
        int rightHeight;

        if (current.getLeft() == null) {
            leftHeight = -1;
        } else {
            leftHeight = current.getLeft().getHeight();
        }

        if (current.getRight() == null) {
            rightHeight = -1;
        } else {
            rightHeight = current.getRight().getHeight();
        }
        if (leftHeight >= rightHeight) {
            current.setHeight(leftHeight + 1);
        } else {
            current.setHeight(rightHeight + 1);
        }
        current.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Method that checks if the current node needs to be rotated
     * @param current the current node that is to be checked if needed for a rotation
     * @return the node at the appropriate location
     */
    private AVLNode<T> masterRotation(AVLNode<T> current) {
        if (current.getBalanceFactor() > 1) {
            if (current.getLeft().getBalanceFactor() >= 0) {
                return rightRotation(current);
            } else {
                current.setLeft(leftRotation(current.getLeft()));
                return rightRotation(current);
            }
        } else if (current.getBalanceFactor() < -1) {
            if (current.getRight().getBalanceFactor() <= 0) {
                return leftRotation(current);
            } else {
                current.setRight(rightRotation(current.getRight()));
                return leftRotation(current);
            }
        }
        return current;
    }

    /**
     * Method that does the left rotation
     * @param current the current node that is to be left rotated
     * @return the node at the appropriate location
     */
    private AVLNode<T> leftRotation(AVLNode<T> current) {
        AVLNode<T> rightNode = current.getRight();
        current.setRight(rightNode.getLeft());
        rightNode.setLeft(current);
        updateHeight(current);
        updateHeight(rightNode);
        return rightNode;
    }

    /**
     * Method that does the right rotation
     * @param current the current node that is to be right rotated
     * @return the node at the appropriate location
     */
    private AVLNode<T> rightRotation(AVLNode<T> current) {
        AVLNode<T> leftNode = current.getLeft();
        current.setLeft(leftNode.getRight());
        leftNode.setRight(current);
        updateHeight(current);
        updateHeight(leftNode);
        return leftNode;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }

        AVLNode<T> temp = new AVLNode<>(null);
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
    private AVLNode<T> removeHelper(T data, AVLNode<T> current, AVLNode<T> temp) {
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
                AVLNode<T> temp2 = new AVLNode<>(null);
                current.setLeft(predecessorRemove(current.getLeft(), temp2));
                current.setData(temp2.getData());
            }
        }
        updateHeight(current);
        return masterRotation(current);
    }

    /**
     * Helper method that recursively finds the position of the node to remove
     * when the node has two children using the predecessor method.
     * @param current the current node that is used to traverse the tree
     * @param temp a temporary node used to store the removed element
     * @return the current node from pointer reinforcement
     */
    private AVLNode<T> predecessorRemove(AVLNode<T> current, AVLNode<T> temp) {
        if (current.getRight() == null) {
            temp.setData(current.getData());
            return current.getLeft();
        } else {
            current.setRight(predecessorRemove(current.getRight(), temp));
            updateHeight(current);
            return masterRotation(current);
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
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
    private T getHelper(T data, AVLNode<T> current) {
        if (current == null) {
            throw new NoSuchElementException("Data is not in the tree");
        }
        if (data.compareTo(current.getData()) == 0) {
            return current.getData();
        } else if (data.compareTo(current.getData()) < 0) {
            return getHelper(data, current.getLeft());
        } else {
            return getHelper(data, current.getRight());
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
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
    private boolean containsHelper(T data, AVLNode<T> current) {
        if (current == null) {
            return false;
        }
        if (data.compareTo(current.getData()) == 0) {
            return true;
        } else if (data.compareTo(current.getData()) > 0) {
            return containsHelper(data, current.getRight());
        } else {
            return containsHelper(data, current.getLeft());
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * This must be done recursively.
     *
     * Your list should not have duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> deepestList = new LinkedList<>();
        deepHelper(deepestList, root);
        return deepestList;
    }

    /**
     * Helper method to add elements in branches of maximum depth to the list
     * @param list list containing elements in branches of maximum depth
     * @param current current node used to determine the maximum depth
     * @return list of data in branches of maximum depth in preorder traversal order
     */
    private List<T> deepHelper(List<T> list, AVLNode<T> current) {
        if (current == null) {
            return list;
        }
        if (current.getBalanceFactor() == 0) {
            list.add(current.getData());
            deepHelper(list, current.getLeft());
            deepHelper(list, current.getRight());
        } else if (current.getBalanceFactor() < 0) {
            list.add(current.getData());
            deepHelper(list, current.getRight());
        } else {
            list.add(current.getData());
            deepHelper(list, current.getLeft());
        }
        return list;
    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * This must be done recursively.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @return a sorted list of data that is > data1 and < data2
     * @throws IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("Data 1 is larger than data 2");
        }
        List<T> inBetweenList = new LinkedList<>();
        if (data1.compareTo(data2) == 0) {
            return inBetweenList;
        }
        inBetweenHelper(data1, data2, inBetweenList, root);
        return inBetweenList;
    }

    /**
     * Helper method to find the data in between of the two given data range
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @param inBetweenList list containing elements that are greater than data1 and less than data2
     * @param current current node that is considered whether it is greater than data1 and/or less than data2
     * @return list containing elements that are greater than data1 and less than data2
     */
    private List<T> inBetweenHelper(T data1, T data2, List<T> inBetweenList, AVLNode<T> current) {
        if (current == null) {
            return inBetweenList;
        }

        if (current.getData().compareTo(data1) > 0) {
            inBetweenHelper(data1, data2, inBetweenList, current.getLeft());
            if (current.getData().compareTo(data2) < 0) {
                inBetweenList.add(current.getData());
                inBetweenHelper(data1, data2, inBetweenList, current.getRight());
            }
        } else if (current.getData().compareTo(data1) <= 0) {
            inBetweenHelper(data1, data2, inBetweenList, current.getRight());
        }
        return inBetweenList;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
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