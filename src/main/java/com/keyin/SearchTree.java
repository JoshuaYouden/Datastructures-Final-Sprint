package com.keyin;

public class SearchTree {
    TreeNode root;

    public SearchTree() {
        root = null;
    }

    void insert(int value) {
        root = insertRec(root, value);

    }

    private TreeNode insertRec(TreeNode root, int value) {

        if(root == null) {
            root = new TreeNode(value);
            return root;
        }

        if(value<root.getValue()){
            root.setLeft( insertRec(root.getLeft(),value));
        } else if (value > root.getValue()) {
            root.setRight(insertRec(root.getRight(),value));

        }
        return root;
    }

    void inorder() {
        inorderRec(root);
        System.out.println("\n");
    }

    void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.print(root.getValue() + " ");
            inorderRec(root.getRight());
        }
    }

}
