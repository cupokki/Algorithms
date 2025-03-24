package boj.dp.n1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 점프
 * https://www.acmicpc.net/problem/1890
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N + 1][N + 1];


        // dp[i][j] : (i,j)까지 경로 갯수

        dp[1][1] = 1;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for (int k = i - 1; k >= 1; k--) {
                    if(i == board[k][j] + k)
                        dp[i][j] += dp[k][j];
                }
                for (int l = j - 1; l >= 1; l--) {
                    if (j == board[i][l] + l)
                        dp[i][j] += dp[i][l];

                }
            }
        }
        System.out.println(dp[N][N]);
    }
}
