package org.example;

import org.example.model.BinaryTree;
import org.example.model.DynamicBinaryTree;
import org.example.util.BinaryTreeUtil;

public class App {
    public static void main(String[] args) {
        BinaryTree tree = new DynamicBinaryTree(5);
        tree.addLeft(2);
        tree.addRight(3);
        tree.getLeft().addLeft(1);
        tree.getLeft().addRight(4);
        tree.getRight().addLeft(6);
        tree.getRight().addRight(7);
        System.out.println(BinaryTreeUtil.total(tree));
    }
}