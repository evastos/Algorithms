package com.facebook.eva.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Eva on 21.2.2015..
 */
public class Trees {

    public static class TreeNode {

        private int value;
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;

        public TreeNode(int value) {
            this.value = value;
        }

        public void setLeftChild(TreeNode node) {
            this.leftChild = node;
        }

        public void setRightChild(TreeNode node) {
            this.rightChild = node;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public int getValue() {
            return value;
        }

    }

    public static String testSumsForTreePaths() {
        StringBuilder builder = new StringBuilder();
        List<Integer> sums = getSumsFromTreePaths(createTree(), null, 0);
        for (Integer sum : sums) {
            builder.append("sum = ").append(sum).append(" - ");
        }
        return builder.substring(0, builder.length() - 3);
    }

    private static TreeNode createTree() {
        TreeNode root = new TreeNode(0);
        addNode(root, 0);
        return root;
    }

    private static void addNode(TreeNode root, int i) {
        if (i > 8 || root == null) {
            return;
        }

        root.setLeftChild(new TreeNode(2 * i + 1));
        root.setRightChild(new TreeNode(2 * i + 2));

        addNode(root.getLeftChild(), root.getLeftChild().getValue());
        addNode(root.getRightChild(), root.getRightChild().getValue());
    }

    public static TreeNode createBST(boolean useValidBST) {
        TreeNode root = new TreeNode(0);
        addBSTNode(root, 0, !useValidBST);
        return root;
    }

    private static void addBSTNode(TreeNode root, int i, boolean fuckItUp) {
        if (i > 2 || root == null) {
            return;
        }
        if (i == 1 && fuckItUp) {
            root.setLeftChild(new TreeNode(root.getValue() + 100));
        } else {
            root.setLeftChild(new TreeNode(root.getValue() - 100 / (i + 1)));
        }
        root.setRightChild(new TreeNode(root.getValue() + 100 / (i + 1)));

        addBSTNode(root.getLeftChild(), i + 1, fuckItUp);
        addBSTNode(root.getRightChild(), i + 1, fuckItUp);
    }

    public static List<Integer> getSumsFromTreePaths(TreeNode node, List<Integer> sums, int sum) {
        if (sums == null) {
            sums = new ArrayList<Integer>();
        }

        if (node == null) {
            return sums;
        }

        sum += node.getValue();

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            sums.add(sum);
            return sums;
        }

        sums = getSumsFromTreePaths(node.getLeftChild(), sums, sum);
        sums = getSumsFromTreePaths(node.getRightChild(), sums, sum);
        return sums;
    }

    public static String testPrintTreeLevelByLevel() {
        return printTreeLevelByLevel(createTree());
    }

    public static String testPrintBSTLevelByLevel() {
        return printTreeLevelByLevel(createBST(USE_VALID_BINARY_SEARCH_TREE));
    }

    public static String printTreeLevelByLevel(TreeNode root) {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            return "";
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        final TreeNode newLevel = null;

        q.add(root);
        q.add(newLevel);

        while (!q.isEmpty()) {
            TreeNode element = q.poll();
            if (element == newLevel) {
                if (q.peek() != null) {
                    result.append('\n');
                    System.out.println();
                    q.add(newLevel);
                }

            } else {
                System.out.format("%d ", element.getValue());
                result.append(element.getValue()).append(" ");
                if (element.getLeftChild() != null) {
                    q.add(element.getLeftChild());
                }
                if (element.getRightChild() != null) {
                    q.add(element.getRightChild());
                }
            }
        }
        return result.toString();
    }

    /**
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized
     * with the root node of a BST.
     * <p/>
     * Calling next() will return the next smallest number in the BST.
     * <p/>
     * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
     */

    public static String testTreeIterator() {
        StringBuilder result = new StringBuilder();
        result.append("Tree: \n");
        result.append(testPrintTreeLevelByLevel());
        result.append("\n");
        TreeIterator iterator = new TreeIterator(createTree());
        while (iterator.hasNext()) {
            TreeNode n = iterator.next();
            result.append(n.getValue()).append(" ");
        }
        return result.toString();
    }

    public static class TreeIterator {

        private Stack<TreeNode> nodes = new Stack<TreeNode>();

        public TreeIterator(TreeNode root) {
            addNodeToStack(root);
        }

        public TreeNode next() {
            if (nodes.isEmpty()) {
                return null;
            }
            TreeNode nextNode = nodes.pop();
            if (nextNode.getRightChild() != null) {
                addNodeToStack(nextNode.getRightChild());
            }
            return nextNode;
        }

        private void addNodeToStack(TreeNode node) {
            nodes.push(node);
            while (node.getLeftChild() != null) {
                node = node.getLeftChild();
                nodes.push(node);
            }
        }

        public boolean hasNext() {
            return !nodes.isEmpty();
        }
    }

    /**
     * Given root of binary search tree and K as input, find K-th smallest element in BST.
     */

    private static final int K = 14;

    private static final boolean USE_VALID_BINARY_SEARCH_TREE = false;

    public static String testFindKth(boolean ascending) {
        StringBuilder result = new StringBuilder();
        result.append("Tree: \n");
        result.append(testPrintTreeLevelByLevel());
        result.append("\n-----------\n");
        TreeNode kthNode;
        if (ascending) {
            kthNode = findKthSmallest(createTree(), K);
            result.append(K).append("-smallest: ");
        } else {
            kthNode = findKthLargest(createTree(), K);
            result.append(K).append("-largest: ");
        }
        if (kthNode != null) {
            result.append(kthNode.getValue());
        } else {
            result.append("null");
        }
        return result.toString();
    }

    public static TreeNode findKthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return null;
        }

        Stack<TreeNode> nodes = new Stack<TreeNode>();
        nodes.push(root);

        TreeNode node = root;
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
            nodes.push(node);
        }

        node = null;
        while (k > 0) {
            if (nodes.isEmpty()) {
                break;
            }
            node = nodes.pop();
            if (node.getRightChild() != null) {
                TreeNode nodeRight = node.getRightChild();
                nodes.push(nodeRight);
                while (nodeRight.getLeftChild() != null) {
                    nodeRight = nodeRight.getLeftChild();
                    nodes.push(nodeRight);
                }
            }
            k--;
        }
        return node;
    }

    public static TreeNode findKthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return null;
        }

        Stack<TreeNode> nodes = new Stack<TreeNode>();
        nodes.push(root);

        TreeNode node = root;
        while (node.getRightChild() != null) {
            node = node.getRightChild();
            nodes.push(node);
        }

        node = null;
        while (k > 0) {
            if (nodes.isEmpty()) {
                break;
            }
            node = nodes.pop();
            if (node.getLeftChild() != null) {
                TreeNode nodeLeft = node.getLeftChild();
                nodes.push(nodeLeft);
                while (nodeLeft.getRightChild() != null) {
                    nodeLeft = nodeLeft.getRightChild();
                    nodes.push(nodeLeft);
                }
            }
            k--;
        }
        return node;
    }

    public static String testValidateBST() {
        StringBuilder result = new StringBuilder();
        result.append("Tree: \n");
        result.append(testPrintBSTLevelByLevel());
        result.append("\n-----------\n");
        result.append("Is valid binary search tree: ").append(validateBinarySearchTreeRecursive
                (createBST
                        (USE_VALID_BINARY_SEARCH_TREE)));
        return result.toString();
    }

    public static boolean validateBinarySearchTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> nodes = new Stack<TreeNode>();
        nodes.push(root);

        TreeNode node = root;
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
            nodes.push(node);
        }

        TreeNode prevNode = null;
        while (!nodes.isEmpty()) {
            TreeNode currentNode = nodes.pop();
            if (prevNode != null && prevNode.getValue() > currentNode.getValue()) {
                return false;
            }
            prevNode = currentNode;
            if (currentNode.getRightChild() != null) {
                TreeNode nodeRight = currentNode.getRightChild();
                nodes.push(nodeRight);
                while (nodeRight.getLeftChild() != null) {
                    nodeRight = nodeRight.getLeftChild();
                    nodes.push(nodeRight);
                }
            }
        }
        return true;
    }

    public static boolean validateBinarySearchTreeRecursive(TreeNode root) {
        return validateBinarySearchTreeRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateBinarySearchTreeRecursive(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.getValue() >= min && node.getValue() <= max) {
            return validateBinarySearchTreeRecursive(node.getLeftChild(), min,
                    node.getValue()) && validateBinarySearchTreeRecursive(node.getRightChild(),
                    node.getValue(), max);
        } else {
            return false;
        }
    }
}
