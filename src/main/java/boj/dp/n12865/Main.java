package boj.dp.n12865;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/12865
 * 평범한 배낭
 * 01knapsack
 *
 * 무게가 i일때, j번째 물건을 고려할때
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //
        int k = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] weight = new int[n + 1];
        int[] value = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][k + 1]; // 가지

        for (int i = 1; i <= n; i++) { // i번째 물건 까지 고려
            for (int j = 0; j <= k; j++) { // 무게 j까지 고려
                if(weight[i] > j) // 현재 고려중인 물건의 무게가 j보다 크다면
                    dp[i][j] = dp[i - 1][j]; // i-1 번째 물건을 고려한 가치와 같다.
                else { // 현재 물건을 넣은것과, 넣지 않은 것의 가치를 비교한다.
                    int temp = dp[i - 1][j - weight[i]] + value[i]; // i-1까지 고려했을 때, 현재 무게에서 i번째 물건의 무게를 뺀
                    dp[i][j] = Math.max(temp, dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}

