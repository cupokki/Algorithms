package boj.dp.n2839;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2839
 * 설탕 배달
 * 3키로와 5키로짜리 설탕봉지, N키로그람을 배달해야한다.
 * 최소한의 봉지로 배송하면 봉지 몇개가 필요한가.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N + 1];
        Arrays.fill(dp,  Integer.MAX_VALUE);
        dp[3] = 1; // N 은 3이상 //dp[i - 3] + 1
        if (N >= 5) dp[5] = 1; // dp[i - 5] + 1
        for (int i = 6; i <= N; i++) {
            // 먼저 가능한지 판단해야한다.
            dp[i] = Math.min(dp[i - 3], dp[i - 5]);
            if(dp[i] == Integer.MAX_VALUE)
                continue;
            dp[i]++;
        }

        System.out.println(dp[N] == Integer.MAX_VALUE ? "-1" : dp[N]);

    }
}
