package com.janosgyerik.utils.counter;

import com.janosgyerik.utils.counter.api.Counter;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest {

    @Test
    public void item_never_added_should_have_count_0() {
        assertThat(Counters.counter().get("nonexistent")).isEqualTo(0);
    }

    @Test
    public void item_added_once_should_have_count_1() {
        String item = "value";
        Counter<String> counter = Counters.counter();
        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(1);
    }

    @Test
    public void item_added_twice_should_have_count_2() {
        String item = "value";
        Counter<String> counter = Counters.counter();
        counter.add(item);
        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(2);
    }

    @Test
    public void test_adding_many_at_once() {
        Counter<String> counter = Counters.counter();
        String item1 = "hello";
        String item2 = "there";
        counter.addAll(Arrays.asList(item1, item1, item1, item2));
        assertThat(counter.get(item1)).isEqualTo(3);
        assertThat(counter.get(item2)).isEqualTo(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_expose_internal_map_of_counts() {
        Counter<String> counter = Counters.counter();
        counter.counts().put("some key", 7);
    }
}
