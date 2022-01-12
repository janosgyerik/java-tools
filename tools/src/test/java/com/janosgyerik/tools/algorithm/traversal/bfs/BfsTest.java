package com.janosgyerik.tools.algorithm.traversal.bfs;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

class BfsTest {

  private final Bfs.BfsVisitor<Object> visitor = mock(Bfs.BfsVisitor.class);
  private final Bfs<Object> underTest = new Bfs.BfsImpl<>(visitor);

  @Test
  void call_beforeLevel_then_visit_then_afterLevel() {
    Object start = new Object();
    underTest.start(start);

    verify(visitor).beforeLevel();
    verify(visitor).visit(start);
    verify(visitor).afterLevel();
    verify(visitor).extraItemsAfterLevel();
    verifyNoMoreInteractions(visitor);
  }

  @Test
  void no_calls_to_visitor_when_no_items() {
    underTest.start(Collections.emptyList());
    verifyZeroInteractions(visitor);
  }

  @Test
  void visit_items_returned_by_visitor_on_next_level() {
    Object level1 = new Object();
    Object level2_1 = new Object();
    Object level2_2 = new Object();

    when(visitor.visit(level1)).thenReturn(Arrays.asList(level2_1, level2_2));

    underTest.start(level1);
    verify(visitor).visit(level1);
    verify(visitor).visit(level2_1);
    verify(visitor).visit(level2_2);
  }
}
