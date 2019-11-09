package com.janosgyerik.tools.algorithm.traversal;

import com.janosgyerik.tools.algorithm.traversal.bfs.Bfs;

/**
 * Utility class to find traversals and implementations easily.
 */
public class Traversals {
  private Traversals() {
    // utility class, forbidden constructor
  }

  /**
   * Returns a BFS traversal implementation using visitor.
   */
  public static<T> Bfs<T> bfs(Bfs.BfsVisitor<T> visitor) {
    return new Bfs.BfsImpl<>(visitor);
  }
}
