package org.example.model.nodes;

public class MultipleDictionaryNode {

    private int key;
    private Node value;
    private MultipleDictionaryNode next;

    public MultipleDictionaryNode() {

    }

    public MultipleDictionaryNode(int key, Node value, MultipleDictionaryNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }

    public MultipleDictionaryNode getNext() {
        return next;
    }

    public void setNext(MultipleDictionaryNode next) {
        this.next = next;
    }
}
