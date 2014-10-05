package data.structures.lists.test;

import org.junit.Assert;
import org.junit.Test;

import data.structures.list.DoublyLinkedList;

/**
 * Unit tests for doubly linked lists
 *
 * @author chetan89
 */
public class DoublyLinkedListTest {

    @Test
    public void testAppendPrependList() {
        DoublyLinkedList<Integer> appendList = new DoublyLinkedList<Integer>();
        appendList.append(1);
        appendList.append(2);
        appendList.append(3);
        appendList.append(4);

        DoublyLinkedList<Integer> prependList = new DoublyLinkedList<Integer>();
        prependList.prepend(4);
        prependList.prepend(3);
        prependList.prepend(2);
        prependList.prepend(1);

        Assert.assertTrue(appendList.equals(prependList));
    }

    @Test
    public void testSortList() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(listOfIntegers);

        Integer[] ascListOfIntegers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        DoublyLinkedList<Integer> ascList = new DoublyLinkedList<Integer>(ascListOfIntegers);

        // Test bubble sort
        list.bubbleSort();
        Assert.assertTrue(list.equals(ascList));

        // Reset list, test merge sort
        list = new DoublyLinkedList<Integer>(listOfIntegers);
        list.mergeSort();
        Assert.assertTrue(list.equals(ascList));

        // Reset list, test insertion sort
        list = new DoublyLinkedList<Integer>(listOfIntegers);
        list.insertionSort();
        Assert.assertTrue(list.equals(ascList));
    }

    @Test
    public void testReverseList() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(listOfIntegers);
        DoublyLinkedList<Integer> originalList = list.copyOfList();

        // Recursively reverse and test
        list.recReverseList();
        for (int i = 0; i < listOfIntegers.length; i++) {
            Assert.assertEquals(list.getNth(i), listOfIntegers[listOfIntegers.length - 1 - i]);
        }
        // Iteratively reverse and test
        list.reverseList();
        Assert.assertTrue(list.equals(originalList));
    }

    @Test
    public void testGetNth() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(listOfIntegers);
        // Test all elements of the array
        for (int i = 0; i < listOfIntegers.length; i++) {
            Assert.assertEquals(list.getNth(i), listOfIntegers[i]);
        }
        // Test non-existent element
        Assert.assertEquals(list.getNth(listOfIntegers.length), null);
    }

    @Test
    public void testPop() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(listOfIntegers);
        // Test all elements of the array
        for (int i = 0; i < listOfIntegers.length; i++) {
            Assert.assertEquals(list.pop(), listOfIntegers[i]);
        }
        // Test non-existent element
        Assert.assertEquals(list.pop(), null);
    }

    @Test
    public void testInsertNth() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        // Should not insert element 4 in 10th position
        Assert.assertEquals(list.insertNth(4, 10), false);
        // Insert all elements of the array
        for (int i = 0; i < listOfIntegers.length; i++) {
            Assert.assertEquals(list.insertNth(listOfIntegers[i], i), true);
        }
        // Verify the elements of the array
        for (int i = 0; i < listOfIntegers.length; i++) {
            Assert.assertEquals(list.getNth(i), listOfIntegers[i]);
        }
    }

}
