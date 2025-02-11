package org.example.model.nodes;

public class LogicDictionaryNode {

    private String key;
    private int[][] value;
    private LogicDictionaryNode next;

    public LogicDictionaryNode() {
    }

    public LogicDictionaryNode(String key, int[][] value, LogicDictionaryNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }

    public LogicDictionaryNode getNext() {
        return next;
    }

    public void setNext(LogicDictionaryNode next) {
        this.next = next;
    }
}
