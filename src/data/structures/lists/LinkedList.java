package data.structures.lists;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import data.structure.utils.Node;

/**
 * LinkedList implementation
 * 
 * @author chetan89
 *
 * @param <T>
 */
@NoArgsConstructor
public class LinkedList<T extends Comparable<T>> implements List<T> {
    @Getter @Setter
    private Node<T> head = null;    // Head of the list
    @Getter @Setter
    private Node<T> tail = null;    // Tail of the list

    /**
     * Constructor
     * @param item
     */
    public LinkedList (T item) {
        if (item == null) {
            return;
        }
        this.head = new Node<T>(item, null);
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
        Node<T> newTail = new Node<T>(item, null);
        if (this.head == null) {
            this.head = newTail;
        } else {
            this.tail.setNext(newTail);
        }
        this.tail = newTail;
    }

    /**
     * Prepend an item to the head of the list
     */
    public void prepend(T item) {
        Node<T> newHead = new Node<T>(item, this.head);
        this.head = newHead;
    }

    /**
     * Print the list data
     * For example, a list of integers from 1-9:
     *     List:
     *         1   2   3   4   5   6   7   8   9
     */
    public void printList() {
        Node<T> iterator = this.head;
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
        Node<T> prev = null;
        Node<T> curr = this.head;
        while (curr != null) {
            Node<T> next = curr.getNext();
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
    private Node<T> recReverseList(Node<T> head) {
        // Base case: null/single node (do nothing)
        if (head == null || head.getNext() == null) {
            this.head = head;
            return head;
        }
        // Recursive case: 
        else {
            // Reverse the list starting from next node
            Node<T> reversedTail = recReverseList(head.getNext());
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
            Node<T> prev = null;
            Node<T> curr = this.head;
            // Inner loop - compare curr to next
            while (curr.getNext() != null) {
                Node<T> next = curr.getNext();
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
        Node<T> iter = this.head;
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
    private Node<T> mergeSort(Node<T> head) {
        // Base case: No node/one node (do nothing)
        if (head == null || head.getNext() == null) {
            return head;
        }
        // Recursive case
        else {
            // Split the into equal halves
            Node<T> head2 = splitList(head);
            // Sort the first half recursively
            Node<T> sorted1 = mergeSort(head);
            // Sort the second half recursively
            Node<T> sorted2 = mergeSort(head2);
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
    private Node<T> merge(Node<T> sorted1, Node<T> sorted2) {
        Node<T> head = null;
        Node<T> tail = null;
        // While there are node to be processed
        while (sorted1 != null || sorted2 != null) {
            Node<T> least = null;
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
    private Node<T> splitList(Node<T> head) {
        if (head == null) return null; // Empty list
        Node<T> fast = head;
        Node<T> slow = head;
        
        while(fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        Node<T> splitNode = slow.getNext();
        slow.setNext(null);
        return splitNode;
    }

    /**
     * Method to sort the list
     */
    public void sortList() {
        this.mergeSort();
    }

    public LinkedList<T> copyOfList() {
        if (this.head == null) return new LinkedList<T>();
        LinkedList<T> copyList = new LinkedList<T>(this.head.getData());
        Node<T> iterator = this.head.getNext();
        while (iterator != null) {
            copyList.append(iterator.getData());
            iterator = iterator.getNext();
        }
        return copyList;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof LinkedList<?>) {
            @SuppressWarnings("unchecked")
            LinkedList<T> otherList = (LinkedList<T>) other;

            // Compare head, tail and lengths
            if (this.computeLength() == otherList.computeLength()) {
                Node<T> head1 = this.head;
                Node<T> head2 = otherList.getHead();

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