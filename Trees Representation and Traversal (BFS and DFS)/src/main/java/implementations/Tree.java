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

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }
}



