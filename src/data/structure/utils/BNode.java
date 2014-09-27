package data.structure.utils;

/**
 * Binary Node class: Has 2 object references ('prev', 'next') or ('left', 'right')
 * 
 * @author chetan89
 * 
 * @param <T>
 *            Generic object
 */
public class BNode<T extends Comparable<T>> extends Node<T> {

    private static int SIZE = 2;
    private static int LEFT = 0;
    private static int RIGHT = 1;
    private static int PREV = 0;
    private static int NEXT = 1;

    /**
     * Constructor to setup the node
     * 
     * @param data
     * @param next
     */
    public BNode(T data, BNode<T> next) {
        setData(data);
        setNext(next);
    }

    /**
     * Gets the next node
     * 
     * @return
     */
    public BNode<T> getNext() {
        return getNode(NEXT);
    }

    /**
     * Gets the next node
     * 
     * @return
     */
    public BNode<T> getPrev() {
        return getNode(PREV);
    }

    /**
     * Gets the right node
     * 
     * @return
     */
    public BNode<T> getRight() {
        return getNode(RIGHT);
    }

    /**
     * Gets the left node
     * 
     * @return
     */
    public BNode<T> getLeft() {
        return getNode(LEFT);
    }

    /**
     * Sets the next node
     * 
     * @param next
     */
    public void setNext(BNode<T> next) {
        setNode(next, NEXT);
    }

    /**
     * Sets the next node
     * 
     * @param next
     */
    public void setPrev(BNode<T> prev) {
        setNode(prev, PREV);
    }

    private BNode<T> getNode(int index) {
        if (getChildren() != null && getChildren().length == SIZE) {
            return (BNode<T>) getChildren()[index];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void setNode(BNode<T> node, int index) {
        if (getChildren() == null) {
            setChildren(new Node[SIZE]);
        }
        getChildren()[index] = node;
    }
}
