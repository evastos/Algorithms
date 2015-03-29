package com.facebook.eva.algorithm;

/**
 * Created by Eva on 21.2.2015..
 */
public class Lists {

    /* Reverse a linked list: recursive and non-recursive method */

    static class Node {

        private int value;
        private Node next = null;

        public Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static Node reverseLinkedList(Node start) {
        if (start == null) {
            return null;
        }

        if (start.getNext() == null) {
            return start;
        }

        Node prev = null;
        Node node = start;

        while (node.getNext() != null) {
            Node nextNode = node.getNext();
            node.setNext(prev);
            prev = node;
            node = nextNode;
        }
        node.setNext(prev);
        return node;
    }

    public static Node reverseLinkedListRecursive(Node prev, Node node) {
        if (node == null) {
            return null;
        }

        Node nextNode = node.getNext();
        node.setNext(prev);

        if (nextNode == null) {
            return node;
        }
        return reverseLinkedListRecursive(node, nextNode);
    }



}
