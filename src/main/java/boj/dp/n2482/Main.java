package boj.dp.n2482;

import java.io.*;

/**
 * 색상환
 * https://www.acmicpc.net/problem/2482
 * <p>
 * 색을 고리모양으로 표현, N색 색상환
 * 색상환의 인접한 두 색을 동시에 사용하지 않도록 K개의 색을 선택하는 경우의 수를 구하라.
 */
public class Main {
    static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // [4, 1000]
        int K = Integer.parseInt(br.readLine()); // [1, N]

        int[][] dp = new int[N + 1][K + 1]; // n개의 색에서 k개의 색깔을 뽑는 경우의 수

        dp[0][0] = 1;
        for (int i = 1; i <= N; i++ ) {
            dp[i][0] = 1;
            dp[i][1] = i;
            for (int j = 1; j <= K; j++) {
                if(i >= 3)
                    dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }
        int result = (dp[N - 1][K] + dp[N - 3][K - 1]) % MOD;
        System.out.println(result);
    }
}

