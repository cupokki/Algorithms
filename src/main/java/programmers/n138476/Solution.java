package programmers.n138476;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    static int solution(int k, int[] tangerine) {
        Arrays.sort(tangerine);
        int max = tangerine[tangerine.length + 1];
        int[] size = new int[max + 1];
        for (int t : tangerine) {
            size[t]++;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= max; i++) {
            if (size[i] != 0) {
                map.put(i, size[i]);
            }
        }
        AtomicInteger cnt = new AtomicInteger(0);
        return map.keySet().stream().sorted(Collections.reverseOrder())
                .filter(i -> cnt.incrementAndGet() < k)
                .toList()
                .size();

    }
    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3}));
    }
}
