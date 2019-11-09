package com.janosgyerik.tools.algorithm.traversal.bfs.demos;

import com.janosgyerik.tools.algorithm.traversal.bfs.Bfs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {
  int treeDiameter(int[][] edges) {
    if (edges.length == 0 || edges[0].length == 0)
      return 0;

    Graph g = new Graph();
    Stream.of(edges).forEach(edge -> g.addEdge(edge[0], edge[1]));

    // a much more simple solution: trim the leaves until 1 or 2 nodes are left
    //
    // int levels = 0;
    // while (g.vertexCount() > 1) {
    //   trim(g);
    //   levels++;
    // }
    //
    // return levels * 2 - 1 + g.vertexCount();

    int[] level = {0};
    Bfs.BfsVisitor<Integer> visitor = new Bfs.BfsVisitor<Integer>() {
      Set<Integer> nextLevel = new HashSet<>();
      Set<Integer> removed = new HashSet<>();

      @Override
      public void beforeLevel() {
        level[0]++;
        nextLevel.clear();
        removed.clear();
      }

      @Override
      public List<Integer> visit(Integer current) {
        if (g.neighborsCount(current) == 0) {
          g.remove(current);
          return Collections.emptyList();
        }
        int neighbor = g.neighbors(current).iterator().next();
        g.remove(current);
        removed.add(current);
        if (g.neighborsCount(neighbor) == 1) {
          nextLevel.add(neighbor);
        }
        ;
        return Collections.emptyList();
      }

      @Override
      public List<Integer> extraItemsAfterLevel() {
        if (g.vertexCount() == 1)
          return Collections.emptyList();
        nextLevel.removeAll(removed);
        return new ArrayList<>(nextLevel);
      }
    };

    List<Integer> leafs = g.neighborsMap.entrySet().stream()
      .filter(e -> e.getValue().size() == 1)
      .map(Map.Entry::getKey)
      .collect(Collectors.toList());

    new Bfs.BfsImpl<>(visitor).start(leafs);
    return level[0] * 2 - 1 + g.vertexCount();
  }

  private void trim(Graph g) {
    List<Integer> toRemove = g.neighborsMap.keySet().stream()
      .filter(v -> g.neighborsCount(v) == 1)
      .collect(Collectors.toList());
    toRemove.forEach(g::remove);
  }

  private static class Graph {
    private final Map<Integer, Set<Integer>> neighborsMap = new HashMap<>();

    Set<Integer> neighbors(int v) {
      return neighborsMap.get(v);
    }

    int neighborsCount(int v) {
      return neighbors(v).size();
    }

    int vertexCount() {
      return neighborsMap.size();
    }

    void addEdge(int from, int to) {
      addDirectedEdge(from, to);
      addDirectedEdge(to, from);
    }

    void addDirectedEdge(int from, int to) {
      neighborsMap.computeIfAbsent(from, ignored -> new HashSet<>()).add(to);
    }

    void remove(int v) {
      neighborsMap.get(v).forEach(u -> neighborsMap.get(u).remove(v));
      neighborsMap.remove(v);
    }
  }
}

class FindTreeDiameter {

  private int findTreeDiameter(int[][] edges) {
    return new Solution().treeDiameter(edges);
  }

  @Test
  void diameter_of_empty_tree_is_zero() {
    assertThat(findTreeDiameter(new int[0][])).isZero();
  }

  @Test
  void diameter_of_pair_tree_is_1() {
    assertThat(findTreeDiameter(new int[][] {{0, 1}})).isEqualTo(1);
  }

  @Test
  void diameter_of_triplet_tree_is_2() {
    assertThat(findTreeDiameter(new int[][] {{0, 1}, {0, 2}})).isEqualTo(2);
  }

  @Test
  void diameter_of_example_2_tree_is_4() {
    assertThat(findTreeDiameter(new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}})).isEqualTo(4);
  }

  @Test
  void diameter_of_example_x_tree_is_5() {
    int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {0, 5}, {1, 6}, {2, 7}, {6, 8}, {8, 9}};
    assertThat(findTreeDiameter(edges)).isEqualTo(5);
  }
}
