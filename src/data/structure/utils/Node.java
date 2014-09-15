package data.structure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Generic Node
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

    // Compares the items
    public int compareTo(Node<T> other) {
        return this.data.compareTo(other.getData());
    }
}