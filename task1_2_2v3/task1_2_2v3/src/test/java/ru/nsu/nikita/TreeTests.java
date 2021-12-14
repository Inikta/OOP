package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

public class TreeTests {

    @Test
    public void test1() {
        TreeSettings settings = new TreeSettings(TreeSettings.traverseMode.bfs,
                TreeSettings.removeSetting.attachSubBranches,
                TreeSettings.insertSetting.no);
        Tree<Integer> tree = new Tree<>(0, settings);
        tree.add(1, 0);
        tree.add(2, 0);
        tree.add(3, 0);

        tree.add(11, 1);
        tree.add(12, 1);
        tree.add(21, 2);
        tree.add(31, 3);

        for (Integer node : tree) {
            System.out.println(node.toString());
        }

    }

}
