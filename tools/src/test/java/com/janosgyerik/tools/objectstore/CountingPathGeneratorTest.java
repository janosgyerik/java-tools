package com.janosgyerik.tools.objectstore;

import com.janosgyerik.tools.objectstore.impl.CountingPathGenerator;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CountingPathGeneratorTest {
  @Test
  public void first_3_of_one_level_10_should_be_1_2_3() {
    CountingPathGenerator gen = new CountingPathGenerator(1, 10, Paths.get("."));
    assertThat(gen.next()).isEqualTo(Paths.get("./0"));
    assertThat(gen.next()).isEqualTo(Paths.get("./1"));
    assertThat(gen.next()).isEqualTo(Paths.get("./2"));
  }

  @Test
  public void first_3_of_two_level_10_should_be_0x1_0x2_0x3() {
    CountingPathGenerator gen = new CountingPathGenerator(2, 10, Paths.get("."));
    assertThat(gen.next()).isEqualTo(Paths.get("./0/0"));
    assertThat(gen.next()).isEqualTo(Paths.get("./0/1"));
    assertThat(gen.next()).isEqualTo(Paths.get("./0/2"));
  }

  @Test
  public void first_3_of_two_level_2_should_be_0x1_0x2_1x1() {
    CountingPathGenerator gen = new CountingPathGenerator(2, 2, Paths.get("."));
    assertThat(gen.next()).isEqualTo(Paths.get("./0/0"));
    assertThat(gen.next()).isEqualTo(Paths.get("./0/1"));
    assertThat(gen.next()).isEqualTo(Paths.get("./1/0"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_if_levels_below_1() {
    new CountingPathGenerator(0, 10, Paths.get("."));
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_if_filesPerLevel_below_1() {
    new CountingPathGenerator(1, 0, Paths.get("."));
  }

  @Test
  public void more_than_9_should_exhaust_3_level_3() {
    int levels = 3;
    int filesPerLevel = 3;

    CountingPathGenerator gen = new CountingPathGenerator(levels, filesPerLevel, Paths.get("."));

    for (int i = 0; i < Math.pow(filesPerLevel, levels); i++) {
      assertThat(gen.hasNext()).isTrue();
      gen.next();
    }

    assertThat(gen.hasNext()).isFalse();
    assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(gen::next);
  }
}
