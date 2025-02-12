package org.example.util;

import org.example.model.BinaryTree;

public class BinaryTreeUtil {
    public static int total(BinaryTree binaryTree){
        if (binaryTree == null){
            return 0;
        }

        return 1 + total(binaryTree.getLeft()) + total(binaryTree.getRight());
    }
    public static int height(BinaryTree binaryTree){
        if (binaryTree == null){
            return 0;
        }

        return 1 + Math.max(height(binaryTree.getLeft()), height(binaryTree.getRight()));
    }
    public static boolean isFull(BinaryTree binaryTree){
        return total(binaryTree) == Math.pow(2, height(binaryTree)) - 1;
    }
}
