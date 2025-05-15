package boj.n17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/17404
 * RGB 거리 2
 *
 * 1번 집의 색은 2, N과 다르다.
 * N번 집의 색은 N-1, 1번고ㅓㅏ 다르다.
 * i번째 집은 i-1, i+1과 다르다.
 * => 쉽게말해 인접한 색 다 달라야 한다.
 */
public class Main {
    static int N;
    static int[][] costs;
    static int min = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][3];
        for (int i = 0; i < N; i++) {
            costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int first = 0; first < 3; first++) {
            int[][] dp = new int[N][3];

            // 첫 집의 색 설정
            for (int i = 0; i < 3; i++) {
                dp[0][i] = (i == first) ? costs[0][i] : 1000001;
            }

            // DP 진행
            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }

            // 마지막과 첫 집의 색이 다름
            for (int last = 0; last < 3; last++) {
                if (last != first) {
                    min = Math.min(min, dp[N - 1][last]);
                }
            }
        }

        System.out.println(min);

    }
}