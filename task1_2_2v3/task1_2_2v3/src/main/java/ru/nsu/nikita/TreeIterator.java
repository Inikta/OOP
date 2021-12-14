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
        TreeNode<T> leftNeighbor = tree.getNodesList().get(previousIndex()).getParent();
        TreeNode<T> rightNeighbor = tree.getNodesList().get(nextIndex()).getParent();

        TreeNode<T> leftNeighborParent = tree.getNodesList().get(previousIndex() - 1).getParent();
        TreeNode<T> rightNeighborParent = tree.getNodesList().get(nextIndex()).getParent();

        if (leftNeighborParent == rightNeighborParent) {
            leftNeighborParent.getChildren().add(
                    leftNeighborParent.getChildren().indexOf(lastReferredTo),
                    new TreeNode<>(content, leftNeighborParent)
            );
            tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighborParent), leftNeighborParent);
        } else {
            if (settings.insertParameter == TreeSettings.insertSetting.no) {
                leftNeighborParent.getChildren().add(new TreeNode<>(content, leftNeighborParent));
                tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighborParent), leftNeighborParent);
            } else if (settings.insertParameter == TreeSettings.insertSetting.insertBeforeSingle) {
                if (leftNeighbor == rightNeighborParent) {
                    int insertionChildrenIndex = leftNeighbor.getChildren().indexOf(rightNeighbor);
                    TreeNode<T> insertion = new TreeNode<>(content, leftNeighbor);
                    insertion.getChildren().add(rightNeighbor);
                    rightNeighbor.setParent(insertion);

                    leftNeighbor.getChildren().remove(rightNeighbor);
                    leftNeighbor.getChildren().add(insertionChildrenIndex, insertion);

                    tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighbor), leftNeighbor);
                    tree.getNodesList().set(tree.getNodesList().indexOf(rightNeighbor), rightNeighbor);
                } else {
                    leftNeighbor.getChildren().add(new TreeNode<>(content, leftNeighbor));
                    tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighbor), leftNeighbor);
                }
            } else if (settings.insertParameter == TreeSettings.insertSetting.insertBeforeBunch) {
                if (leftNeighbor == rightNeighborParent) {
                    int insertionChildrenIndex = leftNeighbor.getChildren().indexOf(rightNeighbor);
                    TreeNode<T> insertion = new TreeNode<>(content, leftNeighbor);
                    insertion.getChildren().addAll(leftNeighbor.getChildren());
                    leftNeighbor.getChildren().forEach(child -> child.setParent(insertion));

                    leftNeighbor.getChildren().remove(rightNeighbor);
                    leftNeighbor.getChildren().add(insertionChildrenIndex, insertion);

                    tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighbor), leftNeighbor);
                    tree.getNodesList().stream()
                            .filter(node -> node.getParent() == leftNeighbor)
                            .forEach(node -> node.setParent(insertion));
                } else {
                    leftNeighbor.getChildren().add(new TreeNode<>(content, leftNeighbor));
                    tree.getNodesList().set(tree.getNodesList().indexOf(leftNeighbor), leftNeighbor);
                }
            }

        }

        nodesAmount = tree.getSearchSortedNodes(tree.getRoot()).size();
    }

    @Override
    public void remove() {
        if (settings.getRemoveParameter() == TreeSettings.removeSetting.attachSubBranches) {
            TreeNode<T> lastReferredToParent = lastReferredTo.getParent();
            int toRemoveChildIndex = lastReferredToParent.getChildren().indexOf(lastReferredTo);

            lastReferredTo.getChildren().forEach(node -> node.setParent(lastReferredToParent));

            lastReferredToParent.getChildren().remove(lastReferredTo);
            lastReferredToParent.getChildren().addAll(toRemoveChildIndex, lastReferredTo.getChildren());

            tree.getNodesList().set(tree.getNodesList().indexOf(lastReferredToParent), lastReferredToParent);
            tree.getNodesList().stream()
                    .filter(node -> node.getParent() == lastReferredTo)
                    .forEach(node -> node.setParent(lastReferredToParent));

        } else if (settings.getRemoveParameter() == TreeSettings.removeSetting.deleteSubBranches) {
            tree.getNodesList().remove(lastReferredTo);
        }

        nodesAmount = tree.getSearchSortedNodes(tree.getRoot()).size();
    }

    @Override
    public void set(T newContent) {
        tree.getNodesList().set(
                tree.getNodesList().indexOf(lastReferredTo),
                new TreeNode<>(newContent, lastReferredTo.getParent()));
    }
}
