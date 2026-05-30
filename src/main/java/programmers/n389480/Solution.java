package programmers.n389480;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /*
    a, b 두명이 팀을 이루어 물건을 훔친다.
    물건 i를 훔칠때, info[i]는 [a 흔적 수, b 흔적 수]이다. (각 도둑은 1개이상 3개이하 흔적을 남긴다.)
    각 n, m개 이상시 잡힌다.

    도둑들이 모두 경찰에 잡히지 않도록 모든 물건을 훔칠때, a도둑의 흔적의 최솟값을 출력한다.
    그러지 못한다면 -1

    dfs는 불가?
    */
    static int[][] memo;
    static int min;
    static int M, N;
    public static int solution(int[][] info, int n, int m) {
        memo = new int[info.length][121];
        for (int i = 0; i < info.length; i++)
            Arrays.fill(memo[i], Integer.MAX_VALUE);

        N = n; M = m;
        dfs(info, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    /**
     * @return a 최솟값
     */
    static int dfs(int[][] info, int b, int depth) {
        if (b > M) return Integer.MAX_VALUE;

        if (depth == info.length) {
            return memo[depth][b];
        }

        if (memo[depth][b] != Integer.MAX_VALUE) {
            return memo[depth][b];
        }

        memo[depth][b] = dfs(info, b, depth + 1) + info[depth][0];
        memo[depth][b + info[depth][1]] = dfs(info, b + info[depth][1], depth + 1);

        return Math.min(
                memo[depth][b],
                memo[depth][b + info[depth][1]]
                );

    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 2}, {2, 3}, {2, 1}}, 4, 4));
        System.out.println(solution(new int[][]{{1, 2}, {2, 3}, {2, 1}}, 1, 7));
        System.out.println(solution(new int[][]{{3, 3}, {3, 3}}, 7, 1));
        System.out.println(solution(new int[][]{{3, 3}, {3, 3}}, 6, 1));
    }
}