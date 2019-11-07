package com.janosgyerik.tools.collections.bloomfilter;

import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BloomFilterImplTest {

  private static final Random RANDOM = new Random();

  private static ToIntFunction<String> hashOfCharAtOrZero(int charAtIndex) {
    return s -> {
      if (s.length() <= charAtIndex) {
        return 0;
      }
      return Integer.hashCode(s.charAt(charAtIndex));
    };
  }

  @Test
  void containsMaybe_finds_all_strings_starting_with_a_when_indexed_by_first_letter() {
    BloomFilter<String> underTest = BloomFilters.create(1000, hashOfCharAtOrZero(0));
    underTest.add("a" + randomString(10));

    IntStream.range(0, 5).forEach(ignored -> {
      assertThat(underTest.containsMaybe('a' + randomString(10))).isTrue();

      char notA = (char) ('b' + RANDOM.nextInt(25));
      assertThat(underTest.containsMaybe(notA + randomString(10))).isFalse();
    });
  }

  @Test
  void containsMaybe_finds_all_strings_starting_with_ab_when_indexed_by_first_two_letters() {
    BloomFilter<String> underTest = BloomFilters.create(1000, hashOfCharAtOrZero(0), hashOfCharAtOrZero(1));
    underTest.add("ab" + randomString(10));

    IntStream.range(0, 5).forEach(ignored -> {
      assertThat(underTest.containsMaybe("ab" + randomString(10))).isTrue();

      char notA = (char) ('b' + RANDOM.nextInt(25));
      assertThat(underTest.containsMaybe(notA + randomString(10))).isFalse();

      char notB = (char) ('c' + RANDOM.nextInt(24));
      assertThat(underTest.containsMaybe("a" + notB + randomString(10))).isFalse();
    });
  }

  private static String randomString(int length) {
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, length).forEach(ignored -> sb.append((char) ('a' + RANDOM.nextInt(26))));
    return sb.toString();
  }
}
