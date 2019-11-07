package com.janosgyerik.utils.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class MapUtils {
  private MapUtils() {
    // utility class, forbidden constructor
  }

  public static <K, V extends Comparable<V>> Optional<Map.Entry<K, V>> max(Map<K, V> map) {
    return map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
  }
}
