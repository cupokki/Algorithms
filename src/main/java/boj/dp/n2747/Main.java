package boj.dp.n2747;

import java.util.Scanner;

/**
 * 피보나치 수
 * https://www.acmicpc.net/problem/2747
 *
 * n번째 피보나치수를 출력하라
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
