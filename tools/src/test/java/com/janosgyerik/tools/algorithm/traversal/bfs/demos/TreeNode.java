package com.janosgyerik.tools.algorithm.traversal.bfs.demos;

class TreeNode<T> {
  final T value;
  final TreeNode<T>[] children;

  TreeNode(T value, TreeNode<T>... children) {
    this.value = value;
    this.children = children;
  }

  static <T> TreeNode<T> create(T value, TreeNode<T>... children) {
    return new TreeNode<>(value, children);
  }
}
