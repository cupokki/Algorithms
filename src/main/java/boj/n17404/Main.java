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
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][3];
        for (int i = 0; i < N; i++) {
            costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 첫 번째 집의 색을 각각 R, G, B로 고정하고 DP 수행
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N][3];

            // 첫 번째 집의 색 설정
            for (int i = 0; i < 3; i++) {
                dp[0][i] = (i == firstColor) ? costs[0][i] : Integer.MAX_VALUE;
            }

            // DP 진행
            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }

            // 마지막 집의 색이 첫 번째 집의 색과 달라야 하므로 체크
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    min = Math.min(min, dp[N - 1][lastColor]);
                }
            }
        }

        System.out.println(min);

    }
}