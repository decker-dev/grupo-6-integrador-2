package org.example.model;

public interface BinaryTree {
    int getRoot();
    BinaryTree getLeft();
    BinaryTree getRight();
    void addLeft(int value);
    void addRight(int value);
    void removeLeft();
    void removeRight();
}
