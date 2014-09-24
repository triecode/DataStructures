package data.structures.lists.test;

import org.junit.Assert;
import org.junit.Test;

import data.structures.lists.LinkedList;

/**
 * Unit tests for linked lists
 *
 * @author chetan89
 */
public class LinkedListTest {

    @Test
    public void testAppendPrependList() {
        LinkedList<Integer> appendList = new LinkedList<Integer>();
        appendList.append(1);
        appendList.append(2);
        appendList.append(3);
        appendList.append(4);

        LinkedList<Integer> prependList = new LinkedList<Integer>();
        prependList.prepend(4);
        prependList.prepend(3);
        prependList.prepend(2);
        prependList.prepend(1);

        Assert.assertTrue(appendList.equals(prependList));
    }
    
    @Test
    public void testSortList() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        LinkedList<Integer> list = new LinkedList<Integer>(listOfIntegers);

        Integer[] ascListOfIntegers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkedList<Integer> ascList = new LinkedList<Integer>(ascListOfIntegers);

        // Test bubble sort
        list.bubbleSort();
        Assert.assertTrue(list.equals(ascList));

        // Reset list, test merge sort
        list = new LinkedList<Integer>(listOfIntegers);
        list.mergeSort();
        Assert.assertTrue(list.equals(ascList));

        // Reset list, test insertion sort
        list = new LinkedList<Integer>(listOfIntegers);
        list.insertionSort();
        Assert.assertTrue(list.equals(ascList));
    }

    @Test
    public void testReverseList() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        LinkedList<Integer> list = new LinkedList<Integer>(listOfIntegers);
        LinkedList<Integer> originalList = list.copyOfList();

        // Double reverse and test
        list.recReverseList();
        list.reverseList();
        Assert.assertTrue(list.equals(originalList));
    }

    @Test
    public void testGetNth() {
        Integer[] listOfIntegers = { 4, 5, 7, 6, 1, 2, 3, 8, 9 };
        LinkedList<Integer> list = new LinkedList<Integer>(listOfIntegers);
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
        LinkedList<Integer> list = new LinkedList<Integer>(listOfIntegers);
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
        LinkedList<Integer> list = new LinkedList<Integer>();
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
