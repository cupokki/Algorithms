package boj.dp.n2193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2193
 * 이친수
 * 이친수는 아래를 만족하는 이진수이다.
 * 1. 0으로 시작하지 않는다.
 * 2. 1이 두번 연속으로 나타나지 않는다.
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];

        // 초기화
        dp[0] = 0; // i = 0일때, 가짓 수
        if (N >= 1)
            dp[1] = 1; // i = 1일때, 1
        if (N >= 2)
            dp[2] = 1; // i = 2일때, 10

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[N]);
    }
}
