package com.janosgyerik.tools.util;

import org.junit.Test;

import java.util.*;

import static com.janosgyerik.tools.util.IterTools.permutationIterator;
import static com.janosgyerik.tools.util.IterTools.toList;
import static org.junit.Assert.assertEquals;

public class IterToolsTest {

    private Set<List<Integer>> permutations(List<Integer> integers) {
        return IterTools.permutations(integers);
    }

    @Test
    public void test_permutate_empty() {
        assertEquals(Collections.<List<Integer>>emptySet(), permutations(Collections.emptyList()));
    }

    @Test
    public void test_permutate_1() {
        assertEquals(Collections.singleton(Collections.singletonList(1)), permutations(Collections.singletonList(1)));
    }

    private Set<List<Integer>> makeSet(List<List<Integer>> lists) {
        Set<List<Integer>> result = new HashSet<>();
        result.addAll(lists);
        return result;
    }

    @Test
    public void test_permutate_1_2() {
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 1))),
                permutations(Arrays.asList(1, 2)));
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(2, 1), Arrays.asList(1, 2))),
                permutations(Arrays.asList(1, 2)));
    }

    @Test
    public void test_permutate_1_2_3() {
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                        Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1))),
                permutations(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void should_get_equal_list_from_iterator() {
        List<Integer> list = Arrays.asList(3, 1, 4, 5, 2);
        assertEquals(list, toList(list.iterator()));
    }

    @Test
    public void should_get_empty_list_from_empty_iterator() {
        List<Integer> list = Collections.emptyList();
        assertEquals(list, toList(list.iterator()));
    }

    @Test
    public void should_get_1_empty_permutation_for_empty_list() {
        List<List<Integer>> permutations = toList(IterTools.permutationIterator(Collections.emptyList()));
        assertEquals(Collections.singletonList(Collections.emptyList()), permutations);
    }

    @Test
    public void should_get_1_permutation_for_singleton_list() {
        List<List<Integer>> permutations = toList(IterTools.permutationIterator(Collections.singletonList(7)));
        assertEquals(Collections.singletonList(Collections.singletonList(7)), permutations);
    }

    @Test
    public void should_get_2_ordered_permutations_for_x_y() {
        List<List<Character>> permutations = toList(IterTools.permutationIterator(Arrays.asList('x', 'y')));
        assertEquals(Arrays.asList(
                Arrays.asList('x', 'y'),
                Arrays.asList('y', 'x')
        ), permutations);
    }

    @Test
    public void should_get_6_ordered_permutations_for_1_2_3() {
        List<List<Integer>> permutations = toList(IterTools.permutationIterator(Arrays.asList(1, 2, 3)));
        assertEquals(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(3, 2, 1)
        ), permutations);
    }

    @Test
    public void should_get_equal_permutations_from_recursion_and_iterator() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(makeSet(toList(permutationIterator(list))), permutations(list));
    }
}
