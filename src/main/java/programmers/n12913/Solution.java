package programmers.n12913;

import java.util.Arrays;

public class Solution {
    // n행 4열의 땅
    // 1행부터 한행씩 내려올때마다 4열중 하나를 밟는다.
    // 같은 열을 밟을 순 없다.
    // 점수 최대값을 구하라.
    // dfs로 해결불능 n은 최대 100,000
    // DP 문제인가?
//    static int solution(int[][] land) {
//        int score = 0;
//
//        int[][] dp = new int[land.length + 1][4];
//
//        for (int i = 1; i <= land.length ; i++) {
//            for (int j = 0; j < 4; j++) {
//                for(int k = 1; k < 4; k++) {
//                    dp[i][j] = Math.max(dp[i][j], land[i - 1][j] + dp[i - 1][(j + k) % 4]);
//                }
//            }
//        }
//        return Arrays.stream(dp[land.length]).max().getAsInt();
//    }

    static int solution(int[][] land) {
        memo = new int[land.length + 1][4];
        Arrays.stream(memo).forEach(row -> Arrays.fill(row, -1));
        for (int col = 0; col < 4; col++) {
            memo[0][col] = land[0][col];
        }
        int answer = 0;
        for (int col = 0; col < 4; col++) {
            answer = Math.max(dfs(land, land.length - 1, col), answer);
        }

        return answer;
    }
    static int[][] memo;
    static int dfs(int[][] land, int depth, int col) {
        if (depth == 0) return memo[0][col];
        // 명시적으로, 사실 초기화되어있서 위가 없어도 아래 조건문에 걸림

        if (memo[depth][col] != -1) {
            return memo[depth][col];
        }

        int max = 0;
        for (int k = 0; k < 4; k++) {
            if (k != col) {
                max = Math.max(max, dfs(land, depth - 1, k));
            }
        }
        return memo[depth][col] = land[depth][col] + max;
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
