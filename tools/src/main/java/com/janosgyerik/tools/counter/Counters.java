package com.janosgyerik.tools.counter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Counters {

  private Counters() {
    throw new AssertionError("utility class, forbidden constructor");
  }

  public static <T> Counter<T> create() {
    return new CounterImpl<>();
  }

  public static <T> Counter<T> create(Iterable<T> iterable) {
    Counter<T> counter = create();
    counter.addAll(iterable);
    return counter;
  }

  public static <T> Counter<T> create(T[] array) {
    return create(Arrays.asList(array));
  }

  public static Counter<Character> create(String text) {
    Counter<Character> counter = create();
    text.chars().mapToObj(c -> (char) c).forEach(counter::add);
    return counter;
  }

  private static class CounterImpl<T> implements Counter<T> {

    private final Map<T, Integer> counts;

    private CounterImpl() {
      this(new HashMap<>());
    }

    private CounterImpl(Map<T, Integer> map) {
      this.counts = map;
    }

    @Override
    public void add(T item) {
      counts.merge(item, 1, Integer::sum);
    }

    @Override
    public void addAll(Iterable<T> items) {
      items.forEach(this::add);
    }

    @Override
    public int get(T item) {
      return counts.getOrDefault(item, 0);
    }

    @Override
    public Map<T, Integer> counts() {
      return new HashMap<>(counts);
    }
  }
}
