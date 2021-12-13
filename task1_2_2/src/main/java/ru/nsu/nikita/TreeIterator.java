package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TreeIterator<T> implements ListIterator<T> {

    private final TreeNode<T> root;

    private int nodesAmount;
    private int currentNode;
    private List<TreeNode<T>> treeList;

    private TreeNode<T> lastReferredTo;
    private final boolean traverseMode;

    public TreeIterator(TreeNode<T> tree, boolean traverseMode) {
        this.root = tree;
        this.treeList = getWholeTree(new ArrayList<>(0), true);

        this.nodesAmount = treeList.size();
        this.currentNode = 0;

        this.lastReferredTo = null;
        this.traverseMode = traverseMode;
    }

    public TreeNode<T> getTree() {
        return treeList.get(0);
    }

    private List<TreeNode<T>> getWholeTree(List<TreeNode<T>> wholeTree, boolean start) {

        if (!traverseMode && root.getChildren().size() > 0) {
            if (start) {
                wholeTree.add(root);
            }
            wholeTree.addAll(root.getChildren());
            for (TreeNode<T> child : root.getChildren()) {
                wholeTree = child.iterator().getWholeTree(wholeTree, false);
            }
        } else if (traverseMode) {
            for (TreeNode<T> child : root.getChildren()) {
                wholeTree.add(root);
                if (root.getChildren().size() > 0) {
                    wholeTree = child.iterator().getWholeTree(wholeTree, false);
                }
            }
        }

        return wholeTree;
    }

    @Override
    public boolean hasNext() {
        return currentNode < nodesAmount;
    }

    @Override
    public T next() {
        if (hasNext()) {
            lastReferredTo = treeList.get(currentNode);
            return treeList.get(currentNode++).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasPrevious() {
        return currentNode > 0;
    }

    @Override
    public T previous() {
        if (hasPrevious()) {
            lastReferredTo = treeList.get(--currentNode);
            return treeList.get(currentNode).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int nextIndex() {
        return currentNode;
    }

    @Override
    public int previousIndex() {
        return currentNode - 1;
    }

    @Override
    public void add(T content) {
        int newIndex = Math.abs(treeList
                .indexOf(lastReferredTo) - 1);

        treeList.add(newIndex, new TreeNode<>(content, root));
        treeList = getWholeTree(treeList, traverseMode);
        nodesAmount = treeList.size();
        currentNode++;
        lastReferredTo = null;
    }

    @Override
    public void remove() {
        root.getChildren().addAll(lastReferredTo.getChildren());
        lastReferredTo.getChildren().forEach(node -> node.setParent(root));

        treeList.remove(lastReferredTo);
        treeList = getWholeTree(treeList, traverseMode);
        nodesAmount = treeList.size();
        lastReferredTo = null;
    }

    @Override
    public void set(T content) {
        if (lastReferredTo != null) {
            treeList
                    .get(treeList
                            .indexOf(lastReferredTo))
                    .setContent(content);
        } else {
            throw new IllegalStateException();
        }

    }
}
