package com.janosgyerik.tools.algorithms.tree.binarytree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;

import static com.janosgyerik.tools.algorithms.tree.binarytree.Iterators.inOrderIterator;
import static com.janosgyerik.tools.algorithms.tree.binarytree.Iterators.levelOrderIterator;
import static com.janosgyerik.tools.algorithms.tree.binarytree.Iterators.postOrderIterator;
import static com.janosgyerik.tools.algorithms.tree.binarytree.Iterators.preOrderIterator;
import static org.junit.Assert.assertEquals;

public class IteratorsTest {

  // example tree from http://en.wikipedia.org/wiki/Tree_traversal
  /*
   * F
   * B G
   * A D I
   * C E H
   */
  private final Node<Character> root;

  public IteratorsTest() {
    root = new Node<>('F');
    root.left = new Node<>('B');
    root.left.left = new Node<>('A');
    root.left.right = new Node<>('D');
    root.left.right.left = new Node<>('C');
    root.left.right.right = new Node<>('E');
    root.right = new Node<>('G');
    root.right.right = new Node<>('I');
    root.right.right.left = new Node<>('H');
  }

  static <T> List<T> iterateToList(Iterator<T> iterator) {
    List<T> list = new LinkedList<>();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    return list;
  }

  @Test
  public void test_preOrderIterator() {
    Iterator<Character> iterator = preOrderIterator(root);
    assertEquals(Arrays.asList('F', 'B', 'A', 'D', 'C', 'E', 'G', 'I', 'H'), iterateToList(iterator));
  }

  @Test(expected = NoSuchElementException.class)
  public void preOrderIterator_should_throw_if_iterated_beyond() {
    Iterator<Character> iterator = preOrderIterator(new Node<>('A'));
    iterator.next();
    iterator.next();
  }

  @Test
  public void test_inOrderIterator() {
    Iterator<Character> iterator = inOrderIterator(root);
    assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'), iterateToList(iterator));
  }

  @Test(expected = NoSuchElementException.class)
  public void inOrderIterator_should_throw_if_iterated_beyond() {
    Iterator<Character> iterator = inOrderIterator(new Node<>('A'));
    iterator.next();
    iterator.next();
  }

  @Test
  public void test_postOrderIterator() {
    Iterator<Character> iterator = postOrderIterator(root);
    assertEquals(Arrays.asList('A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F'), iterateToList(iterator));
  }

  @Test(expected = NoSuchElementException.class)
  public void postOrderIterator_should_throw_if_iterated_beyond() {
    Iterator<Character> iterator = postOrderIterator(new Node<>('A'));
    iterator.next();
    iterator.next();
  }

  @Test
  public void test_levelOrderIterator() {
    Iterator<Character> iterator = levelOrderIterator(root);
    assertEquals(Arrays.asList('F', 'B', 'G', 'A', 'D', 'I', 'C', 'E', 'H'), iterateToList(iterator));
  }

  @Test(expected = NoSuchElementException.class)
  public void levelOrderIterator_should_throw_if_iterated_beyond() {
    Iterator<Character> iterator = levelOrderIterator(new Node<>('A'));
    iterator.next();
    iterator.next();
  }
}
