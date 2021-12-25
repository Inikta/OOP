package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTests {

    public Node<String> tree;

    @Test
    void getDescendants() {

    }

    @Test
    void find() {
    }

    @Test
    void addBFS() {
        Node<String> tree = new Node<>("A", Node.TraverseMode.BFS);
        tree.add("1", 0, Node.AddMode.ADD_AS_CHILD);
        tree.add("2", 0, Node.AddMode.ADD_AS_CHILD);

        tree.add("11", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("12", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("13", 1, Node.AddMode.ADD_AS_CHILD);

        tree.add("21", 2, Node.AddMode.ADD_AS_CHILD);
        tree.add("22", 2, Node.AddMode.ADD_AS_CHILD);

        tree.add("14", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("141", 6, Node.AddMode.ADD_AS_CHILD);

        tree.add("221", 8, Node.AddMode.ADD_AS_CHILD);

        tree.add("2211", 9, Node.AddMode.ADD_AS_CHILD);

        Assertions.assertEquals(12, tree.getTree().size());
        Assertions.assertEquals("141", tree.find(9).getContent());
    }

    @Test
    void addDFS() {
        Node<String> tree = new Node<>("A", Node.TraverseMode.DFS);
        tree.add("1", 0, Node.AddMode.ADD_AS_CHILD);
        tree.add("2", 0, Node.AddMode.ADD_AS_CHILD);

        tree.add("11", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("12", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("13", 1, Node.AddMode.ADD_AS_CHILD);

        tree.add("21", 5, Node.AddMode.ADD_AS_CHILD);
        tree.add("22", 5, Node.AddMode.ADD_AS_CHILD);

        tree.add("14", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("141", 5, Node.AddMode.ADD_AS_CHILD);

        tree.add("221", 9, Node.AddMode.ADD_AS_CHILD);

        tree.add("2211", 10, Node.AddMode.ADD_AS_CHILD);

        Assertions.assertEquals(12, tree.getTree().size());
        Assertions.assertEquals("2211", tree.find(10).getContent());


    }

    @Test
    void remove() {
    }

    @Test
    void set() {
    }

    @Test
    void iterator() {
    }
}
