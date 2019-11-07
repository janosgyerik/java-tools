package com.janosgyerik.tools.objectstore.impl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingPathGenerator implements Iterator<Path> {

  private final AtomicInteger counter = new AtomicInteger(0);

  private final int levels;
  private final int filesPerLevel;
  private final Path base;

  private final int limit;

  public CountingPathGenerator(int levels, int filesPerLevel, Path base) {
    if (levels < 1) {
      throw new IllegalArgumentException("levels must be > 0");
    }
    if (filesPerLevel < 1) {
      throw new IllegalArgumentException("filesPerLevel must be > 0");
    }

    this.levels = levels;
    this.filesPerLevel = filesPerLevel;
    this.base = base;

    limit = (int) Math.pow(filesPerLevel, levels);
  }

  @Override
  public boolean hasNext() {
    return counter.get() < limit;
  }

  @Override
  public Path next() {
    if (!hasNext()) {
      throw new NoSuchElementException("path generator exhausted");
    }

    int count = counter.getAndIncrement();

    List<String> parts = new ArrayList<>(levels);
    for (int i = 0; i < levels; i++) {
      parts.add(Integer.toString(count % filesPerLevel));
      count /= filesPerLevel;
    }

    Collections.reverse(parts);

    Path path = base;
    for (String part : parts) {
      path = path.resolve(part);
    }

    return path;
  }
}
