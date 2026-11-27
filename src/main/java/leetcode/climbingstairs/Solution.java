package leetcode.climbingstairs;

import java.util.*;

class Solution {
    // public int climbStairs(int n) {
    //     int[] dp = new int[n + 1];

    //     dp[0] = 1;
    //     if (n >= 1) dp[1] = 1;
    //     for (int i = 2; i <= n; i++) {
    //         dp[i] = dp[i - 1] + dp[i - 2];
    //     }

    //     return dp[n];
    // }

    public int climbStairs(int n ) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 1;
        if (n >= 1) memo[1] = 1;
        return climb(n);
    }

    int[] memo;
    int climb(int n) {
        if (memo[n] != -1) return memo[n];
        memo[n] = climb(n - 1) + climb(n - 2);
        return memo[n];
    }
}