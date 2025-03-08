package boj.dp.n11048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이동하기
 * https://www.acmicpc.net/problem/11048
 * N×M 크기의 미로. 각 칸에 사탕이 있다.
 * 미로의 좌상 방 (1,1)
 * 미로의 우하 방 (N,M)
 * 우, 하, 우하로 이동 할 수 있다. 이동시에 사탕을 가져간다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N + 1][M + 1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                maze[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][M + 1];


        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                dp[r][c] = maze[r][c] + Math.max(dp[r - 1][c], Math.max(dp[r - 1][c - 1], dp[r][c - 1]));
            }
        }



        System.out.println(dp[N][M]);
    }
}
