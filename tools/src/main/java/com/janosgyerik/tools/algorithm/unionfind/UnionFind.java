package com.janosgyerik.tools.algorithm.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
  private final Map<Integer, Integer> parents = new HashMap<>();

  public void union(int u, int v) {
    int pu = find(u);
    int pv = find(v);
    if (pu == pv) return;
    parents.put(pu, pv);
  }

  public int find(int u) {
    if (!parents.containsKey(u)) {
      parents.put(u, u);
      return u;
    }

    int pu = parents.get(u);
    while (u != pu) {
      int ppu = parents.get(pu);
      parents.put(u, ppu);
      u = pu;
      pu = ppu;
    }

    return pu;
  }
}
