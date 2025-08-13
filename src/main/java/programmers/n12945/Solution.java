package programmers.n12945;

import java.util.Arrays;

public class Solution {
    static final int MOD = 1_234_567;
//     static public int solution(int n) {
//         int[] dp = new int[n + 1];
//         dp[1] = 1;
//         for (int i = 2; i <= n; i++) {
//             dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
//         }
//         return dp[n];
//     }
    static int[] memo;
    static public int solution(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        if (n >= 1) {
            memo[1] = 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    static public int fib(int n) {
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = (fib(n - 1) + fib(n - 2)) % MOD;
        return memo[n];
    }
    public static void main(String[] args) {
        System.out.println(solution(3));
        System.out.println(solution(5));
    }

}
