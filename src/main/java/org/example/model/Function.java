package org.example.model;

public interface Function {
    int get(int key);
    Set getKeys();
    void add(int key, int value);
    void remove(int key);
}
