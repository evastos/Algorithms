import java.util.Stack;

/**
 * Created by Eva on 23.2.2015..
 */
public class BstToDll {

    /**
     * Given a Binary Search Tree (BST), convert it to a Doubly Linked List(DLL).
     * The left and right pointers in nodes are to be used as previous and next pointers
     * respectively in converted DLL.
     * The order of nodes in DLL must be same as Inorder of the given Binary Tree.
     * The first node of Inorder traversal (left most node in BT) must be head node of the DLL.
     */

    public static String testCreateDLLFromBST() {
        Trees.TreeNode head = BSTToDLL(Trees.createBST(true));

        StringBuilder result = new StringBuilder();
        result.append(head.getValue());
        result.append(" (").append(head.getLeftChild()
                .getValue()).append(", ").append(head.getRightChild().getValue()).append(')');
        Trees.TreeNode n = head.getRightChild();
        do {
            result.append(" -> ").append(n.getValue()).append(" (").append(n.getLeftChild()
                    .getValue());
            result.append(',').append(n.getRightChild().getValue()).append(')');
            n = n.getRightChild();
        }  while (n.getLeftChild().getValue() != head.getValue());
        return result.toString();
    }

    private static Trees.TreeNode createDLLFromBST(Trees.TreeNode root) {
        Trees.TreeNode head = null;
        Trees.TreeNode prev = null;
        head = binarySearchTreeToDLL(root, prev, head);
        return head;
    }

    private static Trees.TreeNode binarySearchTreeToDLL(Trees.TreeNode node,
                                                        Trees.TreeNode prev,
                                                        Trees.TreeNode head) {

        if (node == null) {
            return head;
        }
        head = binarySearchTreeToDLL(node.getLeftChild(), prev, head);
        node.setLeftChild(prev);
        if (prev != null) {
            if (head != null && head.getValue() == prev.getValue()) {
                prev.setRightChild(node);
                head.setRightChild(node);
            }
        } else {
            if (head == null) {
                head = node;
            }
        }
        Trees.TreeNode rightNode = node.getRightChild();
        head.setLeftChild(node);
        node.setRightChild(head);
        prev = node;
        head = binarySearchTreeToDLL(rightNode, prev, head);
        return head;
    }

    public static Trees.TreeNode BSTToDLL(Trees.TreeNode root) {
        Trees.TreeNode head = null;
        if (root == null) {
            return head;
        }
        Stack<Trees.TreeNode> nodes = new Stack<Trees.TreeNode>();
        nodes.push(root);

        Trees.TreeNode node = root;
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
            nodes.push(node);
        }

        Trees.TreeNode prevNode = null;
        while (!nodes.isEmpty()) {
            Trees.TreeNode currentNode = nodes.pop();
            if (prevNode != null) {
                prevNode.setRightChild(currentNode);
                currentNode.setLeftChild(prevNode);
            } else {
                head = currentNode;
            }
            prevNode = currentNode;
            if (currentNode.getRightChild() != null) {
                Trees.TreeNode nodeRight = currentNode.getRightChild();
                nodes.push(nodeRight);
                while (nodeRight.getLeftChild() != null) {
                    nodeRight = nodeRight.getLeftChild();
                    nodes.push(nodeRight);
                }
            } else {
                currentNode.setRightChild(head);
                head.setLeftChild(currentNode);
            }
        }
        return head;
    }

}

