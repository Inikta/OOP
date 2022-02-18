package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.nsu.nikita.Node.AddMode.*;
import static ru.nsu.nikita.Node.RemoveMode.*;
import static ru.nsu.nikita.Node.TraverseMode.*;

public class TreeTests {

    @Test
    void addAsChildWithBFS() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);
        tree.add("13", 1, ADD_AS_CHILD);

        tree.add("21", 2, ADD_AS_CHILD);
        tree.add("22", 2, ADD_AS_CHILD);

        tree.add("14", 1, ADD_AS_CHILD);
        tree.add("141", 6, ADD_AS_CHILD);

        tree.add("221", 8, ADD_AS_CHILD);

        tree.add("2211", 9, ADD_AS_CHILD);

        Assertions.assertEquals(12, tree.getTree().size());
        Assertions.assertEquals("141", tree.find(9).getContent());
    }

    @Test
    void addAsChildWithDFS() {
        Node<String> tree = new Node<>("A", DFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);
        tree.add("13", 1, ADD_AS_CHILD);

        tree.add("21", 5, ADD_AS_CHILD);
        tree.add("22", 5, ADD_AS_CHILD);

        tree.add("14", 1, ADD_AS_CHILD);
        tree.add("141", 5, ADD_AS_CHILD);

        tree.add("221", 9, ADD_AS_CHILD);

        tree.add("2211", 10, ADD_AS_CHILD);

        Assertions.assertEquals(12, tree.getTree().size());
        Assertions.assertEquals("2211", tree.find(11).getContent());
    }

    @Test
    void addAsNeighbor() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 1, ADD_AS_NEIGHBOR);

        tree.add("3", 1, ADD_AS_NEIGHBOR);

        Assertions.assertEquals("3", tree.find(2).getContent());
    }

    @Test
    void addInsertBefore() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("3", 1, INSERT_BEFORE);
        Assertions.assertEquals("3", tree.find(1).getContent());
        Assertions.assertEquals("1", tree.find(3).getContent());

        tree.add("4", 0, INSERT_BEFORE);

        Assertions.assertEquals("4", tree.getParent().getContent());
        Assertions.assertEquals("A", tree.find(0).getContent());
        Assertions.assertEquals("3", tree.find(1).getContent());
        Assertions.assertEquals("2", tree.find(2).getContent());
        Assertions.assertEquals("1", tree.find(3).getContent());
    }

    @Test
    void addInsertAfter() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("3", 1, INSERT_AFTER);
        Assertions.assertEquals("3", tree.find(3).getContent());

        tree.add("4", 0, INSERT_AFTER);
        Assertions.assertEquals("4", tree.find(1).getContent());

        tree.add("5", -1, INSERT_AFTER);
        Assertions.assertEquals("5", tree.getParent().getContent());
        Assertions.assertEquals("A", tree.find(0).getContent());
    }

    @Test
    void removeWithSubbranchConcatenation() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);
        tree.add("13", 1, ADD_AS_CHILD);

        tree.add("21", 2, ADD_AS_CHILD);
        tree.add("22", 2, ADD_AS_CHILD);

        tree.add("14", 1, ADD_AS_CHILD);
        tree.add("141", 6, ADD_AS_CHILD);

        tree.add("221", 8, ADD_AS_CHILD);

        tree.add("2211", 10, ADD_AS_CHILD);

        tree.remove(1, CONCATENATE_SUBBRANCH);
        
        Assertions.assertEquals(11, tree.getTree().size());
        Assertions.assertEquals("11", tree.find(2).getContent());
        Assertions.assertEquals("12", tree.find(3).getContent());
        Assertions.assertEquals("13", tree.find(4).getContent());
        Assertions.assertEquals("14", tree.find(5).getContent());
    }

    @Test
    void removeWithSubbranchElimination() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);
        tree.add("13", 1, ADD_AS_CHILD);

        tree.add("21", 2, ADD_AS_CHILD);
        tree.add("22", 2, ADD_AS_CHILD);

        tree.add("14", 1, ADD_AS_CHILD);
        tree.add("141", 6, ADD_AS_CHILD);

        tree.add("221", 8, ADD_AS_CHILD);   //что-то с порядком в tree

        tree.add("2211", 10, ADD_AS_CHILD);

        tree.remove(1, DELETE_SUBBRANCH);

        Assertions.assertEquals(6, tree.getTree().size());
        Assertions.assertEquals("2", tree.find(1).getContent());
        Assertions.assertEquals("21", tree.find(2).getContent());
        Assertions.assertEquals("22", tree.find(3).getContent());
    }

    @Test
    void set() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);

        tree.set(1, "New content");
        Assertions.assertEquals("New content", tree.find(1).getContent());
    }

    @Test
    void iterator() {
        Node<String> tree = new Node<>("A", BFS);
        tree.add("1", 0, ADD_AS_CHILD);
        tree.add("2", 0, ADD_AS_CHILD);

        tree.add("11", 1, ADD_AS_CHILD);
        tree.add("12", 1, ADD_AS_CHILD);

        for (Node<String> node : tree) {
            System.out.println(node.toString());
        }
    }
}
