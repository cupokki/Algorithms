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
    static int[] result;
    static int min;
    static int M, N;
    public static int solution(int[][] info, int n, int m) {
//        result = new int[info.length];
//        min = Integer.MAX_VALUE;
//        N = n; M = m;
//        dfs(info, 0, 0, 0);
//        return min == Integer.MAX_VALUE ? -1 : min;

        int[][] dp = new int[info.length][2];

        for (int i = 0; i < info.length; i++) {
            // i까지 고려했을때 최소로 물건을 선택하는
        }

        return -1;
    }

//    static void dfs(int[][] info, int a, int b, int depth) {
//        if (depth == info.length) {
//            min = Math.min(min, a);
//            return;
//        }
//
//        if (a + info[depth][0] < N) dfs(info, a + info[depth][0], b, depth + 1);
//        if (b + info[depth][1] < M) dfs(info, a, b + info[depth][1], depth + 1);
//    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 2}, {2, 3}, {2, 1}}, 4, 4));
        System.out.println(solution(new int[][]{{1, 2}, {2, 3}, {2, 1}}, 1, 7));
        System.out.println(solution(new int[][]{{3, 3}, {3, 3}}, 7, 1));
        System.out.println(solution(new int[][]{{3, 3}, {3, 3}}, 6, 1));
    }
}