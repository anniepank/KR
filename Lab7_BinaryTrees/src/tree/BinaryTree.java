package tree;

public class BinaryTree<T1 extends Comparable<T1>, T2> {

    private Node<T1, T2> root;

    public static class Node<T1 extends Comparable<T1>, T2> {
        T1 key;
        T2 value;

        Node<T1, T2> left, right, parent;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node<T1, T2> getRoot() {
        return root;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node current) {
        if (current == null) {
            return 0;
        }
        return Math.max(getHeight(current.left), getHeight(current.right)) + 1;
    }

    public int getWidth() {
        return (int) Math.pow(2, getHeight());
    }

    public void addToTree(T1 key, T2 value) {
        Node<T1, T2> nodeToAdd = new Node<>(key, value);
        addToTree(nodeToAdd);
    }

    public void addToTree(Node<T1, T2> nodeToAdd) {
        Node<T1, T2> y = null;
        Node<T1, T2> x = this.root;

        while (x != null) {
            y = x;

            int compareResult = nodeToAdd.key.compareTo(x.key);

            if (compareResult < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        nodeToAdd.parent = y;

        // in case if tree was empty;

        if (y == null) {
            this.root = nodeToAdd;
        } else {
            int compareResult = nodeToAdd.key.compareTo(y.key);
            if (compareResult < 0) {
                y.left = nodeToAdd;
            } else {
                y.right = nodeToAdd;
            }
        }
    }

    public void transplant(Node<T1, T2> u, Node<T1, T2> v) {
        if (root == u) {
            root = v;
            return;
        }
        if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public Node<T1, T2> findMinimum(Node<T1, T2> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void deleteFromTree(Node<T1, T2> nodeToDelete) {
        if (nodeToDelete.left == null) {
            transplant(nodeToDelete, nodeToDelete.right);
            return;
        }
        if (nodeToDelete.right == null) {
            transplant(nodeToDelete, nodeToDelete.left);
            return;
        }

        Node<T1, T2> minimumNode = findMinimum(nodeToDelete.right);
        if (minimumNode.parent != nodeToDelete) {
            transplant(minimumNode, minimumNode.right);
            minimumNode.right = nodeToDelete.right;
            minimumNode.right.parent = minimumNode;
        }
        transplant(nodeToDelete, minimumNode);
        minimumNode.left = nodeToDelete.left;
        minimumNode.left.parent = minimumNode;
    }

    public Node<T1, T2> findNode(Node<T1, T2> x, T1 key) {
        if (x == null || x.key.compareTo(key) == 0) {
            return x;
        }
        int compareResult = key.compareTo(x.key);
        if (compareResult < 0) {
            return findNode(x.left, key);
        } else {
            return findNode(x.right, key);
        }
    }

    public abstract static class Visitor<T1 extends Comparable<T1>, T2> {
        abstract void visit(BinaryTree.Node<T1, T2> node);
    }

    public void inOrderTreeWalk(Node<T1, T2> root, Visitor<T1, T2> v) {
        if (root != null) {
            inOrderTreeWalk(root.left, v);
            v.visit(root);
            inOrderTreeWalk(root.right, v);
        }
    }

    public void preOrderTreeWalk(Node<T1, T2> root, Visitor<T1, T2> v) {
        if (root != null) {
            v.visit(root);
            preOrderTreeWalk(root.left, v);
            preOrderTreeWalk(root.right, v);
        }
    }

    public void postOrderTreeWalk(Node<T1, T2> root, Visitor<T1, T2> v) {
        if (root != null) {
            postOrderTreeWalk(root.left, v);
            postOrderTreeWalk(root.right, v);
            v.visit(root);
        }
    }
}
