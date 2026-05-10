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

        long sum = 0;
        for (int i = 4; i <= n; i += 2) {
            sum = (sum + dp[i - 4]) % MOD;
            dp[i] = (dp[i - 2] * 3 + sum * 2) % MOD;
        }

        return (int) dp[n];
    }
}