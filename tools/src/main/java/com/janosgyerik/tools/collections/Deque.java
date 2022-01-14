package com.janosgyerik.tools.collections;

import java.util.Collection;

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

  static <T> Deque<T> create(Collection<T> values) {

    class Node<U> {
      private final U value;
      private Node<U> next;
      private Node<U> prev;

      private Node(U value) {
        this.value = value;
      }
    }

    Deque<T> deque = new Deque<T>() {

      private Node<T> head;
      private Node<T> tail;

      @Override
      public boolean isEmpty() {
        return head == null;
      }

      @Override
      public void addLast(T value) {
        Node<T> node = new Node<>(value);

        if (isEmpty()) {
          head = tail = node;
          return;
        }

        if (head == tail) {
          tail = node;
          head.next = head.prev = tail;
          tail.prev = tail.next = head;
          return;
        }

        tail.next = head.prev = node;
        node.prev = tail;
        node.next = head;
        tail = node;
      }

      @Override
      public T getFirst() {
        if (this.isEmpty()) {
          throw new IllegalStateException("the deque is empty!");
        }
        return head.value;
      }

      @Override
      public T getLast() {
        if (this.isEmpty()) {
          throw new IllegalStateException("the deque is empty!");
        }
        return tail.value;
      }

      @Override
      public void rotateRight(int steps) {
        for (int i = 0; i < steps; i++) {
          head = head.prev;
        }
        tail = head.prev;
      }

      @Override
      public void rotateLeft(int steps) {
        for (int i = 0; i < steps; i++) {
          head = head.next;
        }
        tail = head.prev;
      }
    };

    for (T value : values) {
      deque.addLast(value);
    }

    return deque;
  }
}
