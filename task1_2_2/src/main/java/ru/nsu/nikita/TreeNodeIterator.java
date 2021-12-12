package ru.nsu.nikita;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TreeNodeIterator<T> implements ListIterator<T> {

    private final TreeNode<T> root;
    private Integer childrenAmount;
    private Integer currentChild;
    private TreeNode<T> lastReferredTo;

    public TreeNodeIterator(TreeNode<T> tree) {
        this.root = tree;
        this.childrenAmount = root.getChildrenAmount();
        this.currentChild = 0;
        this.lastReferredTo = null;
    }

    @Override
    public boolean hasNext() {
        return currentChild < childrenAmount;
    }

    @Override
    public T next() {
        if (hasNext()) {
            lastReferredTo = root.getChildren().get(currentChild);
            return root.getChildren().get(currentChild++).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasPrevious() {
        return currentChild > 0;
    }

    @Override
    public T previous() {
        if (hasPrevious()) {
            lastReferredTo = root.getChildren().get(--currentChild);
            return root.getChildren().get(currentChild).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int nextIndex() {
        return currentChild;
    }

    @Override
    public int previousIndex() {
        return currentChild - 1;
    }

    @Override
    public void add(T content) {
        Integer newIndex = Math.abs(root
                .getChildren()
                .indexOf(lastReferredTo) - 1);

        root.getChildren()
                .add(newIndex, new TreeNode<>(content, root, root.getDistinctionIndex() + newIndex));
        currentChild++;

    }

    @Override
    public void remove() {
        root.getChildren().remove(lastReferredTo);
        childrenAmount--;
    }

    @Override
    public void set(T content) {
        root.getChildren()
                .get(root
                        .getChildren()
                        .indexOf(lastReferredTo))
                .setContent(content);
    }
}
