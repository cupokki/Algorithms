package boj.dp.n2133;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 타일 채우기
 * https://www.acmicpc.net/problem/2133
 * 3 x n 크기의 벽을 2 x 1, 1 x 2 타일로 채우는 경우의 수를 구하라.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); //(1 ≤ N ≤ 30)
        int[] dp = new int[n + 1];

        dp[0] = 1; // 아무것도 놓지않는 경우
        if (n >= 1) dp[1] = 0; // 불가능
        if (n >= 2) dp[2] = 3;

        for (int i = 4; i <= n; i += 2) { // 홀수일때는 경우의 수가 무조건 0
            // dp[i] = 3 * dp[i - 2] + 2 * (dp[i - 4] + dp[i - 6] + .... dp[i - i])
            dp[i] = dp[i - 2] * 3;
            for (int j = 4 ; j <= i; j += 2) // 4 부터 i까지
                 dp[i] += dp[i - j] * 2;
        }

        sb.append(dp[n]);
        System.out.println(sb);
    }
}
