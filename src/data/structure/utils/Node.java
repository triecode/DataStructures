package data.structure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Node class
 *
 * @author chetan89
 *
 * @param <T> Generic object
 */

@AllArgsConstructor
@ToString
public class Node<T extends Comparable<T>> {
    @Getter @Setter
    private T data;
    @Getter @Setter
    private Node<T> prev;
    @Getter @Setter
    private Node<T> next;

    /**
     * Constructor with data and next parameters
     *
     * @param data
     * @param next
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    // Compares the items
    public int compareTo(Node<T> other) {
        // Null values always smaller
        if (other == null) {
            return 1;
        }
        return this.data.compareTo(other.getData());
    }
}