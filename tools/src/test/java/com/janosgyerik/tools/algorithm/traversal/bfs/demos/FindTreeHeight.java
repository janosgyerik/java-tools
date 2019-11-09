package com.janosgyerik.tools.algorithm.traversal.bfs.demos;

import com.janosgyerik.tools.algorithm.traversal.bfs.Bfs;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FindTreeHeight {
  int findTreeHeight(@Nullable TreeNode<?> root) {
    if (root == null) return 0;
    
    int[] level = {0};
    Bfs.BfsVisitor<TreeNode<?>> visitor = new Bfs.BfsVisitor<TreeNode<?>>() {
      @Override
      public void beforeLevel() {
        level[0]++;
      }

      @Override
      public List<TreeNode<?>> visit(TreeNode<?> current) {
        return Arrays.asList(current.children);
      }
    };

    new Bfs.BfsImpl<>(visitor).start(root);
    return level[0];
  }

  @Test
  void height_of_empty_tree_is_zero() {
    assertThat(findTreeHeight(null)).isZero();
  }

  @Test
  void height_of_singleton_tree_is_1() {
    assertThat(findTreeHeight(TreeNode.create(7))).isEqualTo(1);
  }

  @ParameterizedTest
  @ValueSource(ints = {2, 3, 5, 15})
  void height_of_k_level_tree_is_k(int k) {
    TreeNode<Object> root = TreeNode.create(new Object());
    for (int i = 0; i < k - 1; i++) {
      root = TreeNode.create(new Object(), root, TreeNode.create(new Object()));
    }

    assertThat(findTreeHeight(root)).isEqualTo(k);
  }
}
