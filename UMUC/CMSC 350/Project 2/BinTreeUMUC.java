/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2atausch;
import java.util.*;
import java.io.*;

class BinTreeUMUC<T> {

    public NodeBinTree root;
    public String postResult = "";
    public String inResult = "";
    public String preResult = "";
    public String levelResult = "";

    public BinTreeUMUC() {
        this.root = null;
    }

    public void insertNode(int key, String value) {
        System.out.println("Inserting new node with key " + key + " and data " + value);
        NodeBinTree<T> newNode = new NodeBinTree<T>(key, value, null, null);
        if (root == null) {
            root = newNode;
        } else {
            NodeBinTree<T> current = root;
            NodeBinTree<T> parent;
            while (true) {
                parent = current;
                if (key < current.key) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public String getRoot() {
        return root.data;
    }

    public void scanInNode(NodeBinTree<T> newNode) {
        int key = newNode.getKey();
        if (root == null) {
            root = newNode;
        } else {
            NodeBinTree<T> current = root;
            NodeBinTree<T> parent;
            while (true) {
                parent = current;
                if (key < current.key) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    // public void scanTree(String fileName) throws FileNotFoundException {
    // Scanner scanner = new Scanner(new File(fileName));
    public void scanTree() throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File("BinaryTree.txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (data.contains(" ")) {
                    String[] parts = data.split(" ");
                    NodeBinTree<T> newNode = new NodeBinTree<T>(Integer.parseInt(parts[0]), parts[1], null, null);
                    newNode.setData(parts[1]);
                    scanInNode(newNode);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
    }

    public int size() {
        return this.size(this.root);
    }

    public int size(NodeBinTree<T> current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + this.size(current.getLeft()) + this.size(current.getRight());
        }
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "[]";
        }
        String recStr = this.toString(this.root);
        return "[" + recStr.substring(0, recStr.length() - 1) + "]";
    }

    public String toString(NodeBinTree<T> current) {
        if (current == null) {
            return "";
        }
        return this.toString(current.getLeft()) + current.getData().toString() + ", " + this.toString(current.getRight());
    }

    public int countNodes(NodeBinTree<T> current) {
        if (current == null) {
            return 0;
        } else {
            int count = 1;
            count += countNodes(current.left);
            count += countNodes(current.right);
            return count;
        }
    }

    public int countLeafs(NodeBinTree<T> current) {
        if (current == null) {
            return 0;
        }
        if (current.left == null && current.right == null) {
            return 1;
        } else {
            return countLeafs(current.left) + countLeafs(current.right);
        }
    }

    public int countLeftNodes(NodeBinTree<T> current) {
        int count = 0;
        if (current.left != null) {
            count += 1 + countLeftNodes(current.left);
        }
        if (current.right != null) {
            count += countLeftNodes(current.right);
        }
        return count;
    }

    public int countRightNodes(NodeBinTree<T> current) {
        int count = 0;
        if (current.right != null) {
            count += 1 + countRightNodes(current.right);
        }
        if (current.left != null) {
            count += countRightNodes(current.left);
        }
        return count;
    }

    public int countNodesLevel(NodeBinTree<T> current, int level) {
        if (current == null) {
            return 0;
        }
        if (level == 0) {
            return 1;
        }
        return countNodesLevel(current.left, level - 1) + countNodesLevel(current.right, level - 1);
    }

    public String toPostOrderString(NodeBinTree<T> current) {
        if (current != null) {
            toPostOrderString(current.left);
            toPostOrderString(current.right);
            postResult = postResult + current.data + " ";
        }
        return postResult;
    }

    public String toInOrderString(NodeBinTree<T> current) {
        if (current != null) {
            toInOrderString(current.left);
            inResult = inResult + current.data + " ";
            toInOrderString(current.right);
        }
        return inResult;
    }

    public String toPreOrderString(NodeBinTree<T> current) {
        if (current != null) {
            preResult = preResult + current.data + " ";
            toPreOrderString(current.left);
            toPreOrderString(current.right);
        }
        return preResult;
    }

    public String toLevelOrderString(NodeBinTree<T> current) {
        Queue<NodeBinTree<T>> queue = new LinkedList<NodeBinTree<T>>();
        if (current != null) {
            queue.add(current);
        }
        while (!queue.isEmpty()) {
            NodeBinTree<T> tempNode = queue.remove();
            levelResult = levelResult + tempNode.data + " ";
            if (tempNode.getLeft() != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.getRight() != null) {
                queue.add(tempNode.right);
            }
        }
        return levelResult;
    }

    public String bintreeDriver() {
        BinTreeUMUC<String> tree = new BinTreeUMUC<String>();
        int three = 3;
        tree.insertNode(100, "A");
        tree.insertNode(90, "B");
        tree.insertNode(95, "D");
        tree.insertNode(94, "G");
        tree.insertNode(93, "I");
        tree.insertNode(120, "C");
        tree.insertNode(110, "E");
        tree.insertNode(130, "F");
        tree.insertNode(115, "H");
        tree.insertNode(114, "J");
        tree.insertNode(116, "K");
        return "InOrder: " + '\n'
                + tree.toInOrderString(tree.root) + '\n'
                + "PreOrder: " + '\n'
                + tree.toPreOrderString(tree.root) + '\n'
                + "PostOrder: " + '\n'
                + tree.toPostOrderString(tree.root) + '\n'
                + "LevelOrder: " + '\n'
                + tree.toLevelOrderString(tree.root) + '\n'
                + "Count the number of nodes: " + tree.countNodes(tree.root) + '\n'
                + "Count the number of leaves: " + tree.countLeafs(tree.root) + '\n'
                + "Count the number of left nodes: " + tree.countLeftNodes(tree.root) + '\n'
                + "Count the number of right nodes: " + tree.countRightNodes(tree.root) + '\n'
                + "Level 3 has " + tree.countNodesLevel(tree.root, three) + " nodes" + '\n'
                + tree.getRoot();
    }

    public class NodeBinTree<T> {

        public int key;
        public String data;
        public NodeBinTree<T> left;
        public NodeBinTree<T> right;

        public NodeBinTree(int k, String d, NodeBinTree<T> l, NodeBinTree<T> r) {
            this.key = k;
            this.data = d;
            this.left = l;
            this.right = r;
        }

        public int getKey() {
            return this.key;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return this.data;
        }

        public NodeBinTree<T> getLeft() {
            return this.left;
        }

        public NodeBinTree<T> getRight() {
            return this.right;
        }

        public void setLeft(NodeBinTree<T> newLeft) {
            this.left = newLeft;
        }

        public void setRight(NodeBinTree<T> newRight) {
            this.right = newRight;
        }
    }
}
