package com.janosgyerik.tools.algorithm.traversal.bfs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Perform a breadth-first search from one or more start items.
 */
public interface Bfs<T> {

  void start(List<T> startItems);

  default void start(T startItem) {
    start(Collections.singletonList(startItem));
  }

  class BfsImpl<T> implements Bfs<T> {
    private final BfsVisitor<T> visitor;

    public BfsImpl(BfsVisitor<T> visitor) {
      this.visitor = visitor;
    }

    @Override
    public void start(List<T> startItems) {
      Queue<T> queue = new LinkedList<>(startItems);
      while (!queue.isEmpty()) {
        visitor.beforeLevel();
        IntStream.range(0, queue.size()).forEach(i -> {
          T current = queue.remove();
          queue.addAll(visitor.visit(current));
        });
        visitor.afterLevel();
        queue.addAll(visitor.extraItemsAfterLevel());
      }
    }
  }

  interface BfsVisitor<T> {
    /**
     * Called by Bfs implementation before visiting items on the current (non-empty) level.
     */
    default void beforeLevel() {
      // do nothing
    }

    /**
     * Called by Bfs implementation after visiting all items on the current level.
     */
    default void afterLevel() {
      // do nothing
    }

    /**
     * Called by Bfs implementation for each item on the current (non-empty) level.
     * <p>
     * Return List of items to add for next level.
     */
    default List<T> visit(T current) {
      return Collections.emptyList();
    }

    /**
     * Called by Bfs implementation after visitin all items on the current level.
     * <p>
     * Return List of extra items to add for next level.
     */
    default List<T> extraItemsAfterLevel() {
      return Collections.emptyList();
    }
  }
}
