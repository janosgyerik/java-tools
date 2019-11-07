package com.janosgyerik.utils.collections;

import com.janosgyerik.utils.counter.Counters;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapUtilsTest {
  @Test
  public void should_find_max_entry() {
    Collection<String> words = Arrays.asList("hello", "world", "hello", "again");
    Map<String, Integer> counts = Counters.<String>create().addAll(words).counts();
    Map.Entry<String, Integer> max = MapUtils.max(counts).get();
    assertThat(max.getKey()).isEqualTo("hello");
    assertThat(max.getValue()).isEqualTo(2);
  }

  @Test
  public void test_() {
    System.out.println(~5 & 1);
  }
}
