package com.janosgyerik.tools.collections;

import java.util.Collection;

public class LinkedListDeque<T> implements Deque<T> {

  private Node<T> head;
  private Node<T> tail;

  public LinkedListDeque(Collection<T> values) {
    for (T value : values) {
      this.addLast(value);
    }
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public void addLast(T value) {
    Node<T> node = newNode(value);
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

  private static <T> Node<T> newNode(T value) {
    return new Node<>(value);
  }

  private static class Node<T> {

    private final T value;
    private Node<T> next;
    private Node<T> prev;

    private Node(T value) {
      this.value = value;
    }
  }
}
