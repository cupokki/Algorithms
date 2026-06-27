package programmers.n12942;

import java.util.Arrays;

class Solution {
    /*
    만약 ab, bc행렬이 있을때
    두행렬의 곱을 위해 a * b * c번 곱셈이 수행된다.

    행렬의 연산순서를 바꾸어 최소한의 곱셉 횟수를 출력한다.
    최대한 큰수가 줄어들어야한다.
    */
    public int solution(int[][] mat) {
        int answer = 0;
        int len = mat.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < len; i++) {
            dp[i][i] = 0; // 한 행렬만 존재 => 곱셉: 0
        }

        for (int dist = 1; dist < len; dist++) {
            for (int i = 0; i < len - dist; i++) {
                int j = i + dist;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k + 1][j] + (mat[i][0] * mat[k][1] * mat[j][1])
                    );
                }
            }
        }

        return dp[0][len - 1];
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(new int[][]{{5, 3}, {3, 10}, {10, 6}})); // 270
     }
}