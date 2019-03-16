package com.janosgyerik.utils.files.finder;

import java.util.ArrayList;
import java.util.List;

public class RegexMatchingFileFinderTest extends FileFinderTest {

  @Override
  FileFinder getFileFinder() {
    return FileFinders.regexMatching("fund_\\d+.*.csv");
  }

  @Override
  protected List<String> getMatchingNames(int num) {
    List<String> names = new ArrayList<>(num);
    for (int i = 0; i < num; ++i) {
      names.add("fund_" + i + ".csv");
    }
    return names;
  }

  @Override
  protected List<String> getNonMatchingNames(int num) {
    List<String> names = new ArrayList<>(num);
    for (int i = 0; i < num; ++i) {
      names.add("hello" + i + ".csv");
    }
    return names;
  }
}
