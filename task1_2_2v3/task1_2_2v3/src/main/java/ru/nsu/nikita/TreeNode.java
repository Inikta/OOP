package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    private T content;

    private boolean checked;

    public TreeNode(T content, TreeNode<T> parent) {
        this.parent = parent;
        this.content = content;

        this.children = new ArrayList<>(0);

        this.checked = false;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean newChecked) {
        this.checked = newChecked;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "parent=" + parent +
                ", children=" + children +
                ", content=" + content +
                ", checked=" + checked +
                '}';
    }
}
