package ru.nsu.nikita;

import org.junit.jupiter.api.Test;
import ru.nsu.nikita.Node;

public class TreeTests {

    public Node<String> tree;

    @Test
    void getDescendants() {

    }

    @Test
    void find() {
    }

    @Test
    void add() {
        Node<String> tree = new Node<>("A");
        tree.add("B", 0, Node.AddMode.ADD_AS_CHILD);
        tree.add("C", 0, Node.AddMode.ADD_AS_CHILD);
        tree.add("BA", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("BB", 1, Node.AddMode.ADD_AS_CHILD);
        tree.add("BC", 1, Node.AddMode.ADD_AS_CHILD);
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
