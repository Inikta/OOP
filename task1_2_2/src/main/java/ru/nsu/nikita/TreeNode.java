package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class TreeNode<T> implements Iterable<T> {
    private List<TreeNode<T>> children;
    private int childrenAmount;
    private TreeNode<T> parent;
    private T content;
    private static boolean traverseMode;

    public TreeNode(T content) {
        this.content = content;
        this.parent = null;
        this.children = new ArrayList<>(0);
        this.childrenAmount = 0;
        traverseMode = false;
    }

    public TreeNode(T content, TreeNode<T> parent) {
        this.content = content;
        this.parent = parent;
        this.children = new ArrayList<>(0);
        this.childrenAmount = 0;
        traverseMode = parent.getTraverseMode();
    }

    public TreeNode<T> add(T content, int parentIndex) throws NoSuchElementException {
        TreeIterator<T> localIterator = this.iterator();
        while (localIterator.previousIndex() != parentIndex - 1) {
            localIterator.next();
        }
        localIterator.add(content);

        return localIterator.getTree();
    }

    public void remove(int index) {
        TreeIterator<T> localIterator = this.iterator();
        while (localIterator.previousIndex() != index - 1) {
            localIterator.next();
        }
        localIterator.remove();
    }

    @Override
    public TreeIterator<T> iterator() {
        return new TreeIterator<>(this, traverseMode);
    }

    public int getChildrenAmount() {
        return childrenAmount;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public boolean getTraverseMode() {
        return traverseMode;
    }

    public void setTraverseMode(boolean newTraverseMode) {
        traverseMode = newTraverseMode;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        String contentStr = content.toString();
        return "Parent: " + parent.toString() + "\n" +
                "Content: " + contentStr + "\n" +
                "Children: " + children.toString() + "\n";
    }
}
