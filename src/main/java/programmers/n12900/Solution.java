package programmers.n12900;

public class Solution {
    static final int MOD = 1_000_000_007;
    public static int solution(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        if (n >= 2) dp[2] = 2;
        if (n >= 3) dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
