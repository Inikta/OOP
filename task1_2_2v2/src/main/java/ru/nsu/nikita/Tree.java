package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tree<T> {
    private Tree<T> parent;
    private List<Tree<T>> children;
    private T content;

    private int distinctionIndex;
    private int layer;
    private int traverseMode;

    public Tree(T content, Tree<T> parent) {
        this.parent = parent;
        this.content = content;
        this.children = new ArrayList<>(0);
        this.distinctionIndex = parent.getDistinctionIndex() + parent.getChildren().size();
        this.layer = parent.layer + 1;
    }

    //Getters

    public int getMaxChildrenDistinctionIndex() {
        for (Tree<T> child : children) {

        }
    }

    public Tree<T> getParent() {
        return parent;
    }

    public List<T> getChildren() {
        return children;
    }

    public T getContent() {
        return content;
    }

    public int getDistinctionIndex() {
        return distinctionIndex;
    }

    public int getTraverseMode() {
        return traverseMode;
    }

    //Setters


    public void setContent(T content) {
        this.content = content;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public void setDistinctionIndex(int distinctionIndex) {
        this.distinctionIndex = distinctionIndex;
    }

    public void setTraverseMode(int traverseMode) {
        this.traverseMode = traverseMode;
    }
}
