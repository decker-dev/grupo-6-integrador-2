package org.example.model;

public interface PriorityQueue {

    int getFirst();
    int getPriority();
    boolean isEmpty();
    void add(int a, int priority);
    void remove();

}
