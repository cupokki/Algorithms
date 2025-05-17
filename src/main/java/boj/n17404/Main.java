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

        // 시작점
        for (int s = 0; s < 3; s++) {
            int[][] dp = new int[N][3];
            dp[0][s] = costs[0][s];

            for (int i = 1; i < N; i++) {
//                dp[i][0] = Math.min(dp[i][1]) + costs[i][0];
            }

            for (int e = 0; e < 3; e++) {
                Math.min(dp[3][e], min);
            }

            System.out.println(min);
        }
    }
}