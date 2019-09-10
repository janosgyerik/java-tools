package com.janosgyerik.utils.collections.bloomfilter;

import java.util.BitSet;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

class BloomFilterImpl<T> implements BloomFilter<T> {
  private final BitSet bits;
  private final ToIntFunction<T>[] hashers;

  BloomFilterImpl(int m, ToIntFunction<T>[] hashers) {
    this.bits = new BitSet(m);
    this.hashers = hashers;
  }

  @Override
  public void add(T item) {
    Stream.of(hashers).forEach(h -> bits.set(h.applyAsInt(item)));
  }

  @Override
  public boolean containsMaybe(T item) {
    return Stream.of(hashers).allMatch(h -> bits.get(h.applyAsInt(item)));
  }
}
