package boj.n11049;

import java.util.Scanner;

/**
 * 행렬 곱셈 순서
 * https://acmicpc.net/problem/11049
 *
 * N개의 행렬 크가가 주어질 때
 * 최소한으로 연산하는 횟수를 구하라
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] matrices = new int[N + 1][2];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            matrices[i][0] = sc.nextInt();
            matrices[i][1] = sc.nextInt();
        }

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = dp[i][k] + dp[k + 1][j] + matrices[i][0] * matrices[k][1] * matrices[j][1];
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
            }
        }

        System.out.println(dp[1][N]);
        sc.close();
    }
}