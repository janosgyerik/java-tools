package com.janosgyerik.utils.collections.bloomfilter;

import java.util.function.ToIntFunction;

public class BloomFilters {
  private BloomFilters() {
    // utility class, forbidden constructor
  }

  /**
   * Create a BloomFilter with a bit array having size m,
   * and using the specified hashers to hash objects.
   */
  public static <T> BloomFilter<T> create(int m, ToIntFunction<T>... hashers) {
    if (m < 0) {
      throw new IllegalArgumentException(String.format("Invalid bit array size: %s; must be greater than 0", m));
    }
    if (hashers.length == 0) {
      throw new IllegalArgumentException("No hashers. At least one hasher is required");
    }
    return new BloomFilterImpl<>(m, hashers);
  }
}
