package boj.dp.n2748;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2748
 * 피보나치 수 2
 *
 *  피보나치수 F_n = F_{n-1} + F_{n-2} (n ≥ 2)
 *  n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오
 *
 *  n은 90이하
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] dp = new long[n + 1];

        if(n >= 1)
            dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
