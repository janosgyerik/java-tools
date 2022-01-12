package com.janosgyerik.tools.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class DequeTest {

  @Test
  void isEmpty_returns_true_for_empty_deque() {
    Deque<Integer> deque = newDeque(Collections.emptyList());
    assertThat(deque.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returns_false_for_non_empty_deque() {
    Deque<Integer> deque = newDeque(Arrays.asList(1, 2, 3));
    assertThat(deque.isEmpty()).isFalse();
  }

  @Test
  void create_from_singleton_list() {
    Deque<Integer> deque = newDeque(Collections.singletonList(3));
    assertThat(deque.getFirst()).isEqualTo(3);
    assertThat(deque.getLast()).isEqualTo(3);
  }

  @Test
  void create_from_list() {
    Deque<Integer> deque = newDeque(Arrays.asList(1, 2, 3));
    assertThat(deque.getFirst()).isEqualTo(1);
    assertThat(deque.getLast()).isEqualTo(3);
  }

  @Test
  void rotate_right_by_1() {
    Deque<Integer> deque = newDeque(Arrays.asList(1, 2, 3));
    assertThat(deque.getFirst()).isEqualTo(1);
    assertThat(deque.getLast()).isEqualTo(3);

    deque.rotateRight(1);
    assertThat(deque.getFirst()).isEqualTo(3);
    assertThat(deque.getLast()).isEqualTo(2);
  }

  @Test
  void rotate_left_by_1() {
    Deque<Integer> deque = newDeque(Arrays.asList(1, 2, 3));
    assertThat(deque.getFirst()).isEqualTo(1);
    assertThat(deque.getLast()).isEqualTo(3);

    deque.rotateLeft(1);
    assertThat(deque.getFirst()).isEqualTo(2);
    assertThat(deque.getLast()).isEqualTo(1);
  }

  static <T> Deque<T> newDeque(Collection<T> values) {
    return new LinkedListDeque<>(values);
  }
}
