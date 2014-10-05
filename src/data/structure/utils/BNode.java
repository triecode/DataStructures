package data.structure.utils;

/**
 * Binary Node class: Has 2 object references ('prev', 'next') or ('left', 'right')
 * 
 * @author chetan89
 * 
 * @param <T>
 *            Generic object
 */
public class BNode<T extends Comparable<T>> extends Node<T> implements Comparable<BNode<T>>, Cloneable{

    private static final int SIZE = 2;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int PREV = 0;
    private static final int NEXT = 1;

    /**
     * Constructor to setup the node
     * 
     * @param data
     * @param prevOrLeft
     * @param nextOrRight
     */
    public BNode(T data, BNode<T> prevOrLeft, BNode<T> nextOrRight) {
        setData(data);
        setPrev(prevOrLeft);
        setNext(nextOrRight);
    }

    /**
     * Constructor to setup the node
     * 
     * @param data
     * @param prevOrLeft
     * @param nextOrRight
     */
    public BNode(T data) {
        setData(data);
        setPrev(null);
        setNext(null);
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
     * Sets the prev node
     * 
     * @param prev
     */
    public void setPrev(BNode<T> prev) {
        setNode(prev, PREV);
    }

    /**
     * Sets the left node
     * 
     * @param left
     */
    public void setLeft(BNode<T> left) {
        setNode(left, LEFT);
    }

    /**
     * Sets the right node
     * 
     * @param right
     */
    public void setRight(BNode<T> right) {
        setNode(right, RIGHT);
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

    @Override
    public int compareTo(BNode<T> o) {
        return this.getData().compareTo(o.getData());
    }

    @Override
    public BNode<T> clone() {
        return new BNode<T>(this.getData());
    }
}
