package programmers.n12900;

public class Solution {
    static final int MOD = 1_000_000_007;
//    public static int solution(int n) {
//        int[] dp = new int[n + 1];
//
//        dp[1] = 1;
//        if (n >= 2) dp[2] = 2;
//        for (int i = 3; i <= n; i++) {
//            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
//        }
//
//        return dp[n];
//    }
    public static int solution(int n) {
        memo = new int[n + 1];
        memo[1] = 1;
        if (n >= 2) memo[2] = 2;
        return func(n);
    }
    static int[] memo;
    static int func(int i) {
        if (memo[i] != 0) {
            return memo[i];
        }
        return memo[i] = (func(i - 1) + func(i - 2)) % MOD;
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
