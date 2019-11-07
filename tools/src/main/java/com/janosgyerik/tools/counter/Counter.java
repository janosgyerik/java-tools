package com.janosgyerik.tools.counter;

import java.util.Map;

/**
 * Build a map of counts of objects.
 * 
 * See the static helper methods in the Counters utility class to get implementations.
 * 
 * @see Counters
 *
 * @param <T> type of the counted objects
 */
public interface Counter<T> {

  /**
   * Add an item, incrementing its count.
   */
  void add(T item);

  /**
   * Add many items, incrementing their count.
   */
  void addAll(Iterable<T> items);

  /**
   * Returns the count of the specified item, or 0 if it was never added.
   */
  int get(T item);

  /**
   * Return the counts as a map.
   */
  Map<T, Integer> counts();
}
