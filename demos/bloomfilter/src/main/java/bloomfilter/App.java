package bloomfilter;

import com.janosgyerik.utils.collections.bloomfilter.BloomFilters;

public class App {
  public static void main(String[] args) {
    // TODO
    // - design a class that works as a cache
    // - can compute its own size
    // - uses passed in bloom filter
    // - use 1 instance with well-configured bloom filter
    // - use 1 instance with dummy bloom filter
    // - verify the first one avoids excess storage of one-hit-wonders

    BloomFilters.create(100);
  }
}
