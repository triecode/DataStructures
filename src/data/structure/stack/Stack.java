package data.structure.stack;

import lombok.NoArgsConstructor;
import data.structures.list.LinkedList;

@NoArgsConstructor
public class Stack<T extends Comparable<T>> {
    private LinkedList<T> stack = new LinkedList<T>();
    
    public void push(T item) {
        stack.prepend(item);
    }
    
    public T pop() {
        return stack.pop();
    }

    public T peek() {
        return stack.getNth(0);
    }
    
    public boolean isEmpty() {
        return (stack.getHead() == null);
    }
}
