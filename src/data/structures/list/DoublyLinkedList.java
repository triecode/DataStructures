package data.structures.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import data.structure.utils.BNode;

/**
 * Doubly LinkedList implementation
 * 
 * @author chetan89
 *
 * @param <T>
 */
@NoArgsConstructor
public class DoublyLinkedList<T extends Comparable<T>> implements List<T> {
    @Getter @Setter
    private BNode<T> head = null;    // Head of the list
    @Getter @Setter
    private BNode<T> tail = null;    // Tail of the list

    /**
     * Constructor
     * @param item
     */
    public DoublyLinkedList (T item) {
        if (item == null) {
            return;
        }
        this.head = new BNode<T>(item, null, null);
        this.tail = this.head;
    }

    /**
     * Constructor for an array of items
     * @param items
     */
    public DoublyLinkedList (T[] items) {
        for (int i = 0; i < items.length; i++) {
            this.append(items[i]);
        }
    }
    
    /**
     * Append an item to the tail of the list
     */
    public void append(T item) {
        BNode<T> newTail = new BNode<T>(item, this.tail, null);
        this.append(newTail);
    }

    private void append(BNode<T> item) {
        if (this.head == null) {
            this.head = item;
        } else {
            this.tail.setNext(item);
            item.setPrev(this.tail);
        }
        this.tail = item;
    }

    /**
     * Prepend an item to the head of the list
     */
    public void prepend(T item) {
        BNode<T> newHead = new BNode<T>(item, null, this.head);
        this.head = newHead;
    }

    private void prepend(BNode<T> item) {
        item.setNext(this.head);
        if (this.head != null) {
            this.head.setPrev(item);
        }
        this.head = item;
    }

    /**
     * Print the list data
     * For example, a list of integers from 1-9:
     *     List:
     *         1   2   3   4   5   6   7   8   9
     */
    public void printList() {
        BNode<T> iterator = this.head;
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

        // Iteratively reverse every node starting from 'head'
        BNode<T> curr = this.head;
        while (curr != null) {
            BNode<T> next = curr.getNext();
            curr.setNext(curr.getPrev());
            curr.setPrev(next);
            // Set the head
            if (next == null) {
                this.head = curr;
            }
            curr = next;
        }
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
    private BNode<T> recReverseList(BNode<T> head) {
        // Base case: null/single node (do nothing)
        if (head == null || head.getNext() == null) {
            this.head = head;
            if (this.head != null) {
                this.head.setPrev(null);
            }
            return head;
        }
        // Recursive case: 
        else {
            // Reverse the list starting from next node
            BNode<T> reversedTail = recReverseList(head.getNext());
            // Set the returned tail's next to current node
            reversedTail.setNext(head);
            head.setPrev(reversedTail);
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
            BNode<T> prev = null;
            BNode<T> curr = this.head;
            // Inner loop - compare curr to next
            while (curr.getNext() != null) {
                BNode<T> next = curr.getNext();
                // If current is larger than next
                // swap pointers
                if (curr.compareTo(next) > 0) {
                    // Set next references
                    if (prev != null) prev.setNext(next);
                    curr.setNext(next.getNext());
                    next.setNext(curr);
                    // Set prev references
                    next.setPrev(prev);
                    curr.setPrev(next);
                    if (curr.getNext() != null) {
                        curr.getNext().setPrev(curr);
                    }
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
        BNode<T> iter = this.head;
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
    private BNode<T> mergeSort(BNode<T> head) {
        // Base case: No node/one node (do nothing)
        if (head == null || head.getNext() == null) {
            return head;
        }
        // Recursive case
        else {
            // Split the into equal halves
            BNode<T> head2 = splitList(head);
            // Sort the first half recursively
            BNode<T> sorted1 = mergeSort(head);
            // Sort the second half recursively
            BNode<T> sorted2 = mergeSort(head2);
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
    private BNode<T> merge(BNode<T> sorted1, BNode<T> sorted2) {
        BNode<T> head = null;
        BNode<T> tail = null;
        // While there are node to be processed
        while (sorted1 != null || sorted2 != null) {
            BNode<T> least = null;
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
                least.setPrev(tail);
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
    private BNode<T> splitList(BNode<T> head) {
        if (head == null) return null; // Empty list
        BNode<T> fast = head;
        BNode<T> slow = head;
        
        while(fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        BNode<T> splitNode = slow.getNext();
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
        BNode<T> iterator = this.head;
        this.head = null;

        // Iterate over old list and insert in position
        while(iterator != null) {
            BNode<T> next = iterator.getNext();
            this.insertSorted(iterator);
            iterator = next;
        }
    }

    private void insertSorted(BNode<T> item) {
        BNode<T> iterator = this.head;
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
            BNode<T> next = iterator.getNext();
            iterator.setNext(item);
            item.setPrev(iterator);
            item.setNext(next);
            if (next != null) {
                next.setPrev(item);
            }
        }
    }

    /**
     * Method to duplicate a list
     */
    public DoublyLinkedList<T> copyOfList() {
        if (this.head == null) return new DoublyLinkedList<T>();
        DoublyLinkedList<T> copyList = new DoublyLinkedList<T>(this.head.getData());
        BNode<T> iterator = this.head.getNext();
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
        BNode<T> iterator = this.head;
        for (int i = 0; i < n; i++) {
            iterator = iterator.getNext();
            if (iterator == null) {
                return null;
            }
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
            BNode<T> iterator = this.head;
            for (int i = 1; i < n; i++) {
                if (iterator == null) {
                    return false;
                }
                iterator = iterator.getNext();
            }
            // Insert node
            BNode<T> next = iterator.getNext();
            BNode<T> toInsert = new BNode<T>(item, iterator, next);
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
            BNode<T> oldHead = this.head;
            this.head = this.head.getNext();
            if (this.head != null) {
                this.head.setPrev(null);
            }
            return oldHead.getData();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DoublyLinkedList<?>) {
            @SuppressWarnings("unchecked")
            DoublyLinkedList<T> otherList = (DoublyLinkedList<T>) other;

            // Compare head, tail and lengths
            if (this.computeLength() == otherList.computeLength()) {
                BNode<T> head1 = this.head;
                BNode<T> head2 = otherList.getHead();

                // Check equality of all nodes
                while (head1 != null && head2 != null) {
                    if (!head1.getData().equals(head2.getData())) {
                        return false;
                    }
                    head1 = head1.getNext();
                    head2 = head2.getNext();
                }

                BNode<T> tail1 = this.tail;
                BNode<T> tail2 = otherList.getTail();

                // Check equality of all nodes
                while (tail1 != null && tail2 != null) {
                    if (!tail1.getData().equals(tail2.getData())) {
                        return false;
                    }
                    tail1 = tail1.getPrev();
                    tail2 = tail2.getPrev();
                }
                return true;
            }
        }
        return false;
    }
}