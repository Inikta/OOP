package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> implements Iterable<T> {

    private TreeNode<T> root;
    private List<TreeNode<T>> nodesList;

    private TreeSettings settings;

    private List<TreeNode<T>> queue;

    public Tree(T rootContent, TreeSettings settings) {
        this.root = new TreeNode<>(rootContent, null);

        this.nodesList = new ArrayList<>(0);
        this.nodesList.add(root);

        this.settings = settings;

        this.queue = new ArrayList<>(0);
        this.queue.add(root);
    }

    public List<TreeNode<T>> getSearchSortedNodes(TreeNode<T> currentNode) {
        List<TreeNode<T>> result = new ArrayList<>();
        result.add(currentNode);
        if (settings.getTraverseParameter() == TreeSettings.traverseMode.bfs) {
            for (TreeNode<T> node : queue) {
                for (TreeNode<T> child : queue.get(0).getChildren()) {
                    if (!child.isChecked()) {
                        queue.add(child);
                        child.setChecked(true);
                        result.add(child);
                    }
                }
                queue.remove(node);
            }
        } else if (settings.getTraverseParameter() == TreeSettings.traverseMode.dfs) {
            for (TreeNode<T> node : currentNode.getChildren()) {
                result.addAll(getSearchSortedNodes(node));
            }
        }

        return result;
    }

    public void add(T content, int parentIndex) {
        TreeIterator<T> localIterator = iterator();
        while (localIterator.hasNext() && localIterator.nextIndex() != parentIndex + 1) {
            localIterator.next();
        }
        localIterator.add(content);
    }

    public void remove(int index) {
        TreeIterator<T> localIterator = iterator();
        while (localIterator.hasNext() && localIterator.nextIndex() != index + 1) {
            localIterator.next();
        }
        localIterator.remove();
    }


    @Override
    public TreeIterator<T> iterator() {
        return new TreeIterator<>(this, settings);
    }

    public List<TreeNode<T>> getNodesList() {
        return nodesList;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public TreeSettings getSettings() {
        return settings;
    }

    public void setSettings(TreeSettings settings) {
        this.settings = settings;
    }
}
