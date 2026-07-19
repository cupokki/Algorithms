package programmers.n258705;

import java.util.*;

class Solution {
    /*
    한 변의 길이가 1인 정삼각형 2n + 1개를  이어붙여,
    윗변의 길이가 "n" 아랫변의 길이가 n + 1인 사다리꼴을 만들 수 있다.
    이때, 사다리꼴의 윗변과 변을 공유하는 정삼각형의 일부에 또 삼각형을 붙여 새로운 모양을 만든다.
    이 모양에서 정삼각형, 또는 마름모를 빈곳이 없도록 채운다. 모두 회전가능
    가능한 가짓수를 출력하라.

    n = 1, top = [0]) 3
    n = 1, top = [1]) 4

    n = 2, top = [0, 0]) 8?
    n = 2, top = [0, 1]) 11;

    도형을 쪼갤때, 마름모 하나거나, 삼각형 두개로 겹치는 부눈을 고려한다.

    */
    final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int answer = 0;

        int[][] dp = new int[n + 1][2];

        dp[0][0] = 1; // 오른쪽 마름모x
        dp[0][1] = 0; // 오른쪽 마름모o

        for (int i = 1; i <= n; i++) {
            if (tops[i - 1] == 1) { // 삼각형이 위에 있다.
                dp[i][0] = (dp[i - 1][0] * 3 + dp[i - 1][1] * 2) % MOD;
                dp[i][1] = (dp[i - 1][0] * 1 + dp[i - 1][1] * 1) % MOD;
            } else { // 없다.
                dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 1][1] * 1) % MOD;
                dp[i][1] = (dp[i - 1][0] * 1 + dp[i - 1][1] * 1) % MOD;
            }
        }

        answer = (dp[n][0] + dp[n][1]) % MOD;
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(4, new int[]{1, 1, 0, 1})); // 149
        System.out.println(sol.solution(2, new int[]{0, 1})); // 11
        System.out.println(sol.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0})); // 7704
    }
}