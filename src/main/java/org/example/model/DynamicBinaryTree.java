package org.example.model;

public class DynamicBinaryTree implements BinaryTree {
    private final int root;
    private BinaryTree left;
    private BinaryTree right;

    public DynamicBinaryTree(int root) {
        this.root = root;
    }

    @Override
    public int getRoot() {
        return this.root;
    }

    @Override
    public BinaryTree getLeft() {
        return this.left;
    }

    @Override
    public BinaryTree getRight() {
        return this.right;
    }

    @Override
    public void addLeft(int value) {
        if (this.left != null) {
            throw new RuntimeException("Left child already exists");
        }
        this.left = new DynamicBinaryTree(value);

    }

    @Override
    public void addRight(int value) {
        if (this.right != null) {
            throw new RuntimeException("Right child already exists");
        }
        this.right = new DynamicBinaryTree(value);
    }

    @Override
    public void removeLeft() {
        this.left = null;
/*
   if (this.left.getLeft()!=null){
            //TODO delete most right chil
        }else if (this.left.getRight()!=null){
            //TODO delete most left child
        } else {
            this.left = null;
        }*/
    }

    @Override
    public void removeRight() {
        this.right = null;
    }
}
