package com.janosgyerik.tools.collections;

/**
 * A list-like sequence optimized for data accesses near its endpoints,
 * with an interface similar to Python's collections.deque
 */
public interface Deque<T> {

  boolean isEmpty();

  void addLast(T value);

  T getFirst();

  T getLast();

  /**
   * Rotate the deque steps to the right.
   * For example, rotateRight(1) on [1, 2, 3] will result in [3, 1, 2]
   */
  void rotateRight(int steps);

  /**
   * Rotate the deque steps to the left.
   * For example, rotateLeft(1) on [1, 2, 3] will result in [2, 3, 1]
   */
  void rotateLeft(int steps);
}
