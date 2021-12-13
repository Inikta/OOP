package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

public class TreeTests {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(0);
        root = root.add(1, 0);
        root = root.add(2, 0);
        root = root.add(3, 0);

        root = root.add(11, 1);
        root = root.add(12, 1);
        root = root.add(21, 2);
        root = root.add(31, 3);

        for (Integer node : root) {
            System.out.println(node.toString());
        }

    }

}
