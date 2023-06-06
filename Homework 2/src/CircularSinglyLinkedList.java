import java.util.NoSuchElementException;
/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
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
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is larger than size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            CircularSinglyLinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size = size + 1;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (head == null) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, head.getNext());
            head.setNext(newNode);
            newNode.setData(head.getData());
            head.setData(data);
        }
        size = size + 1;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (head == null) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, head.getNext());
            head.setNext(newNode);
            newNode.setData(head.getData());
            head.setData(data);
            head = head.getNext();
        }
        size = size + 1;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index >= size");
        }
        CircularSinglyLinkedListNode<T> current = head;
        T toReturnData = current.getData();
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            toReturnData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        size = size - 1;
        return toReturnData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        T toRemove = head.getData();
        if (size == 1) {
            head = null;
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }
        size = size - 1;
        return toRemove;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }

        CircularSinglyLinkedListNode<T> current = head;
        T toReturnData = current.getData();
        if (size == 1) {
            head = null;
        } else {
            for (int i = 0; i < size - 2; i++) {
                current = current.getNext();
            }
            toReturnData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        size = size - 1;
        return toReturnData;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0.");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index is more than size");
        }
        CircularSinglyLinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }

        CircularSinglyLinkedListNode<T> current = head;
        CircularSinglyLinkedListNode<T> previous = null;

        for (int i = 0; i < size - 1; i++) {
            if (current.getNext().getData().equals(data)) {
                previous = current;
            }
            current = current.getNext();
        }
        if (previous != null) {
            T toReturn = previous.getNext().getData();
            previous.setNext(previous.getNext().getNext());
            size = size - 1;
            return toReturn;
        } else if (previous == null && head.getData().equals(data)) {
            return removeFromFront();
        } else {
            throw new NoSuchElementException("Data is not found in the CircularSinglyLinkedList.");
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] newArray = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = current.getData();
            current = current.getNext();
        }
        return newArray;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}