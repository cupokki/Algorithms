package boj.dp.n1309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1309
 * 동물원
 * 2*N 크기의 우리가있다. ( N <= 100000)
 * 사자를 우리에 넣으려한다.
 * 사자는 가로 세로에 인접하면 안된다.
 * 사자를 우리에 넣는 경우의 수를 계산하라.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        dp[0] = 1;
        if(N >= 1)  dp[1] = 3; // 좌, 우, 넣지않음

        for(int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1] + dp[i - 1])% 9901;
        }

        System.out.println(dp[N]);
    }
}
