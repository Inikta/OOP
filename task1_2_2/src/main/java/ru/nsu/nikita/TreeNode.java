package ru.nsu.nikita;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> implements Iterable<T> {
    private LinkedList<TreeNode<T>> children;
    private List<Integer> branchLengths;
    private Integer nodeLayer;
    private Integer layersAmount;
    private Integer childrenAmount;
    private TreeNode<T> parent;
    private T content;
    private Integer distinctionIndex;
    private static enum mode {bfs, dfs};
    private static mode traverseMode;

    public TreeNode(T content) {
        this.content = content;
        this.distinctionIndex = 0;
        this.parent = null;
        this.children = new LinkedList<>();
        this.childrenAmount = 0;
        this.branchLengths = new ArrayList<>(0);
        traverseMode = mode.bfs;
        this.nodeLayer = 0;
        this.layersAmount = 0;
    }

    public TreeNode(T content, TreeNode<T> parent, Integer distinctionIndex) {
        this.content = content;
        this.parent = parent;
        this.children = new LinkedList<>();
        this.childrenAmount = 0;
        this.branchLengths = new ArrayList<>(0);
        this.distinctionIndex = distinctionIndex;
        traverseMode = parent.getTraverseMode();
    }

    @Override
    public TreeNodeIterator<T> iterator() {
        return new TreeNodeIterator<>(this);
    }

    public Integer getChildrenAmount() {
        return childrenAmount;
    }

    public LinkedList<TreeNode<T>> getChildren() {
        return children;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public Integer getDistinctionIndex() {
        return distinctionIndex;
    }

    public mode getTraverseMode() {
        return traverseMode;
    }

    public void setTraverseMode(mode newTraverseMode) {
        traverseMode = newTraverseMode;
    }

}
