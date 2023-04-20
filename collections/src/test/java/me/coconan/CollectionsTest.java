package me.coconan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionsTest {
    @Test
    public void test_array_list_amortized_time_complexity() {
        int times = 1000;
        int n = 100000;
        long[] costsAverage = new long[n];
        for (int t = 0; t < times; t++) {
            ArrayList<Integer> ints = new ArrayList<>();
            long[] costs = new long[n];
            for (int i = 0; i < n; i++) {
                long start = System.nanoTime();
                ints.add(i);
                costs[i] = System.nanoTime() - start;
                costsAverage[i] += costs[i];
            }
        }
        for (int i = 0; i < n; i++) {
            costsAverage[i] = costsAverage[i] / times;
        }
        System.out.println(Arrays.toString(costsAverage));
    }
}
