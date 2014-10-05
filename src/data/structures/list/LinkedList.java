package data.structures.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import data.structures.util.UNode;

/**
 * LinkedList implementation
 * 
 * Reference problems:
 * http://cslibrary.stanford.edu/105/LinkedListProblems.pdf
 * 
 * @author chetan89
 *
 * @param <T>
 */
@NoArgsConstructor
public class LinkedList<T extends Comparable<T>> implements List<T> {
    @Getter @Setter
    private UNode<T> head = null;    // Head of the list
    @Getter @Setter
    private UNode<T> tail = null;    // Tail of the list

    /**
     * Constructor
     * @param item
     */
    public LinkedList (T item) {
        if (item == null) {
            return;
        }
        this.head = new UNode<T>(item, null);
        this.tail = this.head;
    }

    /**
     * Constructor for an array of items
     * @param items
     */
    public LinkedList (T[] items) {
        for (int i = 0; i < items.length; i++) {
            this.append(items[i]);
        }
    }
    
    /**
     * Append an item to the tail of the list
     */
    public void append(T item) {
        UNode<T> newTail = new UNode<T>(item, null);
        this.append(newTail);
    }

    private void append(UNode<T> item) {
        if (this.head == null) {
            this.head = item;
        } else {
            this.tail.setNext(item);
        }
        this.tail = item;
        
    }
    
    /**
     * Prepend an item to the head of the list
     */
    public void prepend(T item) {
        UNode<T> newHead = new UNode<T>(item, this.head);
        this.head = newHead;
    }

    private void prepend(UNode<T> item) {
        item.setNext(this.head);
        this.head = item;
    }
    
    /**
     * Print the list data
     * For example, a list of integers from 1-9:
     *     List:
     *         1   2   3   4   5   6   7   8   9
     */
    public void printList() {
        UNode<T> iterator = this.head;
        System.out.println("List:");
        while (iterator != null) {
            System.out.print("\t"+iterator.getData());
            iterator = iterator.getNext();
        } System.out.println();
    }

    /**
     * Method to reverse the list iteratively
     */
    public void reverseList() {
        // Set tail to current head
        this.tail = this.head;

        // Iteratively reverse every 2 nodes starting from (null,head)
        UNode<T> prev = null;
        UNode<T> curr = this.head;
        while (curr != null) {
            UNode<T> next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        // Set the head
        this.head = prev;
    }

    /**
     * Method to reverse the list recursively
     */
    public void recReverseList() {
        this.tail = recReverseList(this.head);
    }
    
    /**
     * This method reverses the linked list 
     * and returns the tail.
     *
     * @param head Head of the list to be reversed
     * @return Node representing the tail of the list
     */
    private UNode<T> recReverseList(UNode<T> head) {
        // Base case: null/single node (do nothing)
        if (head == null || head.getNext() == null) {
            this.head = head;
            return head;
        }
        // Recursive case: 
        else {
            // Reverse the list starting from next node
            UNode<T> reversedTail = recReverseList(head.getNext());
            // Set the returned tail's next to current node
            reversedTail.setNext(head);
            // Set current node's next to null and return
            head.setNext(null);
            return head;
        }
    }

    /**
     * Method to sort the list using the standard
     * bubble sort algorithm - O(n^2) time, O(1) space
     */
    public void bubbleSort() {
        // Compute the length of the list
        int len = computeLength();
        // Outer loop
        for (int i=0; i<len; i++) {
            UNode<T> prev = null;
            UNode<T> curr = this.head;
            // Inner loop - compare curr to next
            while (curr.getNext() != null) {
                UNode<T> next = curr.getNext();
                // If current is larger than next
                // swap pointers
                if (curr.compareTo(next) > 0) {
                    if (prev != null) prev.setNext(next);
                    curr.setNext(next.getNext());
                    next.setNext(curr);
                    if (prev == null) this.head = next;
                    prev = next;
                }
                // Else proceed to next iteration
                else {
                    if (prev == null) this.head = curr;                    
                    prev = curr;
                    curr = curr.getNext();                    
                }
            }
        }
    }

    /**
     * Method to compute length of the list
     * @return Length of the list
     */
    private int computeLength() {
        UNode<T> iter = this.head;
        int count = 0;
        while (iter != null) {
            iter = iter.getNext();
            count++;
        }
        return count;
    }

    /**
     * Method to sort the list using the standard
     * merge sort algorithm
     */
    public void mergeSort() {
        this.head = mergeSort(this.head); 
    }
    
    /**
     * This method implements the merge sort routine
     * for a linked list - O(n log n) time, O(1) space
     * 
     * @param head The head node to sort
     * @return
     */
    private UNode<T> mergeSort(UNode<T> head) {
        // Base case: No node/one node (do nothing)
        if (head == null || head.getNext() == null) {
            return head;
        }
        // Recursive case
        else {
            // Split the into equal halves
            UNode<T> head2 = splitList(head);
            // Sort the first half recursively
            UNode<T> sorted1 = mergeSort(head);
            // Sort the second half recursively
            UNode<T> sorted2 = mergeSort(head2);
            // Merge and return the head of merged list
            return merge(sorted1, sorted2);
        }
    }

    /**
     * Method of merge two sorted lists
     *
     * @param sorted1 Sorted list 1
     * @param sorted2 Sorted list 2
     * @return Head of the merged lists
     */
    private UNode<T> merge(UNode<T> sorted1, UNode<T> sorted2) {
        UNode<T> head = null;
        UNode<T> tail = null;
        // While there are node to be processed
        while (sorted1 != null || sorted2 != null) {
            UNode<T> least = null;
            // Find the least node
            if (sorted1 != null && sorted2 == null) {
                least = sorted1;
                sorted1 = sorted1.getNext();
            }
            else if (sorted1 == null && sorted2 != null) {
                least = sorted2;
                sorted2 = sorted2.getNext();
            }
            else {
                if (sorted1.compareTo(sorted2) <= 0) {
                    least = sorted1;
                    sorted1 = sorted1.getNext();
                } else {
                    least = sorted2;
                    sorted2 = sorted2.getNext();
                }
            }

            // Append the least node to the merged list
            if (head == null && tail == null) {
                head = least;
                tail = least;
            } else {
                tail.setNext(least);
                tail = tail.getNext();
                tail.setNext(null);
            }
        }
        return head;
    }

    /**
     * Method to split a list into two (using fast, slow pointers)
     * NOTE: #(Nodes in list 1) >= #(Nodes in list 2)
     * @param head
     * @return Head of the second list
     */
    private UNode<T> splitList(UNode<T> head) {
        if (head == null) return null; // Empty list
        UNode<T> fast = head;
        UNode<T> slow = head;
        
        while(fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        UNode<T> splitNode = slow.getNext();
        slow.setNext(null);
        return splitNode;
    }

    /**
     * Method to sort the list
     */
    public void sortList() {
        this.mergeSort();
    }

    /**
     * Method to sort the list using the standard
     * insertion sort algorithm
     */
    public void insertionSort() {
        // Reset head
        UNode<T> iterator = this.head;
        this.head = null;

        // Iterate over old list and insert in position
        while(iterator != null) {
            UNode<T> next = iterator.getNext();
            this.insertSorted(iterator);
            iterator = next;
        }
    }
    
    private void insertSorted(UNode<T> item) {
        UNode<T> iterator = this.head;
        // If list is empty or item is smaller than head
        if (this.head == null || this.head.compareTo(item) > 0) {
            this.prepend(item);
        } else {
            // Keep iterating until we find the right spot
            while ((iterator.getNext() != null)
                    && (iterator.compareTo(item) < 0 && iterator.getNext().compareTo(item) < 0)) {
                iterator = iterator.getNext();
            }
            // Insert the node
            UNode<T> next = iterator.getNext();
            iterator.setNext(item);
            item.setNext(next);
        }
    }

    /**
     * Method to duplicate a list
     */
    public LinkedList<T> copyOfList() {
        if (this.head == null) return new LinkedList<T>();
        LinkedList<T> copyList = new LinkedList<T>(this.head.getData());
        UNode<T> iterator = this.head.getNext();
        while (iterator != null) {
            copyList.append(iterator.getData());
            iterator = iterator.getNext();
        }
        return copyList;
    }

    /**
     * Method to get the Nth item (if exists)
     * NOTE: Indices start from 0
     */
    public T getNth(int n) {
        if (n < 0) {
            return null;
        }
        UNode<T> iterator = this.head;
        for (int i = 0; ((i < n) && (iterator != null)); i++) {
            iterator = iterator.getNext();
        }
        if (iterator == null) {
            return null;
        }
        return iterator.getData();
    }

    public boolean insertNth(T item, int n) {
        if (n == 0) {
            // Prepend to list
            this.prepend(item);
            return true;
        } else if (n > 0) {
            // Iterate to previous node
            UNode<T> iterator = this.head;
            for (int i = 1; i < n; i++) {
                if (iterator == null) {
                    return false;
                }
                iterator = iterator.getNext();
            }
            // Insert node
            UNode<T> next = iterator.getNext();
            UNode<T> toInsert = new UNode<T>(item, next);
            iterator.setNext(toInsert);
            return true;
        } else {
            return false;  // Bad input
        }
    }
    
    /**
     * Method to pop the head of the list
     * @return
     */
    public T pop() {
        if (this.head == null) {
            return null;
        } else {
            UNode<T> oldHead = this.head;
            this.head = this.head.getNext();
            return oldHead.getData();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LinkedList<?>) {
            @SuppressWarnings("unchecked")
            LinkedList<T> otherList = (LinkedList<T>) other;

            // Compare head, tail and lengths
            if (this.computeLength() == otherList.computeLength()) {
                UNode<T> head1 = this.head;
                UNode<T> head2 = otherList.getHead();

                // Check equality of all nodes
                while (head1 != null && head2 != null) {
                    if (!head1.getData().equals(head2.getData())) {
                        return false;
                    }
                    head1 = head1.getNext();
                    head2 = head2.getNext();
                }
                return true;
            }
        }
        return false;
    }
}