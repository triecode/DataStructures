package data.structure.queue;

import lombok.NoArgsConstructor;
import data.structures.list.LinkedList;

@NoArgsConstructor
public class Queue<T extends Comparable<T>> {
    private LinkedList<T> queue = new LinkedList<T>();

    public void enqueue(T item) {
        queue.append(item);
    }

    public T dequeue() {
        return queue.pop();
    }

    public T peek() {
        return queue.getNth(0);
    }

    public boolean isEmpty() {
        return (queue.getHead() == null);
    }
}
