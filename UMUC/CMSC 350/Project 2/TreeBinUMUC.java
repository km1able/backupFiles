/*
 * File: TreeBinUMUC.java
 * Author: JordanFournier
 * Date: Feb 5, 2016
 * Purpose: Generic implementation of a binary tree class
 */

package Project2;
import java.util.Scanner;

public class TreeBinUMUC<T extends Comparable> {
    
    private NodeTreeBin<T> root;
    private String order = "";

    //create a tree from a Scanner tied to a file
    TreeBinUMUC(Scanner scanner) {
        while (scanner.hasNext()) {
            T[] value = (T[]) scanner.nextLine().split(",");
            for (int i = 0; i < value.length; i++) {
                if (root == null) {
                    root = new NodeTreeBin<T>(value[i]);
                } else if (value[i].compareTo(root.getData()) < 0) {
                    insertLeftChild(root, value[i]);
                } else if (value[i].compareTo(root.getData()) > 0) {
                    insertRightChild(root, value[i]);
                }
            }
        }
    }
    
    //no-parameter constructor
    TreeBinUMUC() {
        this.root = null;
    }
    
    // returns the node pointing to the element matching the String parameter
    NodeTreeBin<T> findElement(String data) {
        NodeTreeBin<T> tree = root;
        while (tree != null) {
            if (tree.getData().compareTo(data) == 0) {
                return tree;
            } else if (data.compareTo((String) root.getData()) < 0) {
                tree = root.left;
            } else {
                tree = root.right;
            }
        }
        return null;
    }

    void insertLeftChild(NodeTreeBin<T> node, T data) {
        root = insert(node, data);
    }

    void insertRightChild(NodeTreeBin<T> node, T data) {
        root = insert(node, data);
    }

    private NodeTreeBin<T> insert(NodeTreeBin<T> node, T data) {
        // If root is null then tree is empty
        if (node == null) {
            node = new NodeTreeBin<T>(data);
        } else {
            if (data.compareTo((String) root.getData()) < 0) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    NodeTreeBin<T> getRoot() {
        return root;
    }

    String toPreOrderString() {
        return toPreOrderString(root);
    }

    private String toPreOrderString(NodeTreeBin<T> root2) {
        if (root2 == null) {
            return null;
        }
        order += (root2.getData() + " "); 
        toPreOrderString(root2.getLeftChild()); 
        toPreOrderString(root2.getRightChild()); 
        return order;
    }

    String toPostOrderString() {
        order = "";
        return toPostOrderString(root);
    }

    private String toPostOrderString(NodeTreeBin<T> root2) {
        if (root2 == null) {
            return null;
        }
        toPreOrderString(root2.getLeftChild()); 
        toPreOrderString(root2.getRightChild()); 
        order += (root2.getData() + " "); 
        return order;
    }

    String toInOrderString() {
        order = "";
        return toInOrderString(root);
    }

    private String toInOrderString(NodeTreeBin<T> root2) {
        if (root2 == null) {
            return null;
        }
        toPreOrderString(root2.getLeftChild()); 
        order += (root2.getData() + " "); 
        toPreOrderString(root2.getRightChild()); 
        return order;
    }

    public String toString() {
        return toString(root);
    }

    public String toString(NodeTreeBin<T> root) {
        StringBuffer s = new StringBuffer();
        if (root != null) {
            s.append(toString(root.left)); 
            s.append(root.getData() + " "); 
            s.append(toString(root.right)); 
        }
        return s.toString();
    }
}
