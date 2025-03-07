package boj.dp.n2225;

import java.util.Scanner;

/**
 * 합분해
 * https://www.acmicpc.net/problem/2225
 *
 * 0부터 N까지 "정수" K개를 더해 합이 N이 되는 경우의 수를 구하라.
 * 덧샘 순서가 다르면 다른 경우이며, 한 수를 여러번 사용할 수 있다.
 */
public class Main {
    static int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[][] dp = new int[N + 1][K + 1];

        // dp[0][0] = 1; -
        dp[0][0] = 1; // 0인가?
        // dp[0][1] = 1; 0
        // dp[0][2] = 1; 00

        // dp[1][0] = 0;
        // dp[1][1] = 1
        // dp[1][2] = 01, 10 = 2
        // dp[1][3] = 001, 010, 100 = 3

        // dp[2][0] = 0;
        // dp[2][1] = 1; 2
        // dp[2][2] = 3; 11, 02, 20
        // dp[2][3] = 6; 110, 011, 101, 200, 002, 020
        // dp[2][4] = ;  1100 0110 1010 0101 1001 0011 2000 0020 0200 0002
        for (int k = 1; k <= K; k++) {
            dp[0][k] = 1;
            dp[1][k] = k;
        }

        //dp[2][0] = 0;

        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                dp[n][k] = (dp[n][k - 1] + dp[n - 1][k]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }
}
