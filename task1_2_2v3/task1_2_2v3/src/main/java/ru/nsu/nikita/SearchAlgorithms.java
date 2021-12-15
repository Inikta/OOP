package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithms<T> {

    public List<TreeNode<T>> BFS (List<TreeNode<T>> queue) {
        List<TreeNode<T>> result = new ArrayList<>();
        List<TreeNode<T>> newQueue = new ArrayList<>();
        for (TreeNode<T> node : queue) {
            for (TreeNode<T> child : node.getChildren()) {
                if (!child.isChecked()) {
                    newQueue.add(child);
                    child.setChecked(true);
                }
            }
        }
        result.addAll(BFS(newQueue));
        return result;
    }

    public List<TreeNode<T>> DFS (TreeNode<T> currentNode) {
        List<TreeNode<T>> result = new ArrayList<>();
        for (TreeNode<T> child : currentNode.getChildren()) {
            if (!child.isChecked()) {
                child.setChecked(true);
                result.addAll(DFS(child));
            }
        }

        return result;
    }
}
