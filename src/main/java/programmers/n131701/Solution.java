package programmers.n131701;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        int[][] prefixSum = new int[n + 1][n + 1];

        // 몇부터 몇까지 합이아니라
        // n부터 시작했을때 n + m 까지의 누적합

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i][(i + j - 1) % n] + elements[(i + j) % n]; // 여기 인덱스가 잘못된듯


            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.add(prefixSum[i][j]);
            }
        }

        return set.size();
    }



    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 9, 1, 1, 4}));
    }
}
