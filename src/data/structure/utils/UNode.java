package data.structure.utils;

/**
 * Unary Node class: Has a single object reference ('next')
 *
 * @author chetan89
 *
 * @param <T> Generic object
 */
public class UNode<T extends Comparable<T>> extends Node<T> implements Comparable<UNode<T>> {

    /**
     * Constructor to setup the node
     * 
     * @param data
     * @param next
     */
    public UNode(T data, UNode<T> next) {
        setData(data);
        setNext(next);
    }

    /**
     * Gets the next node
     * @return
     */
    public UNode<T> getNext() {
        if (getChildren() != null && getChildren().length == 1) {
            return (UNode<T>) getChildren()[0];
        }
        return null;
    }

    /**
     * Sets the next node
     * @param next
     */
    public void setNext(UNode<T> next) {
        @SuppressWarnings("unchecked")
        Node<T>[] children = new Node[1];
        children[0] = next;
        setChildren(children);
    }

    @Override
    public int compareTo(UNode<T> o) {
        return getData().compareTo(o.getData());
    }
}
