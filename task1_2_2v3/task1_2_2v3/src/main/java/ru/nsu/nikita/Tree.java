package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> implements Iterable<T> {

    private TreeNode<T> root;
    private List<TreeNode<T>> nodesList;

    private TreeSettings settings;

    public Tree(T rootContent, TreeSettings settings) {
        this.root = new TreeNode<>(rootContent, null);

        this.nodesList = new ArrayList<>(0);
        this.nodesList.add(root);

        this.settings = settings;
    }

    public List<TreeNode<T>> getSearchSortedNodes(TreeNode<T> currentNode) {
        SearchAlgorithms<T> traverseAlgorithm = new SearchAlgorithms<>();

        if (settings.getTraverseParameter() == TreeSettings.traverseMode.bfs) {
            List<TreeNode<T>> queue = new ArrayList<>();
            queue.add(currentNode);
            currentNode.setChecked(true);

            return traverseAlgorithm.BFS(queue);

        } else if (settings.getTraverseParameter() == TreeSettings.traverseMode.dfs) {
            currentNode.setChecked(true);

            return traverseAlgorithm.DFS(currentNode);
        } else {
            return new ArrayList<>();
        }
    }

    public void add(T content, int parentIndex) {
        TreeIterator<T> localIterator = iterator();
        while (localIterator.hasNext() && localIterator.nextIndex() != parentIndex + 1) {
            localIterator.next();
        }
        localIterator.add(content);
        nodesList = getSearchSortedNodes(root);
    }

    public void remove(int index) {
        TreeIterator<T> localIterator = iterator();
        while (localIterator.hasNext() && localIterator.nextIndex() != index + 1) {
            localIterator.next();
        }
        localIterator.remove();
        nodesList = getSearchSortedNodes(root);
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
