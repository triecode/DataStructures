package data.structures.list;

/**
 * List interface
 *
 * @author chetan89
 *
 * @param <T>
 */
public interface List<T> {
    /**
     * Method to append an item to a list
     * @param item
     */
    public void append(T item);

    /**
     * Method to prepend an item to a list
     * @param item
     */
    public void prepend(T item);

    /**
     * Method to print the entire list
     */
    public void printList();

    /**
     * Method to reverse a list
     */
    public void reverseList();

    /**
     * Method to sort a list
     */
    public void sortList();

    /**
     * Method to get Nth element of a list
     * @param n
     * @return Nth element or null (if not exists)
     */
    public T getNth(int n);

    /**
     * Method to sort a list
     */
    public List<T> copyOfList();
}
