package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {

        List<E> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();
            result.add(current.key);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        this.dfs(this, result);
        return result;
    }

    private void dfs(Tree<E> eTree, List<E> result) {
        for (Tree<E> child : eTree.children) {
            this.dfs(child, result);
        }
        result.add(eTree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = find(parentKey);
        if (search == null) {
            throw new IllegalArgumentException();
        }
        search.children.add(child);
        child.parent = search;
    }

    private Tree<E> find(E parentKey) {

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();
            if (current.key == parentKey) {
                return current;
            }
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }
        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = find(nodeKey);
        if (toRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : toRemove.children) {
            child.parent = null;
        }

        toRemove.children.clear();
        Tree<E> parent = toRemove.parent;
        if (parent != null) {
            parent.children.remove(toRemove);
        }
        toRemove.key = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        System.out.println();
        Tree<E> firstNode = find(firstKey);
        Tree<E> secondNode = find(secondKey);
        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }
        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstParent == null) {
            swapRoot(secondNode);

            return;
        }
        if (secondParent == null) {
            swapRoot(firstNode);

            return;
        }

        secondNode.parent = firstParent;
        firstNode.parent = secondParent;


        int firstNodeIndex = this.children.indexOf(firstNode);
        int secondNodeIndex = this.children.indexOf(secondNode);

        firstParent.children.set(firstNodeIndex, secondNode);
        secondParent.children.set(secondNodeIndex, firstNode);


    }

    private void swapRoot(Tree<E> node) {
        this.key = node.key;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }
}



