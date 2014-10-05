package data.structures.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import data.structure.stack.Stack;
import data.structure.utils.BNode;
import data.structures.list.LinkedList;

@AllArgsConstructor
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    @Getter @Setter
    private BNode<T> root;       // Root of the tree

    @Override
    public void printTree() {
        if (root == null) {
            return;
        }
        LinkedList<BNode<T>> nodeList = new LinkedList<BNode<T>>();
        LinkedList<Integer> nodeLevel = new LinkedList<Integer>();
        nodeList.prepend(root);
        nodeLevel.prepend(0);
        while (nodeList.getNth(0) != null) {
            BNode<T> element = nodeList.pop();
            Integer level = nodeLevel.pop();
            printElement(element, level);
            if (element.getRight() != null) {
                nodeList.prepend(element.getRight());
                nodeLevel.prepend(level + 1);
            }
            if (element.getLeft() != null) {
                nodeList.prepend(element.getLeft());
                nodeLevel.prepend(level + 1);
            }
        }
    }

    @Override
    public BinaryTree<T> copyOfTree() {
        return copyOfTree(this.root);
    }

    private BinaryTree<T> copyOfTree(BNode<T> root) {
        if (root == null)
            return null;
        else if((root.getLeft() == null) && (root.getRight() == null)) {
            return new BinaryTree<T>(root.clone());
        }
        else {
            BNode<T> copyTreeRoot = new BNode<T>(root.getData());
            BinaryTree<T> lTree = copyOfTree(root.getLeft());
            if (lTree != null) {
                copyTreeRoot.setLeft(lTree.getRoot());
            }
            BinaryTree<T> rTree = copyOfTree(root.getRight());
            if (rTree != null) {
                copyTreeRoot.setRight(rTree.getRoot());
            }
            return new BinaryTree<T>(copyTreeRoot);
        }
    }

    public LinkedList<T> inOrder() {
        return iterativeInOrder();
    }

    public LinkedList<T> morrisInOrder() {
        BNode<T> curr = this.root;
        LinkedList<T> inOrderList = new LinkedList<T>();
        // Iterate through all nodes
        while (curr != null) {
            // If the current node has left child
            if (curr.getLeft() != null) {
                // Find predecessor and confirm threading
                BNode<T> pred = inOrderPredecessor(curr);
                // If the node is threaded
                if (pred.getRight() != null && pred.getRight().equals(curr)) {
                    pred.setRight(null);                  // reset thread
                    inOrderList.append(curr.getData());   // print
                    curr = curr.getRight();               // proceed right
                } else {
                    pred.setRight(curr);                  // set threading
                    curr = curr.getLeft();                // proceed left
                }
            } else {
                inOrderList.append(curr.getData());
                curr = curr.getRight();
            }
        }
        return inOrderList;
    }
    
    private BNode<T> inOrderPredecessor(BNode<T> curr) {
        BNode<T> preCurr = curr.getLeft();
        while (preCurr.getRight() != null && !preCurr.getRight().equals(curr)) {
            preCurr = preCurr.getRight();
        }
        return preCurr;
    }

    public LinkedList<T> iterativeInOrder() {
        LinkedList<T> inOrderList = new LinkedList<T>();
        Stack<BNode<T>> stack = new Stack<BNode<T>>();

        BNode<T> curr = this.root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            curr = stack.pop();
            inOrderList.append(curr.getData());
            curr = curr.getRight();
        }
        return inOrderList;
    }

    public LinkedList<T> recursiveInOrder() {
        LinkedList<T> inOrderList = new LinkedList<T>();
        recInOrder(this.root, inOrderList);
        return inOrderList;
    }

    private void recInOrder(BNode<T> node, LinkedList<T> inOrderList) {
        if (node == null) {
            return;
        }
        recInOrder(node.getLeft(), inOrderList);
        inOrderList.append(node.getData());
        recInOrder(node.getRight(), inOrderList);
    }

    public LinkedList<T> iterativePreOrder() {
        LinkedList<T> preOrderList = new LinkedList<T>();
        Stack<BNode<T>> stack = new Stack<BNode<T>>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            BNode<T> curr = stack.pop();
            if (curr.getRight() != null) {
                stack.push(curr.getRight());
            }
            if (curr.getLeft() != null) {
                stack.push(curr.getLeft());
            }
            preOrderList.append(curr.getData());
        }
        return preOrderList;
    }

    public LinkedList<T> recursivePreOrder() {
        LinkedList<T> preOrderList = new LinkedList<T>();
        recPreOrder(this.root, preOrderList);
        return preOrderList;
    }

    private void recPreOrder(BNode<T> node, LinkedList<T> preOrderList) {
        if (node == null) {
            return;
        }
        preOrderList.append(node.getData());
        recPreOrder(node.getLeft(), preOrderList);
        recPreOrder(node.getRight(), preOrderList);
    }

    public LinkedList<T> iterativePostOrder() {
        LinkedList<T> postOrderList = new LinkedList<T>();
        Stack<BNode<T>> stack = new Stack<BNode<T>>();

        BNode<T> curr = this.root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                if (curr.getRight() != null) stack.push(curr.getRight());
                stack.push(curr);
                curr = curr.getLeft();
            }
            curr = stack.pop();
            if (curr.getRight() != null && curr.getRight().equals(stack.peek())) {
                stack.pop();
                stack.push(curr);
                curr = curr.getRight();
            } else {
                postOrderList.append(curr.getData());
                curr = null;
            }
        }
        return postOrderList;
    }

    public LinkedList<T> recursivePostOrder() {
        LinkedList<T> postOrderList = new LinkedList<T>();
        recPostOrder(this.root, postOrderList);
        return postOrderList;
    }

    private void recPostOrder(BNode<T> node, LinkedList<T> postOrderList) {
        if (node == null) {
            return;
        }
        recPostOrder(node.getLeft(), postOrderList);
        recPostOrder(node.getRight(), postOrderList);
        postOrderList.append(node.getData());
    }


    private void printElement(BNode<T> element, Integer level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(element.getData());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BinaryTree<?>) {
            @SuppressWarnings("unchecked")
            BinaryTree<T> otherTree = (BinaryTree<T>) other;

            // Compare ordered traversals
            return equals(this.root, otherTree.getRoot());
        }
        return false;
    }
    
    private boolean equals(BNode<T> root1, BNode<T> root2) {
        // Shortcut for reference equality; also handles equals(null, null)
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.getData().equals(root2.getData()) &&
               equals(root1.getLeft(), root2.getLeft()) &&
               equals(root1.getRight(), root2.getRight());
    } 
}
