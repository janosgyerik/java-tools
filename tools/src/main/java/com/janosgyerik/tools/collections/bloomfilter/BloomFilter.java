package com.janosgyerik.tools.collections.bloomfilter;

/**
 * https://en.wikipedia.org/wiki/Bloom_filter
 *
 * Medium uses Bloom filters to avoid recommending articles a user has previously read.[21]
 *
 * The servers of Akamai Technologies, a content delivery provider, use Bloom filters
 * to prevent "one-hit-wonders" from being stored in its disk caches.
 * One-hit-wonders are web objects requested by users just once, something that Akamai found
 * applied to nearly three-quarters of their caching infrastructure.
 * Using a Bloom filter to detect the second request for a web object and caching that object
 * only on its second request prevents one-hit wonders from entering the disk cache,
 * significantly reducing disk workload and increasing disk cache hit rates.[10]
 *
 * Google Bigtable, Apache HBase and Apache Cassandra and PostgreSQL[11] use Bloom filters
 * to reduce the disk lookups for non-existent rows or columns. Avoiding costly disk lookups
 * considerably increases the performance of a database query operation.[12]
 */
public interface BloomFilter<T> {

  /**
   * Add an item to the filter. Does not actually store objects,
   * only manipulates bits in an array to be able to check later
   * if the item was maybe added, or definitely not added.
   */
  void add(T item);

  /**
   * Returns false if the item was definitely added to the filter,
   * and returns true if the item was maybe added to the filter.
   * There can be false positives, but never false negatives.
   */
  boolean containsMaybe(T item);

}
