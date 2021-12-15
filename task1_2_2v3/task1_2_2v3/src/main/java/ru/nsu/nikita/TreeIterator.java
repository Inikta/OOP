package ru.nsu.nikita;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TreeIterator<T> implements ListIterator<T> {

    private Tree<T> tree;

    private int nodesAmount;
    private int currentIndex;
    private TreeNode<T> lastReferredTo;

    private TreeSettings settings;

    public TreeIterator(Tree<T> tree, TreeSettings settings) {
        this.tree = tree;

        this.nodesAmount = tree.getNodesList().size();
        this.currentIndex = 0;

        this.settings = settings;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < nodesAmount;
    }

    @Override
    public T next() {
        if (hasNext()) {
            lastReferredTo = tree.getNodesList().get(currentIndex);
            return tree.getNodesList().get(currentIndex++).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    @Override
    public T previous() {
        if (hasPrevious()) {
            lastReferredTo = tree.getNodesList().get(--currentIndex);
            return tree.getNodesList().get(currentIndex).getContent();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int nextIndex() {
        return currentIndex;
    }

    @Override
    public int previousIndex() {
        return currentIndex - 1;
    }

    @Override
    public void add(T content) {

        //Problems with indexes
        TreeNode<T> leftNeighbor = tree.getNodesList().get(previousIndex());
        TreeNode<T> rightNeighbor;
        try {rightNeighbor = tree.getNodesList().get(nextIndex());}
        catch (IndexOutOfBoundsException indexException) {
            rightNeighbor = null;
        }

        TreeNode<T> leftNeighborParent = leftNeighbor.getParent();
        TreeNode<T> rightNeighborParent;
        try {rightNeighborParent = rightNeighbor.getParent();}
        catch (NullPointerException nullPointerException) {
            rightNeighborParent = null;
        }

        if (leftNeighborParent == null) {
            leftNeighbor.getChildren().add(new TreeNode<>(content, leftNeighbor));
            //tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighbor), leftNeighbor);
        }
        else {
            if (leftNeighborParent == rightNeighborParent) {
                leftNeighborParent.getChildren().add(
                        leftNeighborParent.getChildren().indexOf(lastReferredTo),
                        new TreeNode<>(content, leftNeighborParent)
                );
            } else {
                if (settings.insertParameter == TreeSettings.insertSetting.no) {
                    leftNeighborParent.getChildren().add(new TreeNode<>(content, leftNeighborParent));
                } else if (settings.insertParameter == TreeSettings.insertSetting.insertBeforeSingle) {
                    if (leftNeighbor == rightNeighborParent) {
                        int insertionChildrenIndex = leftNeighbor.getChildren().indexOf(rightNeighbor);
                        TreeNode<T> insertion = new TreeNode<>(content, leftNeighbor);
                        insertion.getChildren().add(rightNeighbor);
                        rightNeighbor.setParent(insertion);

                        leftNeighbor.getChildren().remove(rightNeighbor);
                        leftNeighbor.getChildren().add(insertionChildrenIndex, insertion);
                    } else {
                        leftNeighbor.getChildren().add(new TreeNode<>(content, leftNeighbor));
                    }
                } else if (settings.insertParameter == TreeSettings.insertSetting.insertBeforeBunch) {
                    if (leftNeighbor == rightNeighborParent) {
                        int insertionChildrenIndex = leftNeighbor.getChildren().indexOf(rightNeighbor);
                        TreeNode<T> insertion = new TreeNode<>(content, leftNeighbor);
                        insertion.getChildren().addAll(leftNeighbor.getChildren());
                        leftNeighbor.getChildren().forEach(child -> child.setParent(insertion));

                        leftNeighbor.getChildren().remove(rightNeighbor);
                        leftNeighbor.getChildren().add(insertionChildrenIndex, insertion);

                    } else {
                        leftNeighbor.getChildren().add(new TreeNode<>(content, leftNeighbor));
                    }
                }

            }
        }

        nodesAmount ++; //= tree.getSearchSortedNodes(tree.getRoot()).size();
    }

    @Override
    public void remove() {
        if (settings.getRemoveParameter() == TreeSettings.removeSetting.attachSubBranches) {
            TreeNode<T> lastReferredToParent = lastReferredTo.getParent();
            int toRemoveChildIndex = lastReferredToParent.getChildren().indexOf(lastReferredTo);

            lastReferredTo.getChildren().forEach(node -> node.setParent(lastReferredToParent));

            lastReferredToParent.getChildren().remove(lastReferredTo);
            lastReferredToParent.getChildren().addAll(toRemoveChildIndex, lastReferredTo.getChildren());

        } else if (settings.getRemoveParameter() == TreeSettings.removeSetting.deleteSubBranches) {
            tree.getNodesList().remove(lastReferredTo);
        }

        nodesAmount --;//= tree.getSearchSortedNodes(tree.getRoot()).size();
    }

    @Override
    public void set(T newContent) {
        tree.getNodesList().set(
                tree.getNodesList().indexOf(lastReferredTo),
                new TreeNode<>(newContent, lastReferredTo.getParent()));
    }
}
