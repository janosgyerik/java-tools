package com.janosgyerik.tools.counter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountersTest {

  @Test
  void item_never_added_has_count_0() {
    assertThat(Counters.create().get("nonexistent")).isEqualTo(0);
  }

  @Test
  void item_added_once_has_count_1() {
    String item = "value";
    Counter<String> counter = Counters.create();
    counter.add(item);
    assertThat(counter.get(item)).isEqualTo(1);
  }

  @Test
  void item_added_twice_has_count_2() {
    String item = "value";
    Counter<String> counter = Counters.create();
    counter.add(item);
    counter.add(item);
    assertThat(counter.get(item)).isEqualTo(2);
  }

  @Test
  void count_items_added_from_iterable() {
    Counter<String> counter = Counters.create();
    String item1 = "hello";
    String item2 = "there";
    counter.addAll(Arrays.asList(item1, item1, item1, item2));
    assertThat(counter.get(item1)).isEqualTo(3);
    assertThat(counter.get(item2)).isEqualTo(1);
  }

  @Test
  void map_of_counts_is_a_defensive_copy() {
    Counter<String> counter = Counters.create();
    String item = "value";
    counter.add(item);
    int count = 7;
    counter.counts().put(item, count);
    assertThat(counter.get(item)).isEqualTo(1);
  }

  @Test
  void create_counter_from_iterable() {
    Counter<String> counter = Counters.create(Arrays.asList("foo", "bar", "foo"));
    Map<String, Integer> map = new HashMap<>();
    map.put("foo", 2);
    map.put("bar", 1);
    assertThat(counter.counts()).isEqualTo(map);
    assertThat(counter.get("baz")).isEqualTo(0);
  }

  @Test
  void create_counter_from_array_of_objects() {
    Counter<String> counter = Counters.create(new String[] {"foo", "bar", "foo"});
    Map<String, Integer> map = new HashMap<>();
    map.put("foo", 2);
    map.put("bar", 1);
    assertThat(counter.counts()).isEqualTo(map);
    assertThat(counter.get("baz")).isEqualTo(0);
  }

  @Test
  void create_counter_from_string() {
    Counter<Character> counter = Counters.create("foo");
    Map<Character, Integer> map = new HashMap<>();
    map.put('o', 2);
    map.put('f', 1);
    assertThat(counter.counts()).isEqualTo(map);
    assertThat(counter.get('z')).isEqualTo(0);
  }
}
