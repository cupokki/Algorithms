package boj.dp.n4883;

import java.io.*;
import java.util.*;

/**
 * 삼각 그래프
 * https://www.acmicpc.net/problem/4883
 *
 * 삼각그래프는 사이클이 없는, 일방향 그래프 N >= 2 개의 행과, 3개의 열.
 * 간선은 같은 한 노드의 오른쪽이나 바로 아래, 열 - 1, 열 + 1로 연결되어있다.
 * 삼각그래프(그림참조)의 가장 위쪽 가운데 정점에서 가장 아래 정점으로 가는 최단 경로를 찾는 문제이다.
 * 정점에 비용이 있다. 이 바용을 최소로 하여야한다.
 *
 * 테스트 케이스 K가 주어지고 k회 N이 주어진다.
 * 각각의 최소비용을 한 행에 k. n형태로 출력하라.
 *
 * 비용은 정수이며, 비용의 제곱은 1,000,000보다 작다.
 * 말장난이네.. 음수일 수 있다는 것
 */
public class Main {
    static final int col = 3;
    public static void main(String[] args) throws IOException{
//        i - 1, i, i + 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int t = 1;
        while(N > 0) {
            // 비용 배열 초기화
            int[][] costs = new int[N + 1][col]; // 각 요소, 비용의 제곱은 1백만 보다 작다.
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < col; j++) {
                    costs[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            long[][] dp = new long[N + 1][col];
            dp[1][0] = costs[1][1];
            dp[1][1] = costs[1][1];
            dp[1][2] = Math.min(costs[1][1], costs[1][1] + costs[1][2] );

            for(int i = 2; i <= N; i++) { // i행까지 고려
                for (int j = 0; j < col; j++) {
                    dp[i][j] = dp[i - 1][j] + costs[i][j]; // 바로 위
                    if (j - 1 >= 0) {
//                        int temp = Math.min(dp[i - 1][j - 1] + costs[i][j], dp[i][j - 1] + costs[i][j]);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + costs[i][j]); // 옆에서 오는 경우
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]  + costs[i][j]); // 왼쪽 대각선 위
                    }
                    if (j + 1 < col ) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]  + costs[i][j]); // 오른쪽 대각선 위

                    }
                }
            }

            System.out.println(t + ". " + dp[N][1]);
            t++;
            N = Integer.parseInt(br.readLine());
        }

    }
}
