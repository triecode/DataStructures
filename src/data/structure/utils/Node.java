package data.structure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@ToString
public class Node<T extends Comparable<T>> {
    @Getter @Setter
    private T data;
    @Getter @Setter
    private Node<T>[] children;

    // Compares the items
    public int compareTo(Node<T> other) {
        // Null values always smaller
        if (other == null) {
            return 1;
        }
        return this.data.compareTo(other.getData());
    }
}