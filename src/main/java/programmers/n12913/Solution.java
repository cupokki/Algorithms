package programmers.n12913;

import java.util.Arrays;

public class Solution {
    // n행 4열의 땅
    // 1행부터 한행씩 내려올때마다 4열중 하나를 밟는다.
    // 같은 열을 밟을 순 없다.
    // 점수 최대값을 구하라.
    // dfs로 해결불능 n은 최대 100,000
    // DP 문제인가?
    static int solution(int[][] land) {
        int score = 0;

        int[][] dp = new int[land.length + 1][4];

        for (int i = 1; i <= land.length ; i++) {
            for (int j = 0; j < 4; j++) {
                for(int k = 1; k < 4; k++) {
                    dp[i][j] = Math.max(dp[i][j], land[i - 1][j] + dp[i - 1][(j + k) % 4]);
                }
            }
        }
        return Arrays.stream(dp[land.length]).max().getAsInt();
    }

    public static void main(String[] args) {
        int[][] land = {
                {1,2,3,5},
                {5,6,7,8},
                {4,3,2,1}
        };
        System.out.println(solution(land));
    }
}
