package data.structures.lists.test;

import org.junit.Assert;
import org.junit.Test;

import data.structure.utils.BNode;
import data.structures.list.LinkedList;
import data.structures.tree.BinaryTree;

/**
 * Unit tests for binary trees
 *
 * @author chetan89
 */
public class BinaryTreeTest {
    BNode<Integer> lChild = new BNode<Integer>(2, null, new BNode<Integer>(5));
    BNode<Integer> rChild = new BNode<Integer>(3, new BNode<Integer>(6), new BNode<Integer>(7));
    BNode<Integer> root = new BNode<Integer>(1, lChild, rChild);

    Integer[] inOrder = {2, 5, 1, 6, 3, 7};
    LinkedList<Integer> inOrderList = new LinkedList<Integer>(inOrder);
    Integer[] preOrder = {1, 2, 5, 3, 6, 7};
    LinkedList<Integer> preOrderList = new LinkedList<Integer>(preOrder);
    Integer[] postOrder = {5, 2, 6, 7, 3, 1};
    LinkedList<Integer> postOrderList = new LinkedList<Integer>(postOrder);
    
    BinaryTree<Integer> bTree = new BinaryTree<Integer>(root);

    @Test
    public void testOrderTraversal() {
        // Assert inorder (iterative, recursive and morris)
        Assert.assertTrue(inOrderList.equals(bTree.morrisInOrder()));
        Assert.assertTrue(inOrderList.equals(bTree.iterativeInOrder()));
        Assert.assertTrue(inOrderList.equals(bTree.recursiveInOrder()));

        // Assert preorder (iterative, recursive)
        Assert.assertTrue(preOrderList.equals(bTree.iterativePreOrder()));
        Assert.assertTrue(preOrderList.equals(bTree.recursivePreOrder()));

        // Assert postorder (iterative, recursive)
        Assert.assertTrue(postOrderList.equals(bTree.iterativePostOrder()));
        Assert.assertTrue(postOrderList.equals(bTree.recursivePostOrder()));
    }

    @Test
    public void testCopyTree() {
        BinaryTree<Integer> bTreeCopy = bTree.copyOfTree();
        Assert.assertTrue(bTreeCopy.equals(bTree));
    }
}
