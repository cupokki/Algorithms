package boj.dp.n2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전 2
 * https://www.acmicpc.net/problem/2294
 *
 * n가지 동전, 동전으로 k원을 만든다. 동전의 !최소개수!를 구한다.
 *  가치가 같은 동전이 여러번 주어질 수 있다.!!!?
 *      -> 같은 금액이라도 경우의 수가 달라 질 수 있다?
 *      -> 경우의 수가 더 생긴다. 어떻게 계산 할 것인가.
 *  (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
 *  시간 제한 1초, 메모리 128메가
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][0] = 0; // 0원을 만드는 최소 동전개수 0개
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) { // 1번째 동전을 쓰고 j원을 만드는 최소 경우의 수
                // j원을 못만들면 continue;
                if (j >= coins[i] && dp[i][j - coins[i]] != -1) {
                    if(dp[i][j] == -1) {
                        dp[i][j] = dp[i][j - coins[i]] + 1;
                    }
                    else {
                        dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i][j]);
                    }
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
