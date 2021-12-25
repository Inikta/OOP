package ru.nsu.nikita;

import java.util.*;

public class Node<T> implements Iterable<Node<T>> {

    public static enum AddMode {ADD_AS_CHILD, ADD_AS_NEIGHBOR, INSERT_BEFORE, INSERT_AFTER}

    public static enum RemoveMode {CONCATENATE_SUBBRANCH, DELETE_SUBBRANCH}

    public static enum TraverseMode {BFS, DFS}

    private List<Node<T>> children;
    private Node<T> parent;
    private T content;
    private int nodeIndex;

    private List<Node<T>> tree;

    private boolean checked;

    private TraverseMode currentTraverseMode;

    /** ru.nsu.nikita.Node constructor, which is intended to be used for tree creation.
     * @param content content, which is contained in this node.
     */
    public Node(T content) {
        this.parent = null;
        this.content = content;
        this.children = new ArrayList<>();
        this.nodeIndex = 0;

        this.currentTraverseMode = TraverseMode.BFS;
        this.tree = makeTree(currentTraverseMode);

        this.checked = false;
    }

    /** ru.nsu.nikita.Node constructor, which is intended to be used for tree creation.
     * @param content content, which is contained in this node.
     * @param mode traversing mode for the (sub-)tree having this node as root. Available modes: BFS (breadth-first search), DFS (depth-first search).
     */
    public Node(T content, TraverseMode mode) {
        this.parent = null;
        this.content = content;
        this.children = new ArrayList<>();
        this.nodeIndex = 0;

        this.currentTraverseMode = mode;
        this.tree = makeTree(currentTraverseMode);

        this.checked = false;
    }

    /** ru.nsu.nikita.Node constructor, which is intended to be used for adding new nodes to existing tree.
     * @param content content, which is contained in this node.
     * @param parent node, which is obe level high and to which this node is added as a child.
     */
    public Node(T content, Node<T> parent) {
        this.content = content;
        this.children = new ArrayList<>();
        this.nodeIndex = parent.getChildren().size() - 1;
        setParent(parent);

        this.currentTraverseMode = parent.getCurrentTraverseMode();
        this.tree = makeTree(currentTraverseMode);

        this.checked = false;
    }

    /** Get all nodes for which this node is the root. List is sorted in order of traversing (BFS, DFS).
     * @param mode traversing mode for the (sub-)tree having this node as root. Available modes: BFS (breadth-first search), DFS (depth-first search).
     * @return list of all tree nodes sorted by search algorithm.
     */
    public List<Node<T>> makeTree(TraverseMode mode) {
        return makeTree(mode, new ArrayList<>(), this);
    }

    /** Get all nodes for which this node is the root. List is sorted in order of traversing (BFS, DFS).
     * @param mode traversing mode for the (sub-)tree having this node as root. Available modes: BFS (breadth-first search), DFS (depth-first search).
     * @param queue queue of checked nodes, whose children should be checked. Is used for standard BFS (breadth-first search) algorithm.
     * @return list of all tree nodes sorted by search algorithm.
     */
    private List<Node<T>> makeTree(TraverseMode mode, List<Node<T>> queue, Node<T> root) {
        currentTraverseMode = mode;
        List<Node<T>> result = new ArrayList<>();
        List<Node<T>> newQueue = new ArrayList<>();

        if (this == root && !this.isChecked()) {        //проьлема с чекингом
            if (mode == TraverseMode.BFS) {
                queue.add(this);
            }
            this.setChecked(true);
            result.add(this);
        }

        if (mode == TraverseMode.BFS) {
            for (Node<T> node : queue) {
                for (Node<T> child : node.getChildren()) {
                    if (!child.isChecked()) {
                        child.setChecked(true);
                        result.add(child);
                        newQueue.add(child);
                    }
                }
            }
            if (!newQueue.isEmpty()) {
                result.addAll(makeTree(mode, newQueue, root));
            }

            if (this == root) {
                result.forEach(node -> node.setChecked(false));
            }

            return result;

        } else if (mode == TraverseMode.DFS) {
            for (Node<T> child : children) {
                if (!child.isChecked()) {
                    child.setChecked(true);
                    result.addAll(child.makeTree(mode, null, root));
                }
            }

            if (this.getParent() == null) {
                result.forEach(node -> node.setChecked(false));
            }

            return result;
        }

        if (this.getParent() == null) {
            result.forEach(node -> node.setChecked(false));
        }
        return result;
    }

    /** Return the node located by index. Indexes are determined by last searching algorithms used for this tree.
     * @param index position of node in the tree counting from this node.
     * @return required node.
     */
    public Node<T> find(int index) {
        return this.makeTree(currentTraverseMode).get(index);
    }

    /** Return the node located by index. Indexes are determined by search algorithm specified as searchMode used for this tree.
     * @param index position of node in the tree counting from this node.
     * @param searchMode traversing mode (BFS, DFS).
     * @return required node.
     */
    public Node<T> find(int index, TraverseMode searchMode) {
        List<Node<T>> tempList = this.makeTree(searchMode);
        return tempList.get(index);
    }

    /** Add new node to the tree. Different modes are used for adding node in different situations:
     * ADD_AS_CHILD - add node as the last child of one specified by index;
     * ADD_AS_NEIGHBOR - add node as a right neighbor of one specified by index;
     * INSERT_BEFORE - add node between one specified by index and its parent;
     * INSERT_AFTER - add node between one specified by index and its children;
     * @param content content, which is contained in this node.
     * @param index position of node in the tree counting from this node.
     * @param addMode modes (flags) for different adding styles.
     * @param traverseMode set new traversing mode (BFS, DFS).
     */
    public void add(T content, int index, AddMode addMode, TraverseMode traverseMode) {

        if (index > makeTree(currentTraverseMode).size()) {
            throw new IndexOutOfBoundsException("Wrong index value.\n");
        }

        if (traverseMode != currentTraverseMode) {
            setCurrentTraverseMode(traverseMode);
            setTree(makeTree(currentTraverseMode));
            tree.forEach(node -> node.setTree(node.makeTree(currentTraverseMode)));
        }

        if (addMode == AddMode.ADD_AS_CHILD) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Invalid index value in \"ADD_AS_CHILD\" mode.\n");
            }
            Node<T> parent = find(index, currentTraverseMode);
            new Node<>(content, parent);
            /*parent.getChildren().add(
                    parent.getChildren().size(),
                    new Node<>(content, parent));*/

        } else if (addMode == AddMode.ADD_AS_NEIGHBOR) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Invalid index value in \"ADD_AS_NEIGHBOR\" mode.\n");
            }

            boolean isRightNeighbourExists = true;

            Node<T> leftNeighbor = find(index);
            try {
                leftNeighbor
                        .getParent()
                        .getChildren()
                        .get(leftNeighbor
                                .getParent()
                                .getChildren()
                                .indexOf(leftNeighbor) + 1);
            } catch (IndexOutOfBoundsException outOfBoundsException) {
                isRightNeighbourExists = false;
            }

            parent = leftNeighbor.getParent();
            if (!isRightNeighbourExists) {
                parent.getChildren().add(
                        parent.getChildren().size(),
                        new Node<>(content, parent));
            } else {
                parent.getChildren().add(
                        index + 1,
                        new Node<>(content, parent));
            }

        } else if (addMode == AddMode.INSERT_BEFORE) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Invalid index value in \"INSERT_BEFORE\" mode.\n");
            }

            Node<T> currentNode = find(index);
            Node<T> insertion = new Node<>(content);

            Node<T> parent = currentNode.getParent();

            insertion.getChildren().add(currentNode);
            currentNode.setParent(insertion);

            insertion.setParent(parent);

        } else if (addMode == AddMode.INSERT_AFTER) {
            if (index < -1) {
                throw new IndexOutOfBoundsException("Invalid index value in \"INSERT_BEFORE\" mode.\n");
            }

            Node<T> chosenNode;
            try {
                chosenNode = find(index);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                chosenNode = null;
            }

            if (chosenNode == null) {
                setParent(new Node<>(content));
            } else {
                Node<T> insertion = new Node<>(content, chosenNode);
                insertion.setParent(chosenNode);
            }

        }

        setTree(makeTree(currentTraverseMode));
        tree.forEach(node -> node.setTree(node.makeTree(currentTraverseMode)));
    }

    /** Add new node to the tree. Different modes are used for adding node in different situations:
            * ADD_AS_CHILD - add node as the last child of one specified by index;
     * ADD_AS_NEIGHBOR - add node as a right neighbor of one specified by index;
     * INSERT_BEFORE - add node between one specified by index and its parent;
     * INSERT_AFTER - add node between one specified by index and its children;
     * @param content content, which is contained in this node.
     * @param index position of node in the tree counting from this node.
     * @param addMode modes (flags) for different adding styles.
     */
    public void add(T content, int index, AddMode addMode) {
        add(content, index, addMode, currentTraverseMode);
    }

    public void remove(int index, RemoveMode mode) {
        Node<T> chosenNode = find(index);
        Node<T> parent = chosenNode.getParent();

        if (mode == RemoveMode.CONCATENATE_SUBBRANCH) {
            chosenNode.getChildren().forEach(node -> node.setParent(parent));
            parent.getChildren().remove(chosenNode);

        } else if (mode == RemoveMode.DELETE_SUBBRANCH) {
            chosenNode.makeTree(currentTraverseMode).forEach(node -> node.setChildren(null));
            chosenNode.makeTree(currentTraverseMode).forEach(node -> node.setTree(null));
            chosenNode.makeTree(currentTraverseMode).forEach(node -> node.setContent(null));
            chosenNode.makeTree(currentTraverseMode).forEach(node -> node.setParent(null));

            chosenNode.setChildren(null);
            chosenNode.setTree(null);
            chosenNode.setContent(null);
            chosenNode.setParent(null);

            parent.getChildren().remove(chosenNode);
        }

        setTree(makeTree(currentTraverseMode));
        tree.forEach(node -> node.setTree(node.makeTree(currentTraverseMode)));
    }

    /** Set new content for the node specified by index.
     * @param index position of node in the tree counting from this node.
     * @param content content, which is contained in this node.
     */
    public void set(int index, T content) {
        find(index).setContent(content);
    }

    /** Get iterator for the (sub-)tree beginning at this node.
     * @return iterator.
     */
    @Override
    public Iterator<Node<T>> iterator() {
        return new ListIterator<>() {

            private int currentNode = 0;
            private Node<T> lastReferredTo;

            @Override
            public boolean hasNext() {
                return currentNode < tree.size();
            }

            @Override
            public Node<T> next() {
                if (hasNext()) {
                    return tree.get(currentNode++);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return currentNode > 0;
            }

            @Override
            public Node<T> previous() {
                if (hasPrevious()) {
                    return tree.get(--currentNode);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                return currentNode;
            }

            @Override
            public int previousIndex() {
                return Math.abs(currentNode - 1);
            }

            @Override
            public void remove() {
                tree.remove(previousIndex());
            }

            @Override
            public void set(Node<T> tNode) {
                tree.set(previousIndex(), tNode);
            }

            @Override
            public void add(Node<T> newNode) {
                tree.add(currentNode, newNode);
            }
        };
    }

    public void setCurrentTraverseMode(TraverseMode currentTraverseMode) {
        this.currentTraverseMode = currentTraverseMode;
    }

    public TraverseMode getCurrentTraverseMode() {
        return currentTraverseMode;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    /** Set new parent for this node. Removes this node from children and descendents of previous parent node and sets them for the new one.
     * @param parent new parent for this node.
     */
    public void setParent(Node<T> parent) {
        if (this.parent != null) {
            this.parent.getChildren().remove(this);
            this.parent.setTree(
                    this.parent.makeTree(
                            this.parent.getCurrentTraverseMode()));
        }
        if (parent != null) {
            this.parent = parent;
            this.parent.getChildren().add(this);
            this.parent.setTree(
                    makeTree(
                            parent.getCurrentTraverseMode()));
        } else {
            this.parent = null;
        }

    }

    public Node<T> getParent() {
        return parent;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setTree(List<Node<T>> tree) {
        this.tree = tree;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public List<Node<T>> getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return "ru.nsu.nikita.Node{" +
                "children=" + children +
                ", parent=" + parent +
                ", content=" + content +
                ", nodeIndex=" + nodeIndex +
                ", currentTraverseMode=" + currentTraverseMode +
                '}';
    }
}
