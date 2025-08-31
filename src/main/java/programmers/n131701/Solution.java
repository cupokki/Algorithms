package programmers.n131701;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;

        for (int idx = 0; idx < n; idx++) {
            for(int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j <= i; j++) {
                    sum += elements[(idx + j) % n];
                }
                set.add(sum);
            }
        }

        return set.size();
    }



    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 9, 1, 1, 4}));
    }
}
