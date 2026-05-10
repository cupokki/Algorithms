package programmers.n12902;

public class Solution {
    /*
    3xn 타일링
    2xn 타일보다 세로 크기가 크다.

    */
    static int MOD = 1_000_000_007;
    public int solution(int n) {
        long[] dp = new long[n + 1];

        dp[0] = 1;
        if (n >= 2) dp[2] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 2] * 3) % MOD;
            for (int j = 4; j <= i; j += 2)  {
                dp[i] = (dp[i] + (dp[i - j] * 2)) % MOD;
            }
        }

        return (int) dp[n];
    }
}